package jonas.com.helpermemory.services;

import jonas.com.helpermemory.shared.dto.UserDto;

/**
 * UserServiceInterface
 */
public interface UserServiceInterface {

    public UserDto createUser(UserDto user);
}