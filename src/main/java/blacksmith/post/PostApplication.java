package blacksmith.post;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class PostApplication {

	@PostConstruct
	public void started(){
	}

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		log.info("현재시간 {}", LocalDateTime.now());
		SpringApplication.run(PostApplication.class, args);
	}
}
