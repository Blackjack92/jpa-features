package ch.business.decision;

import ch.business.decision.features.StateMachine;
import ch.business.decision.features.StateMachine.State;
import ch.business.decision.models.Person;
import ch.business.decision.repositories.PersonRepository;
import ch.business.decision.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class StateMachineTest {



    @Autowired
    private StateMachine stateMachine;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setup() {
        System.out.println(Utils.DASHES + "[START]");
    }

    @AfterEach
    void cleanup() {
        System.out.println(Utils.DASHES + "[FINISH]");
        this.personRepository.deleteAll();
    }

    @Test
    void runStateMachine_withNewPerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        int balanceIncrease = 10;

        Queue<State> states = this.stateMachine.run(person, balanceIncrease);

        Person expectedPerson = new Person(null, "John", "Doe", 10);
        List<Person> allPersons = this.personRepository.findAll();
        assertThat(allPersons).isNotEmpty()
                .hasSize(1)
                .contains(expectedPerson);

        assertThat(states).containsExactly(
                State.INPUT_DATA_IS_VALID,
                State.PERSON_DOES_NOT_EXIST,
                State.PERSON_CREATED,
                State.FINISHED);
    }

    @Test
    void runStateMachine_withExisting() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setBalance(7);
        int balanceIncrease = 10;

        this.personRepository.save(person);

        Queue<State> states = this.stateMachine.run(person, balanceIncrease);

        Person expectedPerson = new Person(null, "John", "Doe", 17);
        List<Person> allPersons = this.personRepository.findAll();
        assertThat(allPersons).isNotEmpty()
                .hasSize(1)
                .contains(expectedPerson);

        assertThat(states).containsExactly(
                State.INPUT_DATA_IS_VALID,
                State.PERSON_EXISTS,
                State.BALANCE_UPDATED,
                State.FINISHED);
    }
}
