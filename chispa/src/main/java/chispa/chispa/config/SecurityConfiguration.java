package chispa.chispa.config;

import chispa.chispa.auth.JwtAuthenticationFilter; // Custom JWT filter
import chispa.chispa.repositories.UsersDetailsRepository; // Repository to fetch user details
import chispa.chispa.services.UsersDetailsServiceImpl; // Custom UserDetailsService implementation
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Security configuration class that sets up Spring Security for the application.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    /**
     * Repository to load user details from the database.
     */
    private final UsersDetailsRepository userDetailsRepository;

    /**
     * Defines the security filter chain configuration.
     *
     * @param http the HttpSecurity object used to configure security.
     * @param mvc MvcRequestMatcher builder for path matching.
     * @return the configured SecurityFilterChain bean.
     * @throws Exception in case of configuration errors.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection since we use JWT
                .cors().and() // Enable CORS
                .headers().frameOptions().disable() // Allow H2 console (frame support)
                .and()
                .authorizeRequests()
                .requestMatchers(mvc.pattern("/api/auth/login")).permitAll()
                .requestMatchers(mvc.pattern("/api/auth/users")).permitAll()
                .requestMatchers(mvc.pattern("/api/auth/signup")).permitAll()
                .requestMatchers(mvc.pattern("/api/auth/forgot-password")).permitAll()
                .requestMatchers(mvc.pattern("/api/auth/reset-password")).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    /**
     * Creates and returns the JWT authentication filter.
     *
     * @return a new instance of JwtAuthenticationFilter.
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    /**
     * Configures the authentication provider with custom user details service and password encoder.
     *
     * @return a configured AuthenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Provides an AuthenticationManager bean used to authenticate user credentials.
     *
     * @param http the HttpSecurity used to access shared objects.
     * @return an AuthenticationManager instance.
     * @throws Exception in case of configuration errors.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    /**
     * Provides the custom implementation of UserDetailsService.
     *
     * @return an instance of UserDetailsService.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UsersDetailsServiceImpl(userDetailsRepository, passwordEncoder());
    }

    /**
     * Provides the PasswordEncoder bean using BCrypt hashing algorithm.
     *
     * @return a PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides the MvcRequestMatcher.Builder bean to enable request matchers with path patterns.
     *
     * @param introspector used to map handlers in Spring MVC.
     * @return a builder for MvcRequestMatcher.
     */
    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    /**
     * Configures CORS (Cross-Origin Resource Sharing) settings for the application.
     *
     * @return the configured CorsConfigurationSource.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // Allow all origins
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
