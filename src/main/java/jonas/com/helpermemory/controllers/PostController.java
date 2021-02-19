package jonas.com.helpermemory.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jonas.com.helpermemory.models.request.PostCreateRequestModel;
import jonas.com.helpermemory.models.responses.PostRest;
import jonas.com.helpermemory.services.PostServiceInterface;
import jonas.com.helpermemory.services.UserServiceInterface;
import jonas.com.helpermemory.shared.dto.PostCreationDto;
import jonas.com.helpermemory.shared.dto.PostDto;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostServiceInterface postService;

    @Autowired
    UserServiceInterface userService;

    @Autowired
    ModelMapper mapper;


    @PostMapping
    public PostRest createPost(@RequestBody PostCreateRequestModel createRequestModel) {

        //get email from user who is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();

        //obeject request to PostCreationDto and setemail from user
        PostCreationDto postCreationDto = mapper.map(createRequestModel, PostCreationDto.class);
        postCreationDto.setUserEmail(email);

        //save the post and return a PostDto
        PostDto postDto = postService.createPost(postCreationDto);
        //from PostDto to PostRres(response to client)
        PostRest postToReturn = mapper.map(postDto, PostRest.class);

        return postToReturn;

    }
   

}
