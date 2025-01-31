package chispa.chispa.mappers;

import chispa.chispa.dtos.LikesRequestDTO;
import chispa.chispa.dtos.LikesResponseDTO;
import chispa.chispa.models.Likes;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class LikesMapper {
    public LikesResponseDTO toResponse(Likes like) {
        return new LikesResponseDTO(
                like.getId(),
                like.getLiker(),
                like.getLiked(),
                like.getTimestamp(),
                like.getState()
        );
    }
    public List<LikesResponseDTO> toResponse(List<Likes> likes) {
        return likes.stream()
                .map(this::toResponse)
                .toList();
    }
    public Likes toModel(LikesRequestDTO likeRequestDto) {
        return new Likes(
                null,
                likeRequestDto.getLiker(),
                likeRequestDto.getLiked(),
                LocalDateTime.now(),
                likeRequestDto.getState()
        );
    }
}
