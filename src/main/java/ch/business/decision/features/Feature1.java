package ch.business.decision.features;

import ch.business.decision.models.Person;
import ch.business.decision.repositories.PersonReadonlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class Feature1 implements Runnable {

    @Autowired
    private PersonReadonlyRepository repository;

    @Transactional(readOnly = true)
    @Override
    public void run() {
        System.out.println("Feature 1: Readonly repository");
        Optional<Person> person = repository.findByFirstName("John");
        System.out.println(person);

        // Read a person inside a transactional context and change the persons attributes
        person.ifPresent(value -> value.setFirstName("Jane"));
    }
}
