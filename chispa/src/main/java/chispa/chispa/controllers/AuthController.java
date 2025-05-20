package chispa.chispa.controllers;

import chispa.chispa.auth.JwtService;
import chispa.chispa.auth.LoginRequest;
import chispa.chispa.auth.SignupRequest;
import chispa.chispa.models.Users;
import chispa.chispa.models.enums.Role;
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

    //    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//        );
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String token = jwtService.createToken(userDetails.getUsername(), userDetails.getAuthorities(), userDetails.getAuthorities().iterator().next().getAuthority());
//
//        // Verificar si userDetails.getAuthorities() es null
//        Object authorities = userDetails.getAuthorities() != null ? userDetails.getAuthorities() : "No authorities";
//
//        return ResponseEntity.ok(Map.of("token", token, "role", userDetails.getAuthorities()));
//    }
// AuthController.java
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.createToken(userDetails.getUsername(), userDetails.getAuthorities(), getUserRole(userDetails));
        return ResponseEntity.ok(Map.of("token", token, "user", userDetails));
    }

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

    @PostMapping("/signup")
    public ResponseEntity<UserDetails> signup(@RequestBody SignupRequest signupRequest) {
        UserDetails userDetails = userDetailsService.create(signupRequest);
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userDetailsService.getAll();
    }

    @GetMapping("/isAdmin")
    public boolean isAdmin(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }
        return false;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
        Users user = userDetailsService.findById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        usersDetailsServiceImpl.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

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
}