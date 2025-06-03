package chispa.chispa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * Entity representing a block between two users.
 * Used to record when one user blocks another, including the reason.
 */
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

    // The user who is blocking
    @ManyToOne
    @JoinColumn(name = "reporter_id", nullable = false)
    private Users reporter;

    // The user being blocked
    @ManyToOne
    @JoinColumn(name = "reported_id", nullable = false)
    private Users reported;

    // Date when the block was made
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime blockDate;

    // Optional reason for blocking
    @Size(max = 500, message = "The block reason must not exceed 500 characters")
    @Column(nullable = true)
    private String blockReason;
}
