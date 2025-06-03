package chispa.chispa.auth;

import lombok.Data;

/**
 * DTO for user login requests.
 * This class captures the user's email and password when logging in.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
public class LoginRequest {

    // User's email used for login
    private String email;

    // User's password used for authentication
    private String password;
}
