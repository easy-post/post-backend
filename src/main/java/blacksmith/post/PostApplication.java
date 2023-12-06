package blacksmith.post;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class PostApplication {

	@PostConstruct
	public void started(){
	}

	public static void main(String[] args) {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		SpringApplication.run(PostApplication.class, args);
	}
}
