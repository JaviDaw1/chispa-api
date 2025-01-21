package chispa.chispa.models;

import chispa.chispa.models.enums.LikeState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users liker;
    @ManyToOne
    private Users liked;
    private LocalDate timestamp;
    @Enumerated(EnumType.STRING)
    private LikeState state;
}