package chispa.chispa.services;

import chispa.chispa.models.Profile;
import chispa.chispa.repositories.ProfilesRepository;
import chispa.chispa.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Transactional
@Service
@AllArgsConstructor
public class ProfilesServiceImpl implements ProfilesService {

    private final ProfilesRepository profileRepository;
    private final UsersRepository usersRepository;

    @Override
    public Profile findById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profiles not found"));
    }

    @Override
    public Profile findByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    @Override
    public Profile save(Profile profile) {
        profile.setUser(usersRepository.findById(profile.getUser().getId()).orElseThrow());
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Long id, Profile profile) {
        Profile profileUpdated = this.findById(id);
        profileUpdated.setName(profile.getName());
        profileUpdated.setBio(profile.getBio());
        profileUpdated.setProfilePhoto(profile.getProfilePhoto());
        profileUpdated.setInterests(profile.getInterests());
        profileUpdated.setGender(profile.getGender());
        profileUpdated.setIsOnline(profile.getIsOnline());
        profileUpdated.setLastActive(LocalDate.now());
        profileUpdated.setLastName(profile.getLastName());
        profileUpdated.setPreferredRelationship(profile.getPreferredRelationship());
        profileUpdated.setLocation(profile.getLocation());
        return profileRepository.save(profileUpdated);
    }

    @Override
    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Map<String, Long> countProfilesByPreferredRelationship(String preferredRelationship) {
        return profileRepository.countProfilesByPreferredRelationship(preferredRelationship);
    }

    @Override
    public List<Profile> findProfilesByAgeRange(Integer minAge, Integer maxAge) {
        return profileRepository.findProfilesByAgeRange(minAge, maxAge);
    }

    @Override
    public List<Profile> findProfilesByGender(String gender) {
        return profileRepository.findProfileByGender(gender);
    }
}
