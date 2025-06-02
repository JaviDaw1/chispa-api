package chispa.chispa.dtos;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Users;
import chispa.chispa.models.enums.MessageState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessagesResponseDTO {
    private final Long id;
    private final Matches match;
    private final Users senderUser;
    private final Users receiverUser;
    private final String content;
    private final LocalDateTime timestamp;
    private final Boolean isRead;
    private final MessageState messageState;
}
