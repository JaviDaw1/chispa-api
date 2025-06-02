package chispa.chispa.models;

import chispa.chispa.models.enums.LikeState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "liker_id", nullable = false)
    private Users liker;

    @ManyToOne
    @JoinColumn(name = "liked_id", nullable = false)
    private Users liked;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LikeState state;

    @PrePersist
    public void prePersist() {
        if (state == null) {
            state = LikeState.LIKED;
        }
    }
}