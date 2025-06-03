package chispa.chispa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to set up CORS (Cross-Origin Resource Sharing) settings.
 * <p>
 * This configuration allows HTTP requests from specific origins to access the API endpoints.
 * </p>
 */
@Configuration
public class WebConfig {

    /**
     * Configures CORS mappings for the application.
     * <p>
     * Allows requests to `/api/**` endpoints only from "http://localhost:5173"
     * with HTTP methods GET, POST, PUT, DELETE, and OPTIONS.
     * All headers are allowed.
     * </p>
     *
     * @return a {@link WebMvcConfigurer} with the configured CORS mappings
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Add CORS mappings to the registry.
             *
             * @param registry the {@link CorsRegistry} to add mappings to
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")                // Allow CORS for API endpoints starting with /api/
                        .allowedOrigins("http://localhost:5173") // Allow only requests from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                        .allowedHeaders("*");                   // Allow all headers
            }
        };
    }
}
