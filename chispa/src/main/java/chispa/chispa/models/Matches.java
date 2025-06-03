package chispa.chispa.models;

import chispa.chispa.models.enums.MatchState;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * Entity representing a match between two users.
 */
@Entity
@Getter
@Setter
@ToString(exclude = {"user1", "user2"})
@AllArgsConstructor
@NoArgsConstructor
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // First user in the match
    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private Users user1;

    // Second user in the match
    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private Users user2;

    // Date of match creation
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime matchDate;

    // Status of the match
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchState matchState;

    // Default match state before persisting
    @PrePersist
    public void prePersist() {
        if (matchState == null) {
            matchState = MatchState.MATCHED;
        }
    }
}
