package chispa.chispa.services;

import chispa.chispa.models.Likes;
import java.util.List;

/**
 * Service interface for managing likes between users.
 * Provides methods for CRUD operations and querying like relationships.
 */
public interface LikesService {
    /**
     * Finds a like by its ID.
     *
     * @param id the like ID
     * @return the found like
     */
    Likes findById(Long id);

    /**
     * Saves a new like.
     * @param like the like to save
     * @return the saved like
     */
    Likes save(Likes like);

    /**
     * Updates an existing like.
     * @param id the ID of the like to update
     * @param like the updated like data
     * @return the updated like
     */
    Likes update(Long id, Likes like);

    /**
     * Deletes a like by its ID.
     * @param id the like ID to delete
     */
    void deleteById(Long id);

    /**
     * Deletes a like by liker and liked user IDs.
     * @param likerId the ID of the user who liked
     * @param likedId the ID of the user who was liked
     */
    void deleteLikeByLikerIdAndLikedId(Long likerId, Long likedId);

    /**
     * Retrieves all likes.
     * @return list of all likes
     */
    List<Likes> findAll();

    /**
     * Counts total likes.
     * @return total count of likes
     */
    Long countLikes();

    /**
     * Partially updates a like.
     * @param id the like ID to update
     * @param like the like data with fields to update
     * @return the patched like
     */
    Likes patch(Long id, Likes like);

    /**
     * Counts total likes by liker ID.
     * @param reporterId the liker user ID
     * @return count of likes
     */
    Long countTotalLikesByLikerId(Long reporterId);

    /**
     * Counts total likes by liked ID.
     * @param reportedId the liked user ID
     * @return count of likes
     */
    Long countTotalLikesByLikedId(Long reportedId);

    /**
     * Finds all likes where the given user is the liker.
     * @param likerId the liker user ID
     * @return list of likes
     */
    List<Likes> findByLikerId(Long likerId);

    /**
     * Finds all likes where the given user is the liked.
     * @param reportedId the liked user ID
     * @return list of likes
     */
    List<Likes> findByLikedId(Long reportedId);
}