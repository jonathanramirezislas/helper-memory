package jonas.com.helpermemory.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jonas.com.helpermemory.models.request.UserDetailRequestModel;
import jonas.com.helpermemory.models.responses.UserRest;

@RestController
@RequestMapping("/users") // locaslhost:8080/users
public class UserController {

    @GetMapping
    public String getUser(){
        return "get user details";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailRequestModel userDedatils) {
        return null;
    }

}
