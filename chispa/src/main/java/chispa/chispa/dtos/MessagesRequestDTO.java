package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessagesRequestDTO {
    private Long matchId;
    private Long senderUserId;
    private String content;
}
