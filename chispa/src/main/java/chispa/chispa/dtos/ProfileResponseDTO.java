package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponseDTO {
    private Long id;
    private Long userId;
    private String name;
    private String lastName;
    private String gender;
    private String location;
    private String bio;
    private String interests;
    private String profilePhoto;
    private Boolean isOnline;
    private String lastActive;
    private String preferredRelationship;
}
