package chispa.chispa.controllers;

import chispa.chispa.dtos.PreferencesRequestDTO;
import chispa.chispa.dtos.PreferencesResponseDTO;
import chispa.chispa.mappers.PreferencesMapper;
import chispa.chispa.services.PreferencesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class PreferencesController {
    private final PreferencesService preferencesService;
    private final PreferencesMapper preferencesMapper;

    @GetMapping
    public ResponseEntity<List<PreferencesResponseDTO>> getAllPreferences() {
        log.info("getAllPreferences");
        return ResponseEntity.ok(preferencesMapper.toResponse(preferencesService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreferencesResponseDTO> getPreferencesById(@PathVariable Long id) {
        log.info("getPreferencesById");
        return ResponseEntity.ok(preferencesMapper.toResponse(preferencesService.findById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PreferencesResponseDTO> getPreferencesByUserId(@PathVariable Long userId) {
        log.info("getPreferencesByUserId");
        return ResponseEntity.ok(preferencesMapper.toResponse(preferencesService.findByUserId(userId)));
    }

    @PostMapping
    public ResponseEntity<PreferencesResponseDTO> postPreferences(@RequestBody PreferencesRequestDTO preferencesRequestDto) {
        log.info("addPreferences");
        return ResponseEntity.ok(preferencesMapper.toResponse(preferencesService.save(preferencesMapper.toModel(preferencesRequestDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreferencesResponseDTO> putPreferences(@PathVariable Long id, @RequestBody PreferencesRequestDTO preferencesRequestDto) {
        log.info("putPreferences");
        return ResponseEntity.ok(preferencesMapper.toResponse(preferencesService.update(id, preferencesMapper.toModel(preferencesRequestDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreferences(@PathVariable Long id) {
        log.info("deletePreferences");
        preferencesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}