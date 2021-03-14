package ch.business.decision.repositories;

import ch.business.decision.dtos.PersonDTO;
import ch.business.decision.dtos.PersonView;
import ch.business.decision.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT new ch.business.decision.dtos.PersonDTO(a.firstName) FROM Person a WHERE a.firstName = :firstName")
    Optional<PersonDTO> findDTOByFirstName(String firstName);

    Optional<PersonView> findByFirstName(String firstName);
}
