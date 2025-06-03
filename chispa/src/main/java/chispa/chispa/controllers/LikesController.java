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

/**
 * Controller for managing Like operations via REST API.
 * Handles CRUD operations and queries for likes between users.
 */
@RestController
@RequestMapping("/api/likes")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class LikesController {
    // Service for Like business logic
    private final LikesService likesService;
    // Mapper for converting between entity and DTO
    private final LikesMapper likesMapper;

    /**
     * Get all likes in the system.
     *
     * @return List of LikesResponseDTO
     */
    @GetMapping
    public ResponseEntity<List<LikesResponseDTO>> getAllLikes() {
        log.info("getAllLikes");
        return ResponseEntity.ok(likesMapper.toResponse(likesService.findAll()));
    }

    /**
     * Get a like by its ID.
     * @param id Like ID
     * @return LikesResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<LikesResponseDTO> getLikeById(@PathVariable Long id) {
        log.info("getLikeById");
        return ResponseEntity.ok(likesMapper.toResponse(likesService.findById(id)));
    }

    /**
     * Create a new like.
     * @param likesRequestDto Like data
     * @return Created LikesResponseDTO
     */
    @PostMapping
    public ResponseEntity<LikesResponseDTO> postLike(@RequestBody LikesRequestDTO likesRequestDto) {
        log.info("addLike");
        Likes likeSaved = likesService.save(likesMapper.toModel(likesRequestDto));
        return ResponseEntity.created(null).body(likesMapper.toResponse(likeSaved));
    }

    /**
     * Update a like by its ID.
     * @param id Like ID
     * @param likesRequestDto Updated data
     * @return Updated LikesResponseDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<LikesResponseDTO> putLike(@PathVariable Long id, @RequestBody LikesRequestDTO likesRequestDto) {
        log.info("putLike");
        Likes likeUpdated = likesService.update(id, likesMapper.toModel(likesRequestDto));
        return ResponseEntity.ok(likesMapper.toResponse(likeUpdated));
    }

    /**
     * Patch a like by its ID.
     * @param id Like ID
     * @param likesRequestDto Partial data
     * @return Patched LikesResponseDTO
     */
    @PatchMapping("/{id}")
    public ResponseEntity<LikesResponseDTO> patchLike(@PathVariable Long id, @RequestBody LikesRequestDTO likesRequestDto) {
        log.info("patchLike");
        Likes likePatched = likesService.patch(id, likesMapper.toModel(likesRequestDto));
        return ResponseEntity.ok(likesMapper.toResponse(likePatched));
    }

    /**
     * Delete a like by its ID.
     * @param id Like ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        log.info("deleteLike");
        likesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a like by liker and liked user IDs.
     * @param likerId Liker user ID
     * @param likedId Liked user ID
     */
    @DeleteMapping("/liker/{likerId}/liked/{likedId}")
    public ResponseEntity<Void> deleteLikeLikerIdAndLikedId(@PathVariable Long likerId, @PathVariable Long likedId) {
        log.info("deleteLikeLikerIdAndLikedId");
        likesService.deleteLikeByLikerIdAndLikedId(likerId, likedId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all likes made by a specific user.
     * @param likerId Liker user ID
     * @return List of LikesResponseDTO
     */
    @GetMapping("/liker/{likerId}")
    public ResponseEntity<List<LikesResponseDTO>> getLikesByLikerId(@PathVariable Long likerId) {
        log.info("getLikesByLikerId");
        return ResponseEntity.ok(likesMapper.toResponse(likesService.findByLikerId(likerId)));
    }

    /**
     * Get all likes received by a specific user.
     * @param likedId Liked user ID
     * @return List of LikesResponseDTO
     */
    @GetMapping("/liked/{likedId}")
    public ResponseEntity<List<LikesResponseDTO>> getLikesByLikedId(@PathVariable Long likedId) {
        log.info("getLikesByLikedId");
        return ResponseEntity.ok(likesMapper.toResponse(likesService.findByLikedId(likedId)));
    }

    /**
     * Count likes made by a specific user.
     * @param likerId Liker user ID
     * @return Number of likes
     */
    @GetMapping("/count/liker/{likerId}")
    public ResponseEntity<Long> countLikesByLikerId(@PathVariable Long likerId) {
        log.info("countLikesByLikerId");
        return ResponseEntity.ok(likesService.countTotalLikesByLikerId(likerId));
    }

    /**
     * Count likes received by a specific user.
     * @param likedId Liked user ID
     * @return Number of likes
     */
    @GetMapping("/count/liked/{likedId}")
    public ResponseEntity<Long> countLikesByLikedId(@PathVariable Long likedId) {
        log.info("countLikesByLikedId");
        return ResponseEntity.ok(likesService.countTotalLikesByLikedId(likedId));
    }

    /**
     * Count all likes in the system.
     * @return Number of likes
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countTotalLikes() {
        log.info("countTotalLikes");
        return ResponseEntity.ok(likesService.countLikes());
    }
}

