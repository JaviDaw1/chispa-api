package chispa.chispa.mappers;

import chispa.chispa.dtos.LikesRequestDTO;
import chispa.chispa.dtos.LikesResponseDTO;
import chispa.chispa.models.Likes;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapper class for converting between Likes entity and DTOs.
 */
@Component
public class LikesMapper {

    /**
     * Converts a Likes entity to a LikesResponseDTO.
     *
     * @param like the Likes entity to convert
     * @return the corresponding LikesResponseDTO
     */
    public LikesResponseDTO toResponse(Likes like) {
        // Map entity fields to response DTO
        return new LikesResponseDTO(
                like.getId(),
                like.getLiker(),
                like.getLiked(),
                like.getTimestamp(),
                like.getState()
        );
    }

    /**
     * Converts a list of Likes entities to a list of LikesResponseDTOs.
     * @param likes list of Likes entities
     * @return list of LikesResponseDTOs
     */
    public List<LikesResponseDTO> toResponse(List<Likes> likes) {
        // Stream the list and map each Likes entity to DTO
        return likes.stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Converts a LikesRequestDTO to a Likes entity.
     * @param likeRequestDto the request DTO to convert
     * @return the new Likes entity
     */
    public Likes toModel(LikesRequestDTO likeRequestDto) {
        // Create new Likes entity with current timestamp
        return new Likes(
                null,
                likeRequestDto.getLiker(),
                likeRequestDto.getLiked(),
                LocalDateTime.now(),
                likeRequestDto.getState()
        );
    }
}
