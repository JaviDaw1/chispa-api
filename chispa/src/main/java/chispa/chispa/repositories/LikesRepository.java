package chispa.chispa.repositories;

import chispa.chispa.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing "like" interactions between users.
 */
public interface LikesRepository extends JpaRepository<Likes, Long> {

    /**
     * Finds a like made by one user to another.
     */
    Optional<Likes> findByLikerIdAndLikedId(Long likerId, Long likedId);

    /**
     * Counts how many likes a user has given.
     */
    Long countLikesByLikerId(Long likerId);

    /**
     * Gets all likes made by a user.
     */
    List<Likes> findByLikerId(Long likerId);

    /**
     * Counts how many likes a user has received.
     */
    Long countLikesByLikedId(Long likedId);

    /**
     * Gets all likes received by a user.
     */
    List<Likes> findByLikedId(Long likedId);

    /**
     * Deletes a specific like between two users.
     */
    void deleteLikesByLikerIdAndLikedId(Long likerId, Long likedId);
}
