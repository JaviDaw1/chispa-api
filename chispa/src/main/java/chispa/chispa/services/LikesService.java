package chispa.chispa.services;

import chispa.chispa.models.Likes;

import java.util.List;

public interface LikesService {
    Likes findById(Long id);

    Likes save(Likes like);

    Likes update(Long id, Likes like);

    void deleteById(Long id);

    void deleteLikeByLikerIdAndLikedId(Long likerId, Long likedId);

    List<Likes> findAll();

    Long countLikes();

    Likes patch(Long id, Likes like);

    Long countTotalLikesByLikerId(Long reporterId);

    Long countTotalLikesByLikedId(Long reportedId);

    Long countTotalLikes();

    List<Likes> findByLikerId(Long likerId);

    List<Likes> findByLikedId(Long reportedId);
}
