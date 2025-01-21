package chispa.chispa.repositories;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Preferences;
import chispa.chispa.models.enums.MatchState;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchesRepository extends JpaRepository<Matches, Long> {
    List<Matches> findMatchesByMatchState(MatchState matchState);

    List<Matches> findByUser1Id(Long user1Id);

    Long countByMatchState(MatchState matchState);
}
