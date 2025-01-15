package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    private String userEmail;
    private String userPassword;
    private String userRole;
    private String userState;
    private Integer age;

}