package jonas.com.helpermemory.shared.dto;

import java.io.Serializable;

/**
 * UserDto
 */
public class UserDto implements Serializable{

    private static final long serialVersionUID=1L;

    private long id; 
    private String userID;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String encryptedPassword;
    
}
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
