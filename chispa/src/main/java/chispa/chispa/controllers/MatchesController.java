package chispa.chispa.controllers;

import chispa.chispa.dtos.MatchesRequestDTO;
import chispa.chispa.dtos.MatchesResponseDTO;
import chispa.chispa.models.enums.MatchState;
import chispa.chispa.mappers.MatchesMapper;
import chispa.chispa.models.Matches;
import chispa.chispa.services.MatchesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Match operations via REST API.
 * Handles CRUD operations and queries for matches between users.
 */
@RestController
@RequestMapping("/api/matches")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class MatchesController {
    // Service for Match business logic
    private final MatchesService matchesService;
    // Mapper for converting between entity and DTO
    private final MatchesMapper matchesMapper;

    /**
     * Get all matches in the system.
     *
     * @return List of MatchesResponseDTO
     */
    @GetMapping("")
    public ResponseEntity<List<MatchesResponseDTO>> getAllMatches() {
        log.info("getAllMatches");
        return ResponseEntity.ok(matchesMapper.toResponse(matchesService.findAll()));
    }

    /**
     * Get a match by its ID.
     * @param id Match ID
     * @return MatchesResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<MatchesResponseDTO> getMatchById(@PathVariable Long id) {
        log.info("getMatchById");
        return ResponseEntity.ok(matchesMapper.toResponse(matchesService.findById(id)));
    }

    /**
     * Create a new match.
     * @param matchesRequestDto Match data
     * @return Created MatchesResponseDTO
     */
    @PostMapping
    public ResponseEntity<MatchesResponseDTO> postMatch(@RequestBody MatchesRequestDTO matchesRequestDto) {
        log.info("addMatch");
        Matches matchSaved = matchesService.save(matchesMapper.toModel(matchesRequestDto));
        return ResponseEntity.created(null).body(matchesMapper.toResponse(matchSaved));
    }

    /**
     * Update a match by its ID.
     * @param id Match ID
     * @param matchesRequestDto Updated data
     * @return Updated MatchesResponseDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<MatchesResponseDTO> putMatch(@PathVariable Long id, @RequestBody MatchesRequestDTO matchesRequestDto) {
        log.info("putMatch");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        var match = matchesService.findById(id);
        if (!match.getUser1().getEmail().equals(username) && !match.getUser2().getEmail().equals(username) && !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).build();
        }
        Matches matchUpdated = matchesService.update(id, matchesMapper.toModel(matchesRequestDto));
        return ResponseEntity.ok(matchesMapper.toResponse(matchUpdated));
    }

    /**
     * Patch a match by its ID.
     * @param id Match ID
     * @param matchesRequestDto Partial data
     * @return Patched MatchesResponseDTO
     */
    @PatchMapping("/{id}")
    public ResponseEntity<MatchesResponseDTO> patchMatch(@PathVariable Long id, @RequestBody MatchesRequestDTO matchesRequestDto) {
        log.info("patchMatch");
        Matches matchPatched = matchesService.patch(id, matchesMapper.toModel(matchesRequestDto));
        return ResponseEntity.ok(matchesMapper.toResponse(matchPatched));
    }

    /**
     * Delete a match by its ID.
     * @param id Match ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        log.info("deleteMatch");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        var match = matchesService.findById(id);
        if (!match.getUser1().getEmail().equals(username) && !match.getUser2().getEmail().equals(username) && !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).build();
        }
        matchesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all matches for a specific user.
     * @param userId User ID
     * @return List of MatchesResponseDTO
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MatchesResponseDTO>> getMatchesByUserId(@PathVariable Long userId) {
        log.info("getMatchesByUserId");
        return ResponseEntity.ok(matchesMapper.toResponse(matchesService.findByUserId(userId)));
    }

    /**
     * Get all matches by their state.
     * @param state Match state
     * @return List of MatchesResponseDTO
     */
    @GetMapping("/state/{state}")
    public ResponseEntity<List<MatchesResponseDTO>> getMatchesByState(@PathVariable MatchState state) {
        log.info("getMatchesByState");
        return ResponseEntity.ok(matchesMapper.toResponse(matchesService.findMatchesByMatchState(state)));
    }

    /**
     * Count matches by their state.
     * @param state Match state
     * @return Number of matches
     */
    @GetMapping("/count/state/{state}")
    public ResponseEntity<Long> countMatchesByState(@PathVariable MatchState state) {
        log.info("countMatchesByState");
        return ResponseEntity.ok(matchesService.countMatchesByMatchState(state));
    }
}
