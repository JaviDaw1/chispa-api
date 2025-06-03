package chispa.chispa.auth;

import chispa.chispa.models.enums.Role;
import lombok.Data;

/**
 * DTO for user signup (registration) requests.
 * This class is used to collect all the information needed to create a new user.
 */
@Data // Lombok annotation that generates getters, setters, toString, equals, and hashCode
public class SignupRequest {

    // User's first name
    private String firstname;

    // User's last name
    private String lastname;

    // Unique username chosen by the user
    private String username;

    // User's email address
    private String email;

    // User's chosen password
    private String password;

    // Role assigned to the user (e.g., ADMIN or USER)
    private Role UserRole;
}
