package ch.business.decision.repositories;

import ch.business.decision.models.VehicleWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleWrapperRepository extends JpaRepository<VehicleWrapper, Long> {
}
