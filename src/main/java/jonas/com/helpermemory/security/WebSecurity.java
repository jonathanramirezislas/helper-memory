package jonas.com.helpermemory.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jonas.com.helpermemory.services.UserServiceInterface;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserServiceInterface userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    
    public WebSecurity(UserServiceInterface userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /*
    #1 login endpoint is public 
    #2 Allow to get post/last without authentication
    #3 the rest endpoints ask for auth
    #4 filter that we will use as authentication
    #5 filter for authorization using JWT
    #6 We indicate that we dont want to save A VARIABLE of session in the server due to we are usign JWT
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().
        antMatchers(HttpMethod.POST, "/users").permitAll() // #1
        .antMatchers(HttpMethod.GET, "/posts/last").permitAll()//#2
        .anyRequest().authenticated()//#3
        .and().addFilter(getAuthenticationFilter()) //#4
        .addFilter(new AuthorizationFilter(authenticationManager())) //#5
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//#6
    }

    //Tell what service want for the app and the algorithm that we implemented
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        //Override the default endpoint localhost:8080/login to localhost:8080/user/login
        filter.setFilterProcessesUrl("/users/login");
        return filter;
    }

}