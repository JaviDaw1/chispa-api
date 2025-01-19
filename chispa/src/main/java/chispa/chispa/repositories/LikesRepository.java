package chispa.chispa.repositories;

import chispa.chispa.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByLikerIdAndLikedId(Long likerId, Long likedId);
}
