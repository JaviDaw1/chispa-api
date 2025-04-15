package chispa.chispa.models;

import chispa.chispa.models.enums.Gender;
import chispa.chispa.models.enums.PreferredRelationship;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString(exclude = "user")
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Size(max = 255, message = "La ubicación no puede tener más de 255 caracteres")
    @Column(nullable = true)
    private String location;

    @Size(max = 2000, message = "La biografía no puede tener más de 2000 caracteres")
    @Column(nullable = true)
    private String bio;

    @Size(max = 1000, message = "Los intereses no pueden tener más de 1000 caracteres")
    @Column(nullable = true)
    private String interests;

    @Size(max = 255, message = "La foto de perfil no puede tener más de 255 caracteres")
    @Column(nullable = true)
    private String profilePhoto;

    @Column(nullable = false)
    private Boolean isOnline;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime lastActive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreferredRelationship preferredRelationship;

    // Método para calcular la edad de manera dinámica
    public int getAge() {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        }
        return 0; // Si birthDate es null, retorna 0
    }
}
