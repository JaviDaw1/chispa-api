package chispa.chispa.dtos;

import chispa.chispa.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersResponseDTO {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String username;
    private final String email;
    private final String password;
    private final Role userRole;
}
