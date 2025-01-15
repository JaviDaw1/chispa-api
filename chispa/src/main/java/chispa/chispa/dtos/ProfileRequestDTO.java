package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDTO {
    private String name;
    private String lastName;
    private String gender; // Enum: 'male', 'female', 'other'
    private String location;
    private String bio;
    private String interests;
    private String profilePhoto;
    private Boolean isOnline;
    private String lastActive;
    private String preferredRelationship;  // Enum: 'friendship', 'casual', 'serious'

}
