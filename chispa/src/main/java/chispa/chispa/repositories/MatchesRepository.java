package chispa.chispa.repositories;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Preferences;
import chispa.chispa.models.enums.MatchState;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchesRepository extends JpaRepository<Matches, Long> {
    List<Matches> findMatchesByMatchState(MatchState matchState);

    List<Matches> findByUser1Id(Long user1Id);

    Long countByMatchState(MatchState matchState);

    // Método para verificar si existe un match entre dos usuarios
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Matches m WHERE " +
            "(m.user1.id = :user1Id AND m.user2.id = :user2Id) OR " +
            "(m.user1.id = :user2Id AND m.user2.id = :user1Id)")
    boolean existsMatchBetweenUsers(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
}
