package jonas.com.helpermemory.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jonas.com.helpermemory.shared.dto.PostCreationDto;

@RestController
@RequestMapping("/posts")
public class PostController {

    //TODO:
    @PostMapping
    public String createPost(@RequestBody  PostCreationDto createRequestModel) {
        return createRequestModel.getTitle();
    }

   

}
