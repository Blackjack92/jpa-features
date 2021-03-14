package ch.business.decision.repositories;

import ch.business.decision.models.PersonWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonWrapperRepository extends JpaRepository<PersonWrapper, Long> {
}
