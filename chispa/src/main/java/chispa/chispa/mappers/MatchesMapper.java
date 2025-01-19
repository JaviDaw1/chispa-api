package chispa.chispa.mappers;

import chispa.chispa.dtos.MatchesRequestDTO;
import chispa.chispa.dtos.MatchesResponseDTO;
import chispa.chispa.models.Matches;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MatchesMapper {
    public MatchesResponseDTO toResponse(Matches match) {
        return new MatchesResponseDTO(
                match.getId(),
                match.getUser1(),
                match.getUser2(),
                match.getMatchDate(),
                match.getMatchState()
        );
    }
    public List<MatchesResponseDTO> toResponse(List<Matches> matches) {
        return matches.stream()
                .map(this::toResponse)
                .toList();
    }
    public Matches toModel(MatchesRequestDTO matchRequestDto) {
        return new Matches(
                null,
                matchRequestDto.getUser1(),
                matchRequestDto.getUser2(),
                LocalDate.now(),
                matchRequestDto.getMatchState()
        );
    }
}
