package jonas.com.helpermemory;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
        allowedMethods("*").//PUT,POST
        allowedOrigins("*").//URL OF APP 192.168...
        allowedHeaders("*");//
    }

}
