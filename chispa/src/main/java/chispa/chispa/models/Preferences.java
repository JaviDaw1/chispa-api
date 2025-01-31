package chispa.chispa.models;

import chispa.chispa.models.enums.FavoriteGender;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Setter
@ToString(exclude = "user")
@AllArgsConstructor
@NoArgsConstructor
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Min(value = 18, message = "La edad mínima debe ser 18")
    @Max(value = 100, message = "La edad máxima debe ser 100")
    @Column(nullable = false)
    private Integer minAgeRange;

    @Min(value = 18, message = "La edad mínima debe ser 18")
    @Max(value = 100, message = "La edad máxima debe ser 100")
    @Column(nullable = false)
    private Integer maxAgeRange;

    @Min(value = 1, message = "La distancia máxima debe ser al menos 1")
    @Column(nullable = false)
    private Integer maxDistance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FavoriteGender favoriteGender;
}
