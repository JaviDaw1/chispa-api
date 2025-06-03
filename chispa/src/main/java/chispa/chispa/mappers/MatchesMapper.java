package chispa.chispa.mappers;

import chispa.chispa.dtos.MatchesRequestDTO;
import chispa.chispa.dtos.MatchesResponseDTO;
import chispa.chispa.models.Matches;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapper class for converting between Matches entity and DTOs.
 */
@Component
public class MatchesMapper {

    /**
     * Converts a Matches entity to a MatchesResponseDTO.
     *
     * @param match the Matches entity to convert
     * @return the corresponding MatchesResponseDTO
     */
    public MatchesResponseDTO toResponse(Matches match) {
        // Map entity fields to response DTO
        return new MatchesResponseDTO(
                match.getId(),
                match.getUser1(),
                match.getUser2(),
                match.getMatchDate(),
                match.getMatchState()
        );
    }

    /**
     * Converts a list of Matches entities to a list of MatchesResponseDTOs.
     * @param matches list of Matches entities
     * @return list of MatchesResponseDTOs
     */
    public List<MatchesResponseDTO> toResponse(List<Matches> matches) {
        // Stream the list and map each Matches entity to DTO
        return matches.stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Converts a MatchesRequestDTO to a Matches entity.
     * @param matchRequestDto the request DTO to convert
     * @return the new Matches entity
     */
    public Matches toModel(MatchesRequestDTO matchRequestDto) {
        // Create new Matches entity with current timestamp
        return new Matches(
                null,
                matchRequestDto.getUser1(),
                matchRequestDto.getUser2(),
                LocalDateTime.now(),
                matchRequestDto.getMatchState()
        );
    }
}
