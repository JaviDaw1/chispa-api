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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class LikesServiceImpl implements LikesService {
    private final LikesRepository likesRepository;
    private final UsersDetailsRepository usersRepository;
    private final MatchesRepository matchesRepository;

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
    public void deleteLikeByLikerIdAndLikedId(Long likerId, Long likedId) {
        likesRepository.deleteLikesByLikerIdAndLikedId(likerId, likedId);
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
    public Long countTotalLikes() {
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

    @Override
    public Likes save(Likes like) {
        // Verificar si ya existe un like recíproco
        Optional<Likes> reciprocalLike = likesRepository.findByLikerIdAndLikedId(
                like.getLiked().getId(),
                like.getLiker().getId()
        );

        like.setLiker(usersRepository.findById(like.getLiker().getId()).get());
        like.setLiked(usersRepository.findById(like.getLiked().getId()).get());
        Likes savedLike = likesRepository.save(like);

        // Si existe un like recíproco, crear un match
        if (reciprocalLike.isPresent()) {
            createMatch(like.getLiker().getId(), like.getLiked().getId());
        }

        return savedLike;
    }

    private void createMatch(Long user1Id, Long user2Id) {
        // Verificar si ya existe un match entre estos usuarios (en cualquier orden)
        boolean matchExists = matchesRepository.existsMatchBetweenUsers(user1Id, user2Id);

        if (!matchExists) {
            Matches match = new Matches();
            // No necesitas ordenar los IDs porque el método existsMatchBetweenUsers
            // ya verifica en ambos sentidos
            match.setUser1(usersRepository.findById(user1Id).get());
            match.setUser2(usersRepository.findById(user2Id).get());
            match.setMatchDate(LocalDateTime.now());
            match.setMatchState(MatchState.PENDING);
            matchesRepository.save(match);
        }
    }

    // Añade este método al repositorio de Likes
    Optional<Likes> findByLikerIdAndLikedId(Long likerId, Long likedId) {
        return null;
    }
}
