package chispa.chispa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ChispaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChispaApplication.class, args);
	}

}
