package jonas.com.helpermemory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Autowired
    private Environment env;
    ///Get token from properties
    public String getTokenSecret() {
        return env.getProperty("tokenSecret");
    }
    
}
