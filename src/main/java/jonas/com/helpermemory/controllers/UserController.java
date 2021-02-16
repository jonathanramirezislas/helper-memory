package jonas.com.helpermemory.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jonas.com.helpermemory.models.request.UserDetailsRequestModel;
import jonas.com.helpermemory.models.responses.UserRest;
import jonas.com.helpermemory.services.UserServiceInterface;
import jonas.com.helpermemory.shared.dto.UserDto;

@RestController
@RequestMapping("/users") // locaslhost:8080/users
public class UserController {

    // Injection dependencies
    @Autowired
    UserServiceInterface userService;

    @Autowired
    ModelMapper mapper; 

    // Return JSON and XML
    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest getUser() {

        // get authentication from the user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // get the email of user that was atheticated
        String email = authentication.getPrincipal().toString();

        UserDto userDto = userService.getUser(email);

        //mapper allows has to go deep in the map like cases of List<Post>
        UserRest userToReturn = mapper.map(userDto, UserRest.class);


        return userToReturn;
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

        UserRest userToReturn = new UserRest();

        UserDto userDto = new UserDto();

        // Copy properties from one to another object
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, userToReturn);

        return userToReturn;// Return the object with the modifications
    }

}
