package chispa.chispa.models;

import chispa.chispa.models.enums.LikeState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity representing a like between two users.
 * Includes the state and timestamp of the like.
 */
@Entity
@Getter
@Setter
@ToString(exclude = {"liker", "liked"})
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who likes
    @ManyToOne
    @JoinColumn(name = "liker_id", nullable = false)
    private Users liker;

    // User being liked
    @ManyToOne
    @JoinColumn(name = "liked_id", nullable = false)
    private Users liked;

    // Time the like was made
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    // State of the like (LIKED, DISLIKED, etc.)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LikeState state;

    // Default state before persisting
    @PrePersist
    public void prePersist() {
        if (state == null) {
            state = LikeState.LIKED;
        }
    }
}
