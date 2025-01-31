package chispa.chispa.controllers;

import chispa.chispa.dtos.LikesRequestDTO;
import chispa.chispa.dtos.LikesResponseDTO;
import chispa.chispa.mappers.LikesMapper;
import chispa.chispa.models.Likes;
import chispa.chispa.services.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class LikesController {
    private final LikesService likesService;
    private final LikesMapper likesMapper;

    @GetMapping
    public ResponseEntity<List<LikesResponseDTO>> getAllLikes() {
        log.info("getAllLikes");
        return ResponseEntity.ok(likesMapper.toResponse(likesService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LikesResponseDTO> getLikeById(@PathVariable Long id) {
        log.info("getLikeById");
        return ResponseEntity.ok(likesMapper.toResponse(likesService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<LikesResponseDTO> postLike(@RequestBody LikesRequestDTO likesRequestDto) {
        log.info("addLike");
        Likes likeSaved = likesService.save(likesMapper.toModel(likesRequestDto));
        return ResponseEntity.created(null).body(likesMapper.toResponse(likeSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LikesResponseDTO> putLike(@PathVariable Long id, @RequestBody LikesRequestDTO likesRequestDto) {
        log.info("putLike");
        Likes likeUpdated = likesService.update(id, likesMapper.toModel(likesRequestDto));
        return ResponseEntity.ok(likesMapper.toResponse(likeUpdated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LikesResponseDTO> patchLike(@PathVariable Long id, @RequestBody LikesRequestDTO likesRequestDto) {
        log.info("patchLike");
        Likes likePatched = likesService.patch(id, likesMapper.toModel(likesRequestDto));
        return ResponseEntity.ok(likesMapper.toResponse(likePatched));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        log.info("deleteLike");
        likesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/liker/{likerId}")
    public ResponseEntity<List<LikesResponseDTO>> getLikesByLikerId(@PathVariable Long likerId) {
        log.info("getLikesByLikerId");
        return ResponseEntity.ok(likesMapper.toResponse(likesService.findByLikerId(likerId)));
    }

    @GetMapping("/liked/{likedId}")
    public ResponseEntity<List<LikesResponseDTO>> getLikesByLikedId(@PathVariable Long likedId) {
        log.info("getLikesByLikedId");
        return ResponseEntity.ok(likesMapper.toResponse(likesService.findByLikedId(likedId)));
    }

    @GetMapping("/count/liker/{likerId}")
    public ResponseEntity<Long> countLikesByLikerId(@PathVariable Long likerId) {
        log.info("countLikesByLikerId");
        return ResponseEntity.ok(likesService.countTotalLikesByLikerId(likerId));
    }

    @GetMapping("/count/liked/{likedId}")
    public ResponseEntity<Long> countLikesByLikedId(@PathVariable Long likedId) {
        log.info("countLikesByLikedId");
        return ResponseEntity.ok(likesService.countTotalLikesByLikedId(likedId));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countTotalLikes() {
        log.info("countTotalLikes");
        return ResponseEntity.ok(likesService.countLikes());
    }
}