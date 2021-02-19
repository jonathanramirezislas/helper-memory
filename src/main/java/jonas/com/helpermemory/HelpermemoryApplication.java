package jonas.com.helpermemory;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jonas.com.helpermemory.security.AppProperties;

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
		 return new ModelMapper();

	}

}
