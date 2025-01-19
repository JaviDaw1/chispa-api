package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileResponseDTO {
    private final Long id;
    private final Users user;
    private final String name;
    private final String lastName;
    private final String gender;
    private final String location;
    private final String bio;
    private final String interests;
    private final String profilePhoto;
    private final Boolean isOnline;
    private final LocalDate lastActive;
    private final String preferredRelationship;
}
