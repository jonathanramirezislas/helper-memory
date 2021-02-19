package jonas.com.helpermemory.services;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jonas.com.helpermemory.entities.ExposureEntity;
import jonas.com.helpermemory.entities.PostEntity;
import jonas.com.helpermemory.entities.UserEntity;
import jonas.com.helpermemory.repositories.ExposureRepository;
import jonas.com.helpermemory.repositories.PostRepository;
import jonas.com.helpermemory.repositories.UserRepository;
import jonas.com.helpermemory.shared.dto.PostCreationDto;
import jonas.com.helpermemory.shared.dto.PostDto;

@Service
public class PostService implements PostServiceInterface {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExposureRepository exposureRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public PostDto createPost(PostCreationDto post) {

        //get user by email
        UserEntity userEntity = userRepository.findByEmail(post.getUserEmail());
        //get exposure by id
        ExposureEntity exposureEntity = exposureRepository.findById(post.getExposureId());

        PostEntity postEntity = new PostEntity();
        postEntity.setUser(userEntity); 
        postEntity.setExposure(exposureEntity);
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setPostId(UUID.randomUUID().toString());//Public Id 
        postEntity.setExpiresAt(new Date(System.currentTimeMillis() + (post.getExpirationTime() * 60000)));//time that will be expired the post
       
        //save Post
        PostEntity createdPost = postRepository.save(postEntity);
        //pas from entity to PostDto 
        PostDto postToReturn = mapper.map(createdPost, PostDto.class);

        return postToReturn;

       

    }

}
