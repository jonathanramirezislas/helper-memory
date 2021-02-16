package jonas.com.helpermemory.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jonas.com.helpermemory.entities.PostEntity;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {
    
}
