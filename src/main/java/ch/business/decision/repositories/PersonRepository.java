package ch.business.decision.repositories;

import ch.business.decision.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);

}
