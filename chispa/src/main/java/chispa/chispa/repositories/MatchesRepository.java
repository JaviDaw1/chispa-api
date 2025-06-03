package chispa.chispa.repositories;

import chispa.chispa.models.Matches;
import chispa.chispa.models.enums.MatchState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing user matches.
 */
public interface MatchesRepository extends JpaRepository<Matches, Long> {

    /**
     * Finds all matches in a specific state.
     */
    List<Matches> findMatchesByMatchState(MatchState matchState);

    /**
     * Counts how many matches exist in a specific state.
     */
    Long countByMatchState(MatchState matchState);

    /**
     * Checks if a match exists between two users (in any order).
     */
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Matches m WHERE " +
            "(m.user1.id = :user1Id AND m.user2.id = :user2Id) OR " +
            "(m.user1.id = :user2Id AND m.user2.id = :user1Id)")
    boolean existsMatchBetweenUsers(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);

    /**
     * Gets all matches where a given user is either user1 or user2.
     */
    @Query("SELECT m FROM Matches m WHERE m.user1.id = :userId OR m.user2.id = :userId")
    List<Matches> findByEitherUserId(@Param("userId") Long userId);

    /**
     * Gets a match between two users in a specific order.
     */
    Optional<Matches> findMatchesByUser1IdAndUser2Id(Long user1, Long user2);
}
