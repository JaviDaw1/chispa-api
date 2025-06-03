package chispa.chispa.services;

import chispa.chispa.models.Matches;
import chispa.chispa.models.enums.MatchState;
import java.util.List;

/**
 * Service interface for managing matches between users.
 * Provides methods for CRUD operations and querying matches.
 */
public interface MatchesService {
    /**
     * Finds a match by its ID.
     *
     * @param id the match ID
     * @return the found match
     */
    Matches findById(Long id);

    /**
     * Saves a new match.
     * @param match the match to save
     * @return the saved match
     */
    Matches save(Matches match);

    /**
     * Updates an existing match.
     * @param id the ID of the match to update
     * @param match the updated match data
     * @return the updated match
     */
    Matches update(Long id, Matches match);

    /**
     * Deletes a match by its ID.
     * @param id the match ID to delete
     */
    void deleteById(Long id);

    /**
     * Retrieves all matches.
     * @return list of all matches
     */
    List<Matches> findAll();

    /**
     * Finds all matches involving the given user.
     * @param userId the user ID
     * @return list of matches
     */
    List<Matches> findByUserId(Long userId);

    /**
     * Finds all matches with the given state.
     * @param matchState the match state to filter by
     * @return list of matches
     */
    List<Matches> findMatchesByMatchState(MatchState matchState);

    /**
     * Counts matches with the given state.
     * @param matchState the match state to filter by
     * @return count of matches
     */
    Long countMatchesByMatchState(MatchState matchState);

    /**
     * Partially updates a match.
     * @param id the match ID to update
     * @param matches the match data with fields to update
     * @return the patched match
     */
    Matches patch(Long id, Matches matches);
}