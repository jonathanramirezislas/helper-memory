package jonas.com.helpermemory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jonas.com.helpermemory.entities.UserEntity;

@Repository                                                  //class , id type
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
