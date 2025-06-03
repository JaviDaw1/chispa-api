package chispa.chispa.dtos;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Users;
import chispa.chispa.models.enums.MessageState;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for message response data.
 */
@Data
public class MessagesResponseDTO {
    /**
     * Unique identifier
     */
    private final Long id;

    /** Match to which this message belongs */
    private final Matches match;

    /** User who sent the message */
    private final Users senderUser;

    /** User who received the message */
    private final Users receiverUser;

    /** Message content */
    private final String content;

    /** Date and time when the message was sent */
    private final LocalDateTime timestamp;

    /** Whether the message has been read */
    private final Boolean isRead;

    /** State of the message */
    private final MessageState messageState;
}
