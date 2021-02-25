package jonas.com.helpermemory.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jonas.com.helpermemory.entities.PostEntity;

@Repository                             //this class allows to create pagination and sorting
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {
   
    //get user's post                              //by id                        
    List<PostEntity> getByUserIdOrderByCreatedAtDesc(long userId);

}
