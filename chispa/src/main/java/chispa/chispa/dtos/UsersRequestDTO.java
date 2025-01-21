package chispa.chispa.dtos;

import chispa.chispa.models.enums.UserRole;
import chispa.chispa.models.enums.UserState;
import lombok.Data;

@Data
public class UsersRequestDTO {
    private final String email;
    private final String password;
    private final UserRole role;
    private final UserState state;
    private final Integer age;
}