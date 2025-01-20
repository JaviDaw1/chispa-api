package chispa.chispa.repositories;

import chispa.chispa.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfilesRepository extends JpaRepository<Profile, Long> {
    Profile findByUserId(Long userId);

    List<Profile> findProfileByGender(String gender);
}
