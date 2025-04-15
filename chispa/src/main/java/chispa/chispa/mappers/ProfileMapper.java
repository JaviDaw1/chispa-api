package chispa.chispa.mappers;

import chispa.chispa.dtos.ProfileRequestDTO;
import chispa.chispa.dtos.ProfileResponseDTO;
import chispa.chispa.models.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProfileMapper {
    public ProfileResponseDTO toResponse(Profile profile) {
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

    public List<ProfileResponseDTO> toResponse(List<Profile> profiles) {
        return profiles.stream()
                .map(this::toResponse)
                .toList();
    }

    public Profile toModel(ProfileRequestDTO profileRequestDto) {
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