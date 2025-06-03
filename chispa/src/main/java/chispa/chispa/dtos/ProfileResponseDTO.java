package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.Gender;
import chispa.chispa.models.enums.PreferredRelationship;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for user profile response data.
 * Represents the data returned when querying a user profile.
 */
@Data
public class ProfileResponseDTO {
    /**
     * Unique identifier of the profile
     */
    private final Long id;

    /** The user associated with this profile */
    private final Users user;

    /** User's first name */
    private final String name;

    /** User's last name */
    private final String lastName;

    /** User's gender */
    private final Gender gender;

    /** User's date of birth */
    private final LocalDate birthDate;

    /** User's age calculated from birthDate */
    private final Integer age;

    /** User's location description */
    private final String location;

    /** Short biography of the user */
    private final String bio;

    /** User interests */
    private final String interests;

    /** Profile photo data or URL */
    private final String profilePhoto;

    /** Whether the user is currently online */
    private final Boolean isOnline;

    /** Timestamp of the user's last active session */
    private final LocalDateTime lastActive;

    /** Preferred type of relationship */
    private final PreferredRelationship preferredRelationship;
}
