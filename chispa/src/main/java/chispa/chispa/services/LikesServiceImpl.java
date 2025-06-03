package chispa.chispa.services;

import chispa.chispa.models.Likes;
import chispa.chispa.models.Matches;
import chispa.chispa.models.enums.MatchState;
import chispa.chispa.repositories.LikesRepository;
import chispa.chispa.repositories.MatchesRepository;
import chispa.chispa.repositories.UsersDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the LikesService interface.
 * Handles business logic for user like operations and match creation.
 */
@Transactional
@Service
@AllArgsConstructor
public class LikesServiceImpl implements LikesService {
    // Repository dependencies
    private final LikesRepository likesRepository;
    private final UsersDetailsRepository usersRepository;
    private final MatchesRepository matchesRepository;

    @Override
    public List<Likes> findAll() {
        return likesRepository.findAll();
    }

    @Override
    public Likes findById(Long id) {
        // Find like or throw exception if not found
        return likesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Likes not found"));
    }

    @Override
    public void deleteById(Long id) {
        likesRepository.deleteById(id);
    }

    @Override
    public void deleteLikeByLikerIdAndLikedId(Long likerId, Long likedId) {
        likesRepository.deleteLikesByLikerIdAndLikedId(likerId, likedId);
    }

    @Override
    public Likes update(Long id, Likes like) {
        // Find existing like and update its fields
        Likes updated = this.findById(id);
        updated.setLiker(like.getLiker());
        updated.setLiked(like.getLiked());
        updated.setTimestamp(LocalDateTime.now());
        updated.setState(like.getState());
        return likesRepository.save(updated);
    }

    @Override
    public Likes patch(Long id, Likes like) {
        // Find like to patch and update only non-null fields
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
    public List<Likes> findByLikerId(Long likerId) {
        return likesRepository.findByLikerId(likerId);
    }

    @Override
    public List<Likes> findByLikedId(Long likedId) {
        return likesRepository.findByLikedId(likedId);
    }

    @Override
    public Likes save(Likes like) {
        // Check for reciprocal like to potentially create a match
        Optional<Likes> reciprocalLike = likesRepository.findByLikerIdAndLikedId(
                like.getLiked().getId(),
                like.getLiker().getId()
        );

        // Validate users exist
        like.setLiker(usersRepository.findById(like.getLiker().getId()).get());
        like.setLiked(usersRepository.findById(like.getLiked().getId()).get());
        Likes savedLike = likesRepository.save(like);

        // If reciprocal like exists, create a match
        if (reciprocalLike.isPresent()) {
            createMatch(like.getLiker().getId(), like.getLiked().getId());
        }

        return savedLike;
    }

    /**
     * Creates a match between two users if one doesn't already exist.
     *
     * @param user1Id the first user ID
     * @param user2Id the second user ID
     */
    private void createMatch(Long user1Id, Long user2Id) {
        // Check if match already exists between these users
        boolean matchExists = matchesRepository.existsMatchBetweenUsers(user1Id, user2Id);

        if (!matchExists) {
            // Create new match
            Matches match = new Matches();
            match.setUser1(usersRepository.findById(user1Id).get());
            match.setUser2(usersRepository.findById(user2Id).get());
            match.setMatchDate(LocalDateTime.now());
            match.setMatchState(MatchState.MATCHED);
            matchesRepository.save(match);
        }
    }
}