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

@RestController
@RequestMapping("/api/profiles")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class ProfilesController {
    private final ProfilesService profilesService;
    private final ProfileMapper profileMapper;

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {
        log.info("getAllProfiles");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {
        log.info("getProfileById");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.findById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileResponseDTO> getProfileByUserId(@PathVariable Long userId) {
        log.info("getProfileByUserId");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.findByUserId(userId)));
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> postProfile(@RequestBody ProfileRequestDTO profileRequestDto) {
        log.info("addProfile");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.save(profileMapper.toModel(profileRequestDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> putProfile(@PathVariable Long id, @RequestBody ProfileRequestDTO profileRequestDto) {
        log.info("putProfile");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.update(id, profileMapper.toModel(profileRequestDto))));
    }

    @PutMapping("/online/{id}")
    public ResponseEntity<ProfileResponseDTO> setOnline(@PathVariable Long id, @RequestBody ProfileRequestDTO profileRequestDto) {
        log.info("setOnline");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.setOnline(profileMapper.toModel(profileRequestDto))));
    }

    @PutMapping("/offline/{id}")
    public ResponseEntity<ProfileResponseDTO> setOffline(@PathVariable Long id, @RequestBody ProfileRequestDTO profileRequestDto) {
        log.info("setOffline");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.setOffline(profileMapper.toModel(profileRequestDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        log.info("deleteProfile");
        profilesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<ProfileResponseDTO>> getProfilesByGender(@PathVariable Gender gender) {
        log.info("getProfilesByGender");
        return ResponseEntity.ok(profileMapper.toResponse(profilesService.findProfilesByGender(gender)));
    }
}
