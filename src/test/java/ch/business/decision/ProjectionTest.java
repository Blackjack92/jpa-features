package ch.business.decision;

import ch.business.decision.dtos.PersonDTO;
import ch.business.decision.dtos.PersonView;
import ch.business.decision.models.Person;
import ch.business.decision.repositories.PersonRepository;
import ch.business.decision.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProjectionTest {

    @Autowired
    private PersonRepository repository;

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
    void findByFirstName_class_projection() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        this.repository.save(person);

        Optional<PersonDTO> dtoOptional = this.repository.findDTOByFirstName("John");
        assertThat(dtoOptional).isNotEmpty();
        assertThat(dtoOptional.get().getFirstName())
                .isEqualTo("John");
    }

    @Test
    void findByFirstName_interface_projection() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        this.repository.save(person);

        Optional<PersonView> viewOptional = this.repository.findByFirstName("John");
        assertThat(viewOptional).isNotEmpty();
        assertThat(viewOptional.get().getFirstName())
                .isEqualTo("John");
    }
}
