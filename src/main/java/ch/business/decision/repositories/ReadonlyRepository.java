package ch.business.decision.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface ReadonlyRepository<T, ID> extends Repository<T, ID> {

    Optional<T> findById(ID id);

}
