package chispa.chispa.models;

import chispa.chispa.models.enums.MatchState;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private Users user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private Users user2;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime matchDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchState matchState;
}
