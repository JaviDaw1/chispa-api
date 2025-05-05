package chispa.chispa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = {"match", "senderUser", "receiverUser"})
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Matches match;

    @ManyToOne
    @JoinColumn(name = "senderUser_id", nullable = false)
    private Users senderUser;

    @ManyToOne
    @JoinColumn(name = "receiverUser_id", nullable = false)
    private Users receiverUser;

    @NotBlank(message = "El contenido del mensaje no puede estar vacío")
    @Size(max = 2000, message = "El contenido del mensaje no puede tener más de 2000 caracteres")
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private Boolean isRead;
}
