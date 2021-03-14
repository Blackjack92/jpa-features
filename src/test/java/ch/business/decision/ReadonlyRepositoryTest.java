package ch.business.decision;

import ch.business.decision.features.ReadonlyPersonChanger;
import ch.business.decision.models.Person;
import ch.business.decision.repositories.PersonRepository;
import ch.business.decision.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReadonlyRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private ReadonlyPersonChanger personChanger;

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
    void readonlyRepository() {
        // Here the person is "John Doe"
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        this.repository.save(person);

        // Change the name from "John Doe" to "Jane Doe" in a readonly transaction
        this.personChanger.changePerson();

        List<Person> persons = this.repository.findAll();

        Person expectedPerson = new Person();
        expectedPerson.setFirstName("John");
        expectedPerson.setLastName("Doe");
        assertThat(persons)
                .isNotEmpty()
                .hasSize(1);
        assertEquals(expectedPerson, persons.get(0));
    }
}
