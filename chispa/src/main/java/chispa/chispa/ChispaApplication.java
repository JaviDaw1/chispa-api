package chispa.chispa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main class of the Chispa application.
 * This is the entry point of the Spring Boot application.
 * <p>
 * - @EnableJpaAuditing enables JPA Auditing (e.g., creation/update timestamps).
 * - @SpringBootApplication marks this as a Spring Boot application.
 */
@EnableJpaAuditing
@SpringBootApplication
public class ChispaApplication {

	/**
	 * Main method that runs the application.
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ChispaApplication.class, args);
	}

}
