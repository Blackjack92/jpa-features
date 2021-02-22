package ch.business.decision.repositories;

import ch.business.decision.models.Person;

import java.util.Optional;

public interface PersonReadonlyRepository extends ReadonlyRepository<Person, Long> {

    Optional<Person> findByFirstName(String firstName);
}
