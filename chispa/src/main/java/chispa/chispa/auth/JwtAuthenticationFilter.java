package chispa.chispa.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

/**
 * JwtAuthenticationFilter intercepts incoming HTTP requests and validates the JWT token
 * in the Authorization header. If valid, it sets the user's authentication context with their role.
 */
@NoArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Secret key for verifying JWT, injected from application.properties
    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    /**
     * This method filters every HTTP request to validate the JWT.
     * If the token is valid, it authenticates the user and sets the role.
     * If the token is invalid, it responds with a 401 error.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get the Authorization header from the request
        String authorizationHeader = request.getHeader("Authorization");

        // Check if the header contains a Bearer token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the token (removes "Bearer ")
            String token = authorizationHeader.substring(7);

            try {
                // Create the verification algorithm using the secret key
                Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);

                // Create the JWT verifier with expected issuer "chispa"
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer("chispa")
                        .build();

                // Verify and decode the token
                DecodedJWT decodedJWT = verifier.verify(token);

                // Get the username (subject) from the token
                String username = decodedJWT.getSubject();

                // Get the role from the token claims
                String role = decodedJWT.getClaim("role").asString();

                // Create Spring Security authentication token with username and role
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        getAuthoritiesFromRole(role)
                );

                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (JWTVerificationException exception) {
                // If the token is invalid, respond with 401 and the error message
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Authentication error: " + exception.getMessage());
                return;
            }
        }

        // Continue with the next filter in the chain
        filterChain.doFilter(request, response);
    }

    /**
     * Returns the list of authorities based on the role extracted from the token.
     * If the role is ADMIN, assigns ADMIN authority; otherwise, USER.
     */
    private Collection<? extends GrantedAuthority> getAuthoritiesFromRole(String role) {
        // Map role string to Spring Security authority
        if ("ADMIN".equals(role)) {
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        } else {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }
    }
}
