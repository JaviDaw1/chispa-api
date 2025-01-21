package chispa.chispa.models;

import chispa.chispa.models.enums.FavoriteGender;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users user;
    private Integer minAgeRange;
    private Integer maxAgeRange;
    private Integer maxDistance;
    @Enumerated(EnumType.STRING)
    private FavoriteGender favoriteGender;
}
