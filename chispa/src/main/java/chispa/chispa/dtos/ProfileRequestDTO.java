package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.Gender;
import chispa.chispa.models.enums.PreferredRelationship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for user profile request data.
 * Contains all necessary fields to update or create a user profile.
 */
@Data
public class ProfileRequestDTO {
    /**
     * The user associated with this profile
     */
    private final Users user;

    /**
     * User's first name; cannot be blank and max length is 100 characters
     */
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private final String name;

    /**
     * User's last name; cannot be blank and max length is 100 characters
     */
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    private final String lastName;

    /**
     * User's gender; must be provided. Enum values: male, female, other
     */
    @NotNull(message = "Gender cannot be null")
    private final Gender gender;

    /**
     * User's date of birth; cannot be null
     */
    @NotNull(message = "Birth date cannot be null")
    private final LocalDate birthDate;

    /**
     * User's location description; optional with max length 255 characters
     */
    @Size(max = 255, message = "Location cannot exceed 255 characters")
    private final String location;

    /**
     * Short biography of the user; optional with max length 2000 characters
     */
    @Size(max = 2000, message = "Biography cannot exceed 2000 characters")
    private final String bio;

    /** User interests; optional with max length 1000 characters */
    @Size(max = 1000, message = "Interests cannot exceed 1000 characters")
    private final String interests;

    /** Profile photo data */
    private final String profilePhoto;

    /** Whether the user is currently online; cannot be null */
    @NotNull(message = "Online status cannot be null")
    private final Boolean isOnline;

    /** Preferred type of relationship; must be provided */
    @NotNull(message = "Preferred relationship cannot be null")
    private final PreferredRelationship preferredRelationship;
}
