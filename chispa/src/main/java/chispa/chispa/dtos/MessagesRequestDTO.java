package chispa.chispa.dtos;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Users;
import lombok.Data;

@Data
public class MessagesRequestDTO {
    private final Matches match;
    private final Users senderUser;
    private final String content;
    private final Integer isRead;
}
