package jonas.com.helpermemory;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jonas.com.helpermemory.models.responses.UserRest;
import jonas.com.helpermemory.security.AppProperties;
import jonas.com.helpermemory.shared.dto.UserDto;

@SpringBootApplication 
@EnableJpaAuditing //this allow to create automatically dates
public class HelpermemoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpermemoryApplication.class, args);
		System.out.print("Working...");
	}

	@Bean
	public BCryptPasswordEncoder  BCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	 
	//We booked the name appProperties for us in order to avoid conflicts if there is another Bean using this name  by Spring
	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper mapper = new ModelMapper();
		//avoid to copy from UserDto to UserRest(Post)
		mapper.typeMap(UserDto.class, UserRest.class).addMappings(m -> m.skip(UserRest::setPosts));

		return mapper;
	}

}
