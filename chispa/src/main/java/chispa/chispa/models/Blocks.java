package chispa.chispa.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = {"reporter", "reported"})
@NoArgsConstructor
@AllArgsConstructor
public class Blocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reporter_id", nullable = false)
    private Users reporter;

    @ManyToOne
    @JoinColumn(name = "reported_id", nullable = false)
    private Users reported;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime blockDate;

    @Size(max = 500, message = "El motivo del bloqueo no puede tener más de 500 caracteres")
    @Column(nullable = true)
    private String blockReason;
}
