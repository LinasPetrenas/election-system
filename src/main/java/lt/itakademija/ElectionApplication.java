package lt.itakademija;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lt.itakademija.auth.AuthenticationEntity;
import lt.itakademija.auth.AuthenticationRepository;
import lt.itakademija.auth.AuthenticationService;
import lt.itakademija.party.PartyEntity;
import lt.itakademija.party.PartyService;

@SpringBootApplication
public class ElectionApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	public static void main(String[] args) {

		
		
		SpringApplication.run(ElectionApplication.class, args);
	}
	

	
	
	@Bean
	public CommandLineRunner demo(AuthenticationService service) {
		return (args) -> {
			
			service.save(new AuthenticationEntity(0L,"admin","admin", "ADMIN"));
		
			
			
		};
	}
	
}