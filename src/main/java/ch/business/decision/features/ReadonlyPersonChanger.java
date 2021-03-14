package ch.business.decision.features;

import ch.business.decision.models.Person;
import ch.business.decision.repositories.PersonReadonlyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadonlyPersonChanger {

    private final PersonReadonlyRepository repository;

    @Transactional(readOnly = true)
    public void changePerson() {
        Optional<Person> person = this.repository.findByFirstName("John");

        // Read a person inside a transactional context and change the persons attributes
        person.ifPresent(value -> value.setFirstName("Jane"));
    }

}
