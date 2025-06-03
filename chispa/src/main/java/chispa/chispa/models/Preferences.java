package chispa.chispa.models;

import chispa.chispa.models.enums.FavoriteGender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

/**
 * Entity storing user preferences for potential matches.
 */
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

    // User associated with the preferences
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // Minimum preferred age
    @Min(value = 18, message = "Minimum age must be 18")
    @Max(value = 100, message = "Maximum age must be 100")
    @Column(nullable = false)
    private Integer minAgeRange;

    // Maximum preferred age
    @Min(value = 18, message = "Minimum age must be 18")
    @Max(value = 100, message = "Maximum age must be 100")
    @Column(nullable = false)
    private Integer maxAgeRange;

    // Maximum preferred distance (in km)
    @Min(value = 1, message = "Maximum distance must be at least 1")
    @Column(nullable = false)
    private Integer maxDistance;

    // Preferred gender
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FavoriteGender favoriteGender;
}
