package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import lombok.Data;

@Data
public class LikesRequestDTO {
    private final Users liker;
    private final Users liked;
    private final String state; // ENUM: 'pending', 'accepted', 'rejected'

}
