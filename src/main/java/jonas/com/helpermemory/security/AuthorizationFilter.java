package jonas.com.helpermemory.security;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;


public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

  
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
       
        //Get the parms from the request         
        String header = request.getHeader(SecurityConstants.HEADER_STRING);

        //if header is empty or the prefix of token is no Bearer(stablished by us)
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);//continue to the next middleware (filter)
            return; //so we break this method
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

        //set the resulting getAuthentication object into the current SecurityContext 
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        //get the header from the request
        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if (token != null) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, ""); //remove the prfix (Bearer)
            
            //Validate the token and get the email from getBody().getSubject()
            String user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token)
                    .getBody().getSubject();
            
            //System.out.println("email"+user);
            
            if (user != null) {//if the token was valid
                                                             //email , password, authorities(Admin,User,etc)            
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }

            return null;
        }

        return null;
    }
}
