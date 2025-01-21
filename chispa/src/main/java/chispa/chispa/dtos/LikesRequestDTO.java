package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.LikeState;
import lombok.Data;

@Data
public class LikesRequestDTO {
    private final Users liker;
    private final Users liked;
    private final LikeState state; // ENUM: 'pending', 'accepted', 'rejected'

}
