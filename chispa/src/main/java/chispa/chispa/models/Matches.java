package chispa.chispa.models;

import chispa.chispa.models.enums.MatchState;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users user1;
    @ManyToOne
    private Users user2;
    @CreatedDate
    private LocalDate matchDate;
    @Enumerated(EnumType.STRING)
    private MatchState matchState;
}
