package jonas.com.helpermemory.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jonas.com.helpermemory.models.request.UserDetailRequestModel;
import jonas.com.helpermemory.models.responses.UserRest;
import jonas.com.helpermemory.services.UserServiceInterface;
import jonas.com.helpermemory.shared.dto.UserDto;

@RestController
@RequestMapping("/users") // locaslhost:8080/users
public class UserController {

    //Injection dependencies
    @Autowired
    UserServiceInterface userService;

    @GetMapping
    public String getUser(){
        return "get user details";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailRequestModel userDetails) {
       
        UserRest userToReturn = new UserRest();

        UserDto userDto = new UserDto();
        
        //Copy properties from one to another object
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, userToReturn);

        return userToReturn;//Return the object with the modifications
    }




}
