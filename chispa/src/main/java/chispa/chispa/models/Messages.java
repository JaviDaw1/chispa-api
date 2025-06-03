package chispa.chispa.models;

import chispa.chispa.models.enums.MessageState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity representing a message sent between matched users.
 */
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

    // Match associated with the message
    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Matches match;

    // User sending the message
    @ManyToOne
    @JoinColumn(name = "senderUser_id", nullable = false)
    private Users senderUser;

    // User receiving the message
    @ManyToOne
    @JoinColumn(name = "receiverUser_id", nullable = false)
    private Users receiverUser;

    // Message content
    @NotBlank(message = "Message content cannot be blank")
    @Size(max = 2000, message = "Message content must not exceed 2000 characters")
    private String content;

    // Time message was sent
    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Whether the message has been read
    @Column(nullable = false)
    private Boolean isRead;

    // State of the message
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageState messageState;

    // Default message state before persisting
    @PrePersist
    public void prePersist() {
        if (messageState == null) {
            messageState = MessageState.SEND;
        }
    }
}
