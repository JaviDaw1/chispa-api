/*
package chispa.chispa.auth;

import chispa.chispa.models.enums.UserRole;
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
    @Value("${jwt.secret-key}")
    private String jwtSecretKey;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public String createToken(String username, Collection<? extends GrantedAuthority> authorities, UserRole userRole) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);
            token = JWT.create()
                    .withIssuer("chispa")
                    .withSubject(username)
                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            // Manejar excepciones...
        }
        return token;
    }
}
*/
