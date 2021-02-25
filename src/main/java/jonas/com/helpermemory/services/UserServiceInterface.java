package jonas.com.helpermemory.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import jonas.com.helpermemory.shared.dto.PostDto;
import jonas.com.helpermemory.shared.dto.UserDto;


public interface UserServiceInterface extends UserDetailsService {

    public UserDto createUser(UserDto user);
    //this method is for getting the public Id 
    public UserDto getUser(String email);
    //this method returns the User's posts
    public List<PostDto> getUserPosts(String email);
}