package chispa.chispa.repositories;

import chispa.chispa.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    Long countUsersByState(String state);

    Long id(Long id);
}
