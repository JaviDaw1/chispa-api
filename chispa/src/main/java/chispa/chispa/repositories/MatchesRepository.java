package chispa.chispa.repositories;

import chispa.chispa.models.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchesRepository extends JpaRepository<Matches, Long> {
    List<Matches> findByMatchState(String matchState);
    List<Matches> findByUser1IdAndUser2Id(Long user1Id, Long user2Id);
}
