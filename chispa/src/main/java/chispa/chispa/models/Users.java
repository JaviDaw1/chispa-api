package chispa.chispa.models;

import chispa.chispa.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;

@Entity
@Getter
@Setter
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String firstname;

    @NotBlank
    @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres")
    private String lastname;

    @NotBlank
    @Size(max = 100, message = "El nombre de usuario no puede tener más de 100 caracteres")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Users(String firstname, String lastname, String username, String email, String password) {
        this.firstname = firstname;
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

/*
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserState state;
    private Integer age;
    @CreatedDate
    private LocalDate joinDate;
}
*/