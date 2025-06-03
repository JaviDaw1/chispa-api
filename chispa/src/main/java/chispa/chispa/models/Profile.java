package chispa.chispa.models;

import chispa.chispa.models.enums.Gender;
import chispa.chispa.models.enums.PreferredRelationship;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * Entity representing additional profile details of a user.
 */
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

    // Owner of the profile
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    // Optional location info
    @Size(max = 255)
    private String location;

    // Optional bio
    @Size(max = 2000)
    private String bio;

    // Optional interests
    @Size(max = 1000)
    private String interests;

    // Optional profile photo (e.g., base64 or URL)
    @Size(max = 5000)
    private String profilePhoto;

    @Column(nullable = false)
    private Boolean isOnline;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime lastActive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreferredRelationship preferredRelationship;

    /**
     * Calculates the user's age based on birth date.
     *
     * @return the age in years
     */
    public int getAge() {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        }
        return 0;
    }
}
