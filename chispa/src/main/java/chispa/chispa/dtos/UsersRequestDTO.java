package chispa.chispa.dtos;

import lombok.Data;

@Data
public class UsersRequestDTO {
    private final String email;
    private final String password;
    private final String role;
    private final String state;
    private final Integer age;
}