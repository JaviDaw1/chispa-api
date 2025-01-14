package chispa.chispa.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users user;
    private String name;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private String gender;
    @Nullable
    private String location;
    @Nullable
    @Column(length = 2000)
    private String bio;
    @Nullable
    private String interests;
    private LocalDateTime lastActive;
    @Enumerated(EnumType.STRING)
    private String preferredRelationship;

}
