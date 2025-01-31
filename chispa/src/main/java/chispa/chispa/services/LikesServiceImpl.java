package chispa.chispa.services;

import chispa.chispa.models.Likes;
import chispa.chispa.repositories.LikesRepository;
import chispa.chispa.repositories.UsersDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class LikesServiceImpl implements LikesService {
    private final LikesRepository likesRepository;
    private final UsersDetailsRepository usersRepository;

    @Override
    public List<Likes> findAll() {
        return likesRepository.findAll();
    }

    @Override
    public Likes findById(Long id) {
        return likesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Likes not found"));
    }

    @Override
    public void deleteById(Long id) {
        likesRepository.deleteById(id);
    }

    @Override
    public Likes save(Likes like) {
        like.setLiker(usersRepository.findById(like.getLiker().getId()).get());
        like.setLiked(usersRepository.findById(like.getLiked().getId()).get());
        return likesRepository.save(like);
    }

    @Override
    public Likes update(Long id, Likes like) {
        Likes updated = this.findById(id);
        updated.setLiker(like.getLiker());
        updated.setLiked(like.getLiked());
        updated.setTimestamp(LocalDateTime.now());
        updated.setState(like.getState());
        return likesRepository.save(updated);
    }

    @Override
    public Likes patch(Long id, Likes like) {
        Likes likeToPatch = likesRepository.findById(id).orElseThrow();
        if (like.getLiker() != null) {
            likeToPatch.setLiker(like.getLiker());
        }
        if (like.getLiked() != null) {
            likeToPatch.setLiked(like.getLiked());
        }
        if (like.getTimestamp() != null) {
            likeToPatch.setTimestamp(LocalDateTime.now());
        }
        if (like.getState() != null) {
            likeToPatch.setState(like.getState());
        }
        return likesRepository.save(likeToPatch);
    }

    @Override
    public Long countLikes() {
        return likesRepository.count();
    }

    @Override
    public Long countTotalLikesByLikerId(Long likerId) {
        return likesRepository.countLikesByLikerId(likerId);
    }

    @Override
    public Long countTotalLikesByLikedId(Long likedId) {
        return likesRepository.countLikesByLikedId(likedId);
    }

    @Override
    public Long countTotalLikes(){
        return likesRepository.count();
    }

    @Override
    public List<Likes> findByLikerId(Long likerId) {
        return likesRepository.findByLikerId(likerId);
    }

    @Override
    public List<Likes> findByLikedId(Long likedId) {
        return likesRepository.findByLikedId(likedId);
    }
}
