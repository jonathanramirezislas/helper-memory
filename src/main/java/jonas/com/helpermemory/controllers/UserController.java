package jonas.com.helpermemory.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") // locaslhost:8080/users
public class UserController {

    @GetMapping
    public String getUser(){
        return "get user details";
    }

    @PostMapping
    public String createUser() {
        return "post user details";
    }

}
