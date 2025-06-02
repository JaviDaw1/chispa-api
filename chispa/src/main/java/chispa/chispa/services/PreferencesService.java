package chispa.chispa.services;//Esto lo podriamos implementar si es factible

import chispa.chispa.models.Preferences;

import java.util.List;


public interface PreferencesService {
    Preferences findById(Long id);

    Preferences findByUserId(Long userId);

    Preferences save(Preferences preferences);

    Preferences update(Long id, Preferences preferences);

    void deleteById(Long id);

    List<Preferences> findAll();
}

