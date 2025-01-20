package chispa.chispa.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UsersResponseDTO {
    private final Long id;
    private final String email;
    private final String password;
    private final String role;
    private final String state;
    private final Integer age;
    private final LocalDate joinDate;
}
