package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileRequestDTO {
    private final Users user;
    private final String name;
    private final String lastName;
    private final String gender; // Enum: 'male', 'female', 'other'
    private final String location;
    private final String bio;
    private final String interests;
    private final String profilePhoto;
    private final Boolean isOnline;
    private final String preferredRelationship;  // Enum: 'friendship', 'casual', 'serious'
}
