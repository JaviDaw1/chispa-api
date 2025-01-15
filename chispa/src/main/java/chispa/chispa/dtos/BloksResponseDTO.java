package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BloksResponseDTO {
    private Long id;
    private Long reporterId;
    private Long reportedId;
    private String blockDate;
    private String blockReason;
}
