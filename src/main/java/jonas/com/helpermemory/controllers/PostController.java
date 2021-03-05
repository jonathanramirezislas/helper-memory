package jonas.com.helpermemory.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jonas.com.helpermemory.models.request.PostCreateRequestModel;
import jonas.com.helpermemory.models.responses.OperationStatusModel;
import jonas.com.helpermemory.models.responses.PostRest;
import jonas.com.helpermemory.services.PostServiceInterface;
import jonas.com.helpermemory.services.UserServiceInterface;
import jonas.com.helpermemory.shared.dto.PostCreationDto;
import jonas.com.helpermemory.shared.dto.PostDto;
import jonas.com.helpermemory.shared.dto.UserDto;
import jonas.com.helpermemory.utils.Exposures;

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
    public PostRest createPost(@RequestBody @Valid PostCreateRequestModel createRequestModel) {

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

    @GetMapping(path = "/last") // localhost:8080/posts/last
    public List<PostRest> lastPosts() {
        List<PostDto> posts = postService.getLastPosts();
        List<PostRest> postRests = new ArrayList<>();

        for (PostDto post : posts) {
            PostRest postRest = mapper.map(post, PostRest.class);
            postRests.add(postRest);
        }

        return postRests;
    }
   
    //ONLY the owners will be able to see the details of thier Post
     @GetMapping(path = "/{id}") // localhost:8080/posts/uuid
    public PostRest getPost(@PathVariable String id) {

        PostDto postDto = postService.getPost(id);

        PostRest postRest = mapper.map(postDto, PostRest.class);

        // validate if the post is public o private  OR is expired 
        if (postRest.getExposure().getId() == Exposures.PRIVATE || postRest.getExpired()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            UserDto user = userService.getUser(authentication.getPrincipal().toString());
            //if you are the owner the Post
            if (user.getId() != postDto.getUser().getId()) {
                throw new RuntimeException("You are not allow to do this request");
            }
        }

        return postRest;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deletePost(@PathVariable String id) {
        //get user  who is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userService.getUser(authentication.getPrincipal().toString());

        //object of response to the request
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setName("DELETE");//te operation

        postService.deletePost(id, user.getId());//delete post 

        operationStatusModel.setResult("SUCCESS");//if the delete pass we set SUCCESS

        return operationStatusModel;
    }

    @PutMapping(path = "/{id}")
    public PostRest updatePost(@RequestBody @Valid PostCreateRequestModel postCreateRequestModel,
            @PathVariable String id) {
        //get user  who is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userService.getUser(authentication.getPrincipal().toString());

        //from object request to PostCreationDto(we are using the same due to are the same params)
        PostCreationDto postUpdateDto = mapper.map(postCreateRequestModel, PostCreationDto.class);
       
        //update the post
        PostDto postDto = postService.updatePost(id, user.getId(), postUpdateDto);
        
        //response
        PostRest updatedPost = mapper.map(postDto, PostRest.class);

        return updatedPost;
    }


}
