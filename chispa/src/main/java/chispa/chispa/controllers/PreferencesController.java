package chispa.chispa.controllers;

import chispa.chispa.dtos.PreferencesRequestDTO;
import chispa.chispa.dtos.PreferencesResponseDTO;
import chispa.chispa.mappers.PreferencesMapper;
import chispa.chispa.services.PreferencesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Preferences operations via REST API.
 * Handles CRUD operations and queries for user preferences.
 */
@RestController
@RequestMapping("/api/preferences")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class PreferencesController {
    // Service for Preferences business logic
    private final PreferencesService preferencesService;
    // Mapper for converting between entity and DTO
    private final PreferencesMapper preferencesMapper;

    /**
     * Get all preferences in the system.
     *
     * @return List of PreferencesResponseDTO
     */
    @GetMapping
    public ResponseEntity<List<PreferencesResponseDTO>> getAllPreferences() {
        log.info("getAllPreferences");
        return ResponseEntity.ok(preferencesMapper.toResponse(preferencesService.findAll()));
    }

    /**
     * Get preferences by its ID.
     * @param id Preferences ID
     * @return PreferencesResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<PreferencesResponseDTO> getPreferencesById(@PathVariable Long id) {
        log.info("getPreferencesById");
        return ResponseEntity.ok(preferencesMapper.toResponse(preferencesService.findById(id)));
    }

    /**
     * Get preferences by user ID.
     * @param userId User ID
     * @return PreferencesResponseDTO or error message if not found
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPreferencesByUserId(@PathVariable Long userId) {
        log.info("getPreferencesByUserId");
        var preference = preferencesService.findByUserId(userId);
        if (preference == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Preferencias no encontradas");
        }
        return ResponseEntity.ok(preferencesMapper.toResponse(preference));
    }

    /**
     * Create new preferences.
     * @param preferencesRequestDto Preferences data
     * @return Created PreferencesResponseDTO
     */
    @PostMapping
    public ResponseEntity<PreferencesResponseDTO> postPreferences(@RequestBody PreferencesRequestDTO preferencesRequestDto) {
        log.info("addPreferences");
        return ResponseEntity.ok(preferencesMapper.toResponse(preferencesService.save(preferencesMapper.toModel(preferencesRequestDto))));
    }

    /**
     * Update preferences by its ID.
     * @param id Preferences ID
     * @param preferencesRequestDto Updated data
     * @return Updated PreferencesResponseDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<PreferencesResponseDTO> putPreferences(@PathVariable Long id, @RequestBody PreferencesRequestDTO preferencesRequestDto) {
        log.info("putPreferences");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        var preferences = preferencesService.findById(id);
        if (!preferences.getUser().getEmail().equals(username) && !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(preferencesMapper.toResponse(preferencesService.update(id, preferencesMapper.toModel(preferencesRequestDto))));
    }

    /**
     * Delete preferences by its ID.
     * @param id Preferences ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreferences(@PathVariable Long id) {
        log.info("deletePreferences");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        var preferences = preferencesService.findById(id);
        if (!preferences.getUser().getEmail().equals(username) && !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).build();
        }
        preferencesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
