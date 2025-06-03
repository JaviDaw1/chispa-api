package chispa.chispa.controllers;

import chispa.chispa.auth.JwtService;
import chispa.chispa.auth.LoginRequest;
import chispa.chispa.auth.SignupRequest;
import chispa.chispa.models.Users;
import chispa.chispa.models.enums.Role;
import chispa.chispa.services.EmailService;
import chispa.chispa.services.UsersDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

/**
 * REST controller for user authentication and user management endpoints.
 * <p>
 * Handles login, signup, password management, and user queries.
 * </p>
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsersDetailsServiceImpl userDetailsService;
    private final UsersDetailsServiceImpl usersDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    /**
     * User login endpoint.
     * <p>
     * Authenticates user credentials and returns a JWT token on success.
     * </p>
     *
     * @param loginRequest contains email and password for login
     * @return JWT token and user details if authentication is successful
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.createToken(userDetails.getUsername(), userDetails.getAuthorities(), getUserRole(userDetails));
        return ResponseEntity.ok(Map.of("token", token, "user", userDetails));
    }

    /**
     * Helper method to extract user role from UserDetails.
     *
     * @param userDetails authenticated user details
     * @return Role enum representing the user's role, defaults to USER
     */
    private Role getUserRole(UserDetails userDetails) {
        if (userDetails != null) {
            if (userDetails.getAuthorities() != null && !userDetails.getAuthorities().isEmpty()) {
                String roleName = userDetails.getAuthorities().iterator().next().getAuthority();
                return Role.valueOf(roleName);
            } else {
                return Role.USER;
            }
        } else {
            return Role.USER;
        }
    }

    /**
     * User signup (registration) endpoint.
     * <p>
     * Creates a new user with the provided signup data.
     * </p>
     *
     * @param signupRequest data required to create a new user
     * @return created user details
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDetails> signup(@RequestBody SignupRequest signupRequest) {
        UserDetails userDetails = userDetailsService.create(signupRequest);
        return ResponseEntity.ok(userDetails);
    }

    /**
     * Get a list of all users.
     *
     * @return list of all Users entities
     */
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userDetailsService.getAll();
    }

    /**
     * Check if the authenticated user has an ADMIN role.
     *
     * @param authentication current authentication object
     * @return true if user is admin, false otherwise
     */
    @GetMapping("/isAdmin")
    public boolean isAdmin(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }
        return false;
    }

    /**
     * Get user details by user ID.
     *
     * @param userId ID of the user to retrieve
     * @return user details if found, 404 otherwise
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
        Users user = userDetailsService.findById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a user by their ID.
     *
     * @param userId ID of the user to delete
     * @return 204 No Content response after deletion
     */
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        usersDetailsServiceImpl.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update user's password.
     * <p>
     * Requires current password and new password in the request body.
     * </p>
     *
     * @param userId  ID of the user to update password for
     * @param request map containing "currentPassword" and "newPassword"
     * @return confirmation message or error details
     */
    @PutMapping("/change-password/{userId}")
    public ResponseEntity<?> updatePassword(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("newPassword");

        if (currentPassword == null || newPassword == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Se requieren la contraseña actual y la nueva contraseña"));
        }

        Users user = userDetailsService.findById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "Usuario no encontrado"));
        }

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Contraseña actual incorrecta"));
        }

        Users updatedUser = userDetailsService.updatePassword(userId, newPassword);

        return ResponseEntity.ok(Map.of(
                "message", "Contraseña actualizada correctamente",
                "user", updatedUser
        ));
    }

    /**
     * Initiate forgot password process by sending a reset token to user's email.
     *
     * @param request map containing "email" of the user
     * @return success or error message
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Users user = userDetailsService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Usuario no encontrado"));
        }
        String token = java.util.UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusHours(1)); // token valid for 1 hour
        userDetailsService.save(user);
        emailService.sendResetPasswordEmail(user.getEmail(), token);

        return ResponseEntity.ok(Map.of("message", "Correo de recuperación enviado"));
    }

    /**
     * Reset password using a valid reset token.
     *
     * @param request map containing "token" and "newPassword"
     * @return success or error message
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");
        Users user = userDetailsService.findByResetToken(token);
        if (user == null || user.getResetTokenExpiry() == null || user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(400).body(Map.of("message", "Token inválido o expirado"));
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userDetailsService.save(user);
        return ResponseEntity.ok(Map.of("message", "Contraseña actualizada correctamente"));
    }
}
