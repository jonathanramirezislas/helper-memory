package jonas.com.helpermemory.models.responses;

import java.util.List;

/**
 * UserRest
 */
public class UserRest {

    private String userId; // sdfgsdfgsdgfsd uuid
    private String firstName;
    private String lastName;
    private String email;
    private List<PostRest> posts;//this will caouse a problem of ininite mapping(so we had to configure Modelmapper)


    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<PostRest> getPosts() {
        return this.posts;
    }

    public void setPosts(List<PostRest> posts) {
        this.posts = posts;
    }

}