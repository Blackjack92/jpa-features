package ch.business.decision.repositories;

import ch.business.decision.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);

    @Query("select c from Company c left join fetch c.employees where c.name = :name")
    Optional<Company> fetchByName(String name);
}
