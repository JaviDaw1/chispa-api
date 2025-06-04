package chispa.chispa.services;

import chispa.chispa.auth.SignupRequest;
import chispa.chispa.models.Users;
import chispa.chispa.models.enums.Role;
import chispa.chispa.repositories.UsersDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that implements the UserDetailsService interface.
 * It provides user-related operations such as registration, authentication,
 * password updates, and user retrieval.
 */
@Service
@Primary
@RequiredArgsConstructor
public class UsersDetailsServiceImpl implements UserDetailsService {

    // Repository to interact with the user database
    private final UsersDetailsRepository userDetailsRepository;

    // Encoder for encrypting passwords
    private final PasswordEncoder passwordEncoder;

    /**
     * Save a user to the database.
     *
     * @param user The user entity to be saved.
     * @return The saved user.
     */
    public Users save(Users user) {
        return userDetailsRepository.save(user);
    }

    /**
     * Delete a user by their ID.
     *
     * @param id The ID of the user to delete.
     */
    public void deleteById(Long id) {
        userDetailsRepository.deleteById(id);
    }

    /**
     * Load a user by email for authentication purposes.
     *
     * @param email The email of the user.
     * @return UserDetails object used by Spring Security.
     * @throws UsernameNotFoundException if no user is found with the given email.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email);
    }

    /**
     * Retrieve all users from the database.
     *
     * @return A list of all users.
     */
    public List<Users> getAll() {
        return userDetailsRepository.findAll();
    }

    /**
     * Create and save a new user using a SignupRequest.
     *
     * @param signupRequest The sign-up request data.
     * @return The saved UserDetails.
     */
    public UserDetails create(SignupRequest signupRequest) {
        Users user = new Users(
                signupRequest.getFirstname(),
                signupRequest.getLastname(),
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
        );

        // Set the user's role, defaulting to USER if not specified
        Role role = signupRequest.getUserRole() != null
                ? signupRequest.getUserRole()
                : Role.valueOf("USER");
        user.setRole(role);

        // NUEVO: Guarda la ubicación
        user.setLocation(signupRequest.getLocation());
        user.setLatitude(signupRequest.getLatitude());
        user.setLongitude(signupRequest.getLongitude());

        return userDetailsRepository.save(user);
    }

    /**
     * Update a user's password by ID.
     *
     * @param userId      The ID of the user.
     * @param newPassword The new plain-text password to be encoded.
     * @return The updated user entity or null if user not found.
     */
    public Users updatePassword(Long userId, String newPassword) {
        Users user = userDetailsRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        // Set and encode the new password
        user.setPassword(passwordEncoder.encode(newPassword));
        return userDetailsRepository.save(user);
    }

    /**
     * Find a user by their email.
     *
     * @param email The email of the user.
     * @return The user found, or null if not found.
     */
    public Users findByEmail(String email) {
        return (Users) userDetailsRepository.findByEmail(email);
    }

    /**
     * Find a user by their ID.
     *
     * @param userId The ID of the user.
     * @return The user found, or null if not found.
     */
    public Users findById(Long userId) {
        return userDetailsRepository.findById(userId).orElse(null);
    }

    /**
     * Find a user by their password reset token.
     *
     * @param token The reset token.
     * @return The user associated with the reset token.
     */
    public Users findByResetToken(String token) {
        return userDetailsRepository.findByResetToken(token);
    }
}
