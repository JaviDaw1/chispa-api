package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferencesResponseDTO {
    private Long id;
    private Long userId;
    private Integer minAgeRange;
    private Integer maxAgeRange;
    private Integer maxDistance;
    private String favoriteGender;

}
