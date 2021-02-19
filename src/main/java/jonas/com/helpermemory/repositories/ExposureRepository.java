package jonas.com.helpermemory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jonas.com.helpermemory.entities.ExposureEntity;


@Repository
public interface ExposureRepository extends CrudRepository<ExposureEntity, Long> {
    ExposureEntity findById(long id);
}