package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.FavoriteGender;
import lombok.Data;

/**
 * DTO for user preferences response.
 */
@Data
public class PreferencesResponseDTO {
    /**
     * Unique identifier
     */
    private final Long id;

    /** User to whom these preferences belong */
    private final Users user;

    /** Minimum age range */
    private final Integer minAgeRange;

    /** Maximum age range */
    private final Integer maxAgeRange;

    /** Maximum distance */
    private final Integer maxDistance;

    /** Preferred gender */
    private final FavoriteGender favoriteGender;
}
