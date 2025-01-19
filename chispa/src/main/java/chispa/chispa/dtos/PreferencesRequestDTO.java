package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import lombok.Data;

@Data
public class PreferencesRequestDTO {
    private final Users user;
    private final Integer minAgeRange;
    private final Integer maxAgeRange;
    private final Integer maxDistance;
    private final String favoriteGender; //Enum: 'male', 'female', 'other'
}
