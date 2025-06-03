package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.FavoriteGender;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for user preferences request.
 */
@Data
public class PreferencesRequestDTO {
    /**
     * User to whom these preferences belong
     */
    private final Users user;

    /** Minimum age range */
    @Min(value = 18, message = "La edad mínima debe ser 18")
    @Max(value = 100, message = "La edad máxima debe ser 100")
    private final Integer minAgeRange;

    /** Maximum age range */
    @Min(value = 18, message = "La edad mínima debe ser 18")
    @Max(value = 100, message = "La edad máxima debe ser 100")
    private final Integer maxAgeRange;

    /** Maximum distance (in km, for example) */
    @Min(value = 1, message = "La distancia máxima debe ser al menos 1")
    private final Integer maxDistance;

    /** Preferred gender */
    @NotNull(message = "El género favorito no puede estar vacío")
    private final FavoriteGender favoriteGender;
}
