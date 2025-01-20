package chispa.chispa.repositories;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchesRepository extends JpaRepository<Matches, Long> {
    List<Matches> findByMatchState(String matchState);

    List<Matches> findByUser1IdAndUser2Id(Long user1Id, Long user2Id);

    List<Matches> findByUser1Id(Long user1Id);

    Long countByState(String state);
}
