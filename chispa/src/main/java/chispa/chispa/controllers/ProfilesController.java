package chispa.chispa.controllers;

import chispa.chispa.dtos.ProfileRequestDTO;
import chispa.chispa.dtos.ProfileResponseDTO;
import chispa.chispa.mappers.ProfileMapper;
import chispa.chispa.models.enums.Gender;
import chispa.chispa.services.ProfilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Profile entities via REST API.
 * Provides endpoints for CRUD operations and profile-related queries such as by user or gender.
 */
@RestController
@RequestMapping("/api/profiles")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class ProfilesController {

    // Service layer handling business logic for profiles
    private final ProfilesService profilesService;

    // Mapper to convert between Profile entities and DTOs
    private final ProfileMapper profileMapper;

    /**
     * Retrieve all profiles.
     *
     * @return List of ProfileResponseDTO with all profiles
     */
    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {
        log.info("getAllProfiles");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.findAll()));
    }

    /**
     * Get a profile by its ID.
     * @param id Profile ID
     * @return ProfileResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {
        log.info("getProfileById");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.findById(id)));
    }

    /**
     * Get a profile by the associated User ID.
     * @param userId User ID
     * @return ProfileResponseDTO for the user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileResponseDTO> getProfileByUserId(@PathVariable Long userId) {
        log.info("getProfileByUserId");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.findByUserId(userId)));
    }

    /**
     * Create a new profile.
     * @param profileRequestDto Data to create profile
     * @return Created ProfileResponseDTO
     */
    @PostMapping
    public ResponseEntity<ProfileResponseDTO> postProfile(@RequestBody ProfileRequestDTO profileRequestDto) {
        log.info("addProfile");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.save(profileMapper.toModel(profileRequestDto))));
    }

    /**
     * Update an existing profile by ID.
     * @param id Profile ID
     * @param profileRequestDto Updated profile data
     * @return Updated ProfileResponseDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> putProfile(@PathVariable Long id, @RequestBody ProfileRequestDTO profileRequestDto) {
        log.info("putProfile");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.update(id, profileMapper.toModel(profileRequestDto))));
    }

    /**
     * Set a profile status as online.
     * @param id Profile ID
     * @param profileRequestDto Profile data (usually containing status)
     * @return ProfileResponseDTO updated
     */
    @PutMapping("/online/{id}")
    public ResponseEntity<ProfileResponseDTO> setOnline(@PathVariable Long id, @RequestBody ProfileRequestDTO profileRequestDto) {
        log.info("setOnline");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.setOnline(profileMapper.toModel(profileRequestDto))));
    }

    /**
     * Set a profile status as offline.
     * @param id Profile ID
     * @param profileRequestDto Profile data (usually containing status)
     * @return ProfileResponseDTO updated
     */
    @PutMapping("/offline/{id}")
    public ResponseEntity<ProfileResponseDTO> setOffline(@PathVariable Long id, @RequestBody ProfileRequestDTO profileRequestDto) {
        log.info("setOffline");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.setOffline(profileMapper.toModel(profileRequestDto))));
    }

    /**
     * Delete a profile by its ID.
     * @param id Profile ID
     * @return No content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        log.info("deleteProfile");
        profilesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get profiles filtered by gender.
     * @param gender Gender enum value
     * @return List of profiles matching the gender
     */
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<ProfileResponseDTO>> getProfilesByGender(@PathVariable Gender gender) {
        log.info("getProfilesByGender");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.findProfilesByGender(gender)));
    }
}