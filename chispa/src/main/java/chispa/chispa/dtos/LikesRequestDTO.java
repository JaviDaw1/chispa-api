package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikesRequestDTO {
    private Long likerId;
    private Long likedId;
    private String state; // ENUM: 'pending', 'accepted', 'rejected'

}
