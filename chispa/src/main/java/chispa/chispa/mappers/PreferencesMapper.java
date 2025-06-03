package chispa.chispa.mappers;

import chispa.chispa.dtos.PreferencesRequestDTO;
import chispa.chispa.dtos.PreferencesResponseDTO;
import chispa.chispa.models.Preferences;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper class for converting between Preferences entity and DTOs.
 */
@Component
public class PreferencesMapper {

    /**
     * Converts a Preferences entity to a PreferencesResponseDTO.
     *
     * @param preference the Preferences entity to convert
     * @return the corresponding PreferencesResponseDTO
     */
    public PreferencesResponseDTO toResponse(Preferences preference) {
        // Map entity fields to response DTO
        return new PreferencesResponseDTO(
                preference.getId(),
                preference.getUser(),
                preference.getMinAgeRange(),
                preference.getMaxAgeRange(),
                preference.getMaxDistance(),
                preference.getFavoriteGender()
        );
    }

    /**
     * Converts a list of Preferences entities to a list of PreferencesResponseDTOs.
     * @param preferences list of Preferences entities
     * @return list of PreferencesResponseDTOs
     */
    public List<PreferencesResponseDTO> toResponse(List<Preferences> preferences) {
        // Stream the list and map each Preferences entity to DTO
        return preferences.stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Converts a PreferencesRequestDTO to a Preferences entity.
     * @param preferenceRequestDto the request DTO to convert
     * @return the new Preferences entity
     */
    public Preferences toModel(PreferencesRequestDTO preferenceRequestDto) {
        // Create new Preferences entity
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
