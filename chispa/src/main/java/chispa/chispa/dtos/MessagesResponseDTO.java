package chispa.chispa.dtos;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MessagesResponseDTO {
    private final Long id;
    private final Matches match;
    private final Users senderUser;
    private final String content;
    private final LocalDate timestamp;
    private final Integer isRead;
}
