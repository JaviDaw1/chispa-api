package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String userEmail;
    private String userRole;
    private String userState;
    private Integer age;
    private LocalDateTime joinDate;


}
