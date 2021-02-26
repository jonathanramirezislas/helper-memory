package jonas.com.helpermemory.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import jonas.com.helpermemory.utils.Exposures;

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

        // get user by email
        UserEntity userEntity = userRepository.findByEmail(post.getUserEmail());
        // get exposure by id
        ExposureEntity exposureEntity = exposureRepository.findById(post.getExposureId());

        PostEntity postEntity = new PostEntity();
        postEntity.setUser(userEntity);
        postEntity.setExposure(exposureEntity);
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setPostId(UUID.randomUUID().toString());// Public Id
        postEntity.setExpiresAt(new Date(System.currentTimeMillis() + (post.getExpirationTime() * 60000)));// time that
                                                                                                           // will be
                                                                                                           // expired
                                                                                                           // the post

        // save Post
        PostEntity createdPost = postRepository.save(postEntity);
        // pas from entity to PostDto
        PostDto postToReturn = mapper.map(createdPost, PostDto.class);

        return postToReturn;

    }

    @Override
    public List<PostDto> getLastPosts() {

        List<PostEntity> postEntities = postRepository.getLastPublicPosts(Exposures.PUBLIC,
                new Date(System.currentTimeMillis()));

        List<PostDto> postDtos = new ArrayList<>();

        for (PostEntity post : postEntities) {
            PostDto postDto = mapper.map(post, PostDto.class);
            postDtos.add(postDto);
        }

        return postDtos;
    }

    @Override
    public PostDto getPost(String postId) {

        PostEntity postEntity = postRepository.findByPostId(postId);
        PostDto postDto = mapper.map(postEntity, PostDto.class);

        return postDto;
    }

}
