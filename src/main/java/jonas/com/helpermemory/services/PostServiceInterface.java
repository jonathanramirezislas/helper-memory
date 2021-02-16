package jonas.com.helpermemory.services;

import jonas.com.helpermemory.shared.dto.PostCreationDto;
import jonas.com.helpermemory.shared.dto.PostDto;

public interface PostServiceInterface {
    public PostDto createPost(PostCreationDto post);

}
