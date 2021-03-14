package ch.business.decision;

import ch.business.decision.models.Company;
import ch.business.decision.models.Person;
import ch.business.decision.repositories.CompanyRepository;
import ch.business.decision.utils.Utils;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FetchLazyTest {

    @Autowired
    private CompanyRepository repository;

    @BeforeEach
    void setup() {
        System.out.println(Utils.DASHES + "[START]");
    }

    @AfterEach
    void cleanup() {
        System.out.println(Utils.DASHES + "[FINISH]");
        this.repository.deleteAll();
    }

    @Test
    void lazyInitializationException() {
        Person employee1 = new Person();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");

        Person employee2 = new Person();
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");

        List<Person> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        Company company = new Company();
        company.setName("SuperCompany");
        company.setEmployees(employees);
        this.repository.saveAndFlush(company);

        Optional<Company> companyOptional = this.repository.findByName("SuperCompany");

        assertThat(companyOptional).isNotEmpty();
        assertThrows(LazyInitializationException.class, () -> {
            List<Person> readEmployees = companyOptional.get().getEmployees();
            readEmployees.get(0);
        });
    }

    @Test
    void fetchLazy() {
        Person employee1 = new Person();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");

        Person employee2 = new Person();
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");

        List<Person> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        Company company = new Company();
        company.setName("SuperCompany");
        company.setEmployees(employees);
        this.repository.saveAndFlush(company);

        Optional<Company> companyOptional = this.repository.fetchByName("SuperCompany");

        assertThat(companyOptional).isNotEmpty();

        List<Person> readEmployees = companyOptional.get().getEmployees();
        readEmployees.get(0);
    }
}
