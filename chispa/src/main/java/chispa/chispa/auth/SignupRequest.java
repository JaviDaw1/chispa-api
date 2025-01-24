
package chispa.chispa.auth;

import chispa.chispa.models.enums.Role;
import lombok.Data;

@Data
public class SignupRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Role UserRole;
}

