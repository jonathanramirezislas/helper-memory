package jonas.com.helpermemory.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jonas.com.helpermemory.UserRepository;
import jonas.com.helpermemory.entities.UserEntity;
import jonas.com.helpermemory.shared.dto.UserDto;

@Service
public class UserService implements UserServiceInterface {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword("tesPassword");
        userEntity.setUserId("userPublicId");


        UserEntity storedUserDetails = userRepository.save(userEntity);

        //This user has already the id 
        UserDto userToReturn = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, userToReturn);


        return userToReturn;
    }

    
}