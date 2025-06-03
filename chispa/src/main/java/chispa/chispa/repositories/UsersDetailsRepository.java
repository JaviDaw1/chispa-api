package chispa.chispa.repositories;

import chispa.chispa.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for managing user accounts and login details.
 */
@Repository
public interface UsersDetailsRepository extends JpaRepository<Users, Long> {

    /**
     * Finds a user by email (used for login).
     */
    Users findByEmail(String email);

    /**
     * Deletes a user by ID.
     */
    void deleteById(Long id);

    /**
     * Finds a user by their password reset token.
     */
    Users findByResetToken(String resetToken);
}
