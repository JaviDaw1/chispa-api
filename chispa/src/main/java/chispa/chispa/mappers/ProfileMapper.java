package chispa.chispa.mappers;

import chispa.chispa.dtos.ProfileRequestDTO;
import chispa.chispa.dtos.ProfileResponseDTO;
import chispa.chispa.models.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapper class for converting between Profile entity and DTOs.
 */
@Component
public class ProfileMapper {

    /**
     * Converts a Profile entity to a ProfileResponseDTO.
     *
     * @param profile the Profile entity to convert
     * @return the corresponding ProfileResponseDTO
     */
    public ProfileResponseDTO toResponse(Profile profile) {
        // Map entity fields to response DTO
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getUser(),
                profile.getName(),
                profile.getLastName(),
                profile.getGender(),
                profile.getBirthDate(),
                profile.getAge(),
                profile.getLocation(),
                profile.getBio(),
                profile.getInterests(),
                profile.getProfilePhoto(),
                profile.getIsOnline(),
                profile.getLastActive(),
                profile.getPreferredRelationship()
        );
    }

    /**
     * Converts a list of Profile entities to a list of ProfileResponseDTOs.
     * @param profiles list of Profile entities
     * @return list of ProfileResponseDTOs
     */
    public List<ProfileResponseDTO> toResponse(List<Profile> profiles) {
        // Stream the list and map each Profile entity to DTO
        return profiles.stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Converts a ProfileRequestDTO to a Profile entity.
     * @param profileRequestDto the request DTO to convert
     * @return the new Profile entity
     */
    public Profile toModel(ProfileRequestDTO profileRequestDto) {
        // Create new Profile entity with current timestamp as lastActive
        return new Profile(
                null,
                profileRequestDto.getUser(),
                profileRequestDto.getName(),
                profileRequestDto.getLastName(),
                profileRequestDto.getGender(),
                profileRequestDto.getBirthDate(),
                profileRequestDto.getLocation(),
                profileRequestDto.getBio(),
                profileRequestDto.getInterests(),
                profileRequestDto.getProfilePhoto(),
                profileRequestDto.getIsOnline(),
                LocalDateTime.now(),
                profileRequestDto.getPreferredRelationship()
        );
    }
}
