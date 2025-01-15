package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferencesRequestDTO {
    private Integer minAgeRange;
    private Integer maxAgeRange;
    private Integer maxDistance;
    private String favoriteGender; //Enum: 'male', 'female', 'other'
}
