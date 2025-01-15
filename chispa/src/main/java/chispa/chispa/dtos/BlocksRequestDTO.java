package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlocksRequestDTO {
    private Long reporterId;
    private Long reportedId;
    private String blockReason;
}
