package chispa.chispa.services;

import chispa.chispa.models.Likes;
import chispa.chispa.models.Users;

import java.util.List;

public interface LikeService {
    Likes findById(Long id);

    Likes save(Likes like);

    Likes update(Long id, Likes like);

    void deleteById(Long id);

    List<Likes> findAll();

    List<Likes> findByUserId(Long userId);

    Long countLikes();

    List<Users> getUsersWhoLike(Long userId);
}
