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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class MatchesController {
    private final MatchesService matchesService;
    private final MatchesMapper matchesMapper;

    @GetMapping("")
    public ResponseEntity<List<MatchesResponseDTO>> getAllMatches() {
        log.info("getAllMatches");
        return ResponseEntity.ok(matchesMapper.toResponse(matchesService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchesResponseDTO> getMatchById(@PathVariable Long id) {
        log.info("getMatchById");
        return ResponseEntity.ok(matchesMapper.toResponse(matchesService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<MatchesResponseDTO> postMatch(@RequestBody MatchesRequestDTO matchesRequestDto) {
        log.info("addMatch");
        Matches matchSaved = matchesService.save(matchesMapper.toModel(matchesRequestDto));
        return ResponseEntity.created(null).body(matchesMapper.toResponse(matchSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchesResponseDTO> putMatch(@PathVariable Long id, @RequestBody MatchesRequestDTO matchesRequestDto) {
        log.info("putMatch");
        Matches matchUpdated = matchesService.update(id, matchesMapper.toModel(matchesRequestDto));
        return ResponseEntity.ok(matchesMapper.toResponse(matchUpdated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MatchesResponseDTO> patchMatch(@PathVariable Long id, @RequestBody MatchesRequestDTO matchesRequestDto) {
        log.info("patchMatch");
        Matches matchPatched = matchesService.patch(id, matchesMapper.toModel(matchesRequestDto));
        return ResponseEntity.ok(matchesMapper.toResponse(matchPatched));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        log.info("deleteMatch");
        matchesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MatchesResponseDTO>> getMatchesByUserId(@PathVariable Long userId) {
        log.info("getMatchesByUserId");
        return ResponseEntity.ok(matchesMapper.toResponse(matchesService.findByUserId(userId)));
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<MatchesResponseDTO>> getMatchesByState(@PathVariable MatchState state) {
        log.info("getMatchesByState");
        return ResponseEntity.ok(matchesMapper.toResponse(matchesService.findMatchesByMatchState(state)));
    }

    @GetMapping("/count/state/{state}")
    public ResponseEntity<Long> countMatchesByState(@PathVariable MatchState state) {
        log.info("countMatchesByState");
        return ResponseEntity.ok(matchesService.countMatchesByMatchState(state));
    }
}
