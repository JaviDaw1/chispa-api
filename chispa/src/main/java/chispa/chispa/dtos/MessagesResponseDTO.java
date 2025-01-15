package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessagesResponseDTO {
    private Long id;
    private Long matchId;
    private Long senderUserId;
    private String content;
    private String timestamp;
    private Boolean isRead;
}
