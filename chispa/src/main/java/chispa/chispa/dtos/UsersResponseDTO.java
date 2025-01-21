package chispa.chispa.dtos;

import chispa.chispa.models.enums.UserRole;
import chispa.chispa.models.enums.UserState;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UsersResponseDTO {
    private final Long id;
    private final String email;
    private final String password;
    private final UserRole role;
    private final UserState state;
    private final Integer age;
    private final LocalDate joinDate;
}
