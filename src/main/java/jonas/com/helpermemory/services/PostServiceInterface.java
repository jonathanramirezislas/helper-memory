package jonas.com.helpermemory.services;

import java.util.List;

import jonas.com.helpermemory.shared.dto.PostCreationDto;
import jonas.com.helpermemory.shared.dto.PostDto;

public interface PostServiceInterface {
    public PostDto createPost(PostCreationDto post);

    public List<PostDto> getLastPosts();

    public PostDto getPost(String postId);

    public void deletePost(String postId, long userId);

    
}
