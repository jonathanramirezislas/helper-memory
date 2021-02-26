package jonas.com.helpermemory.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jonas.com.helpermemory.entities.PostEntity;

@Repository                             //this class allows to create pagination and sorting
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {
   
    //get user's post                              //by id                        
    List<PostEntity> getByUserIdOrderByCreatedAtDesc(long userId);
    //select last 20 public post where the expires day is lower than now
    @Query(value = "SELECT * FROM posts p WHERE p.exposure_id = :exposure and p.expires_at > :now ORDER BY created_at DESC LIMIT 20", nativeQuery = true)
    List<PostEntity> getLastPublicPosts(@Param("exposure") long exposureId, @Param("now") Date now);

}
