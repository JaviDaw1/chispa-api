package chispa.chispa.mappers;

import chispa.chispa.dtos.PreferencesRequestDTO;
import chispa.chispa.dtos.PreferencesResponseDTO;
import chispa.chispa.models.Preferences;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PreferencesMapper {
    public PreferencesResponseDTO toResponse(Preferences preference) {
        return new PreferencesResponseDTO(
                preference.getId(),
                preference.getUser(),
                preference.getMinAgeRange(),
                preference.getMaxAgeRange(),
                preference.getMaxDistance(),
                preference.getFavoriteGender()
        );
    }
    public List<PreferencesResponseDTO> toResponse(List<Preferences> preferences) {
        return preferences.stream()
                .map(this::toResponse)
                .toList();
    }
    public Preferences toModel(PreferencesRequestDTO preferenceRequestDto) {
        return new Preferences(
                null,
                preferenceRequestDto.getUser(),
                preferenceRequestDto.getMinAgeRange(),
                preferenceRequestDto.getMaxAgeRange(),
                preferenceRequestDto.getMaxDistance(),
                preferenceRequestDto.getFavoriteGender()
        );
    }
}
