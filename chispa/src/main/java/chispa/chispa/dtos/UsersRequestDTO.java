package chispa.chispa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersRequestDTO {
    private final String firstname;
    private final String lastname;
    private final String username;
    private final String email;
    private final String password;

}
