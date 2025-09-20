package dev.gaming_club.GamingClub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "dev.gaming_club.GamingClub") 
public class GamingClubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamingClubApplication.class, args);
	}
	@GetMapping("/test")
	public String testEndpoint() {
		return "Security test endpoint is working!";
	}

}

// Use ctrl A and ctrl shift o to import all the utilities