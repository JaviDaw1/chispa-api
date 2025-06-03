package chispa.chispa.auth;

import chispa.chispa.models.enums.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class JwtService {

    // Secret key for signing JWTs, loaded from application.properties
    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    // Token expiration time in milliseconds, loaded from application.properties
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    /**
     * Creates a JWT token with the given username, authorities, and role.
     * Adds issuer, subject, role claim, and expiration time to the token.
     *
     * @param username    the username of the authenticated user
     * @param authorities the list of granted authorities (not used here but could be extended)
     * @param userRole    the role of the user (e.g., ADMIN or USER)
     * @return a signed JWT token
     */
    public String createToken(String username, Collection<? extends GrantedAuthority> authorities, Role userRole) {
        String token = "";
        try {
            // Create the HMAC256 algorithm using the secret key
            Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);

            // Create and sign the JWT token with issuer, subject, role, and expiration
            token = JWT.create()
                    .withIssuer("chispa") // Issuer of the token
                    .withSubject(username) // Subject (usually username or ID)
                    .withClaim("role", userRole.name()) // Add role as custom claim
                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration)) // Set expiration date
                    .sign(algorithm); // Sign the token
        } catch (JWTCreationException exception) {
            // Handle JWT creation exceptions (e.g., invalid claims or signing errors)
        }

        // Return the generated token
        return token;
    }
}
