package jonas.com.helpermemory.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import jonas.com.helpermemory.shared.dto.UserDto;


public interface UserServiceInterface extends UserDetailsService {

    public UserDto createUser(UserDto user);
}