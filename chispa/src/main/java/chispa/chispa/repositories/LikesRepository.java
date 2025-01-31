package chispa.chispa.repositories;

import chispa.chispa.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByLikerIdAndLikedId(Long likerId, Long likedId);

    Long countLikesByLikerId(Long reporterId);

    List<Likes> findByLikerId(Long likerId);

    Long countLikesByLikedId(Long reportedId);

    List<Likes> findByLikedId(Long reportedId);
}
