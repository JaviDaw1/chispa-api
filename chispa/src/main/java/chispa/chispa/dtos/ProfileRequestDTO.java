package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.Gender;
import chispa.chispa.models.enums.PreferredRelationship;
import lombok.Data;

@Data
public class ProfileRequestDTO {
    private final Users user;
    private final String name;
    private final String lastName;
    private final Gender gender; // Enum: 'male', 'female', 'other'
    private final String location;
    private final String bio;
    private final String interests;
    private final String profilePhoto;
    private final Boolean isOnline;
    private final PreferredRelationship preferredRelationship;  // Enum: 'friendship', 'casual', 'serious'
}
