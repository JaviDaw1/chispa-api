package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikesResponseDTO {
    private Long id;
    private Long likerId;
    private Long likedId;
    private String timestamp;
    private String state;
}
