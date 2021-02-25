package jonas.com.helpermemory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jonas.com.helpermemory.repositories.PostRepository;
import jonas.com.helpermemory.repositories.UserRepository;
import jonas.com.helpermemory.entities.PostEntity;
import jonas.com.helpermemory.entities.UserEntity;
import jonas.com.helpermemory.exceptions.EmailExistsException;
import jonas.com.helpermemory.shared.dto.PostDto;
import jonas.com.helpermemory.shared.dto.UserDto;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null){
            //we launch our custome Expection
            throw new EmailExistsException("El correo electronico ya existe");   
        }
    
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        // encrypt password with B
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // METhod from java to genearte Id
        UUID userId = UUID.randomUUID();
        userEntity.setUserId(userId.toString());

        UserEntity storedUserDetails = userRepository.save(userEntity);

        // This user has already the id
        UserDto userToReturn = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, userToReturn);

        return userToReturn;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity==null){
            throw new UsernameNotFoundException(email);
        }

        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserDto getUser(String email) {
        //get the user by email
        UserEntity userEntity = userRepository.findByEmail(email);
        //check if there is a user 
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        //Copy and return the user found
        UserDto userToReturn = new UserDto();
        BeanUtils.copyProperties(userEntity, userToReturn);

        return userToReturn;
    }


    @Override
    public List<PostDto> getUserPosts(String email) {
        //to get the email from user who is logged in
        UserEntity userEntity = userRepository.findByEmail(email);
        // get user's post using method from reporsitory using user's id
        List<PostEntity> posts = postRepository.getByUserIdOrderByCreatedAtDesc(userEntity.getId());

        List<PostDto> postDtos = new ArrayList<>();

        //pass from list<PostEntity> to list<PostDto>
        for (PostEntity post : posts) {
            PostDto postDto = mapper.map(post, PostDto.class);
            postDtos.add(postDto);
        }
        //return list 
        return postDtos;
    }

    
}