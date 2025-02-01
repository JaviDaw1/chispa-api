
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

@NoArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            DecodedJWT decodedJWT;
            try {
                Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);
                JWTVerifier verifier = JWT.require(algorithm).withIssuer("chispa").build();
                decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                String role = decodedJWT.getClaim("role").asString();
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        "",
                        getAuthoritiesFromRole(role)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException exception) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Error de autenticación: " + exception.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
    private Collection<? extends GrantedAuthority> getAuthoritiesFromRole(String role) {
        if ("ADMIN".equals(role)) {
            return AuthorityUtils.createAuthorityList("ADMIN");
        } else {
            return AuthorityUtils.createAuthorityList("USER");
        }
    }
}

