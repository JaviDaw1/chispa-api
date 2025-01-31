package chispa.chispa.repositories;

import chispa.chispa.models.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferencesRepository extends JpaRepository<Preferences, Long> {
    Preferences findByUserId(Long userId);

}
