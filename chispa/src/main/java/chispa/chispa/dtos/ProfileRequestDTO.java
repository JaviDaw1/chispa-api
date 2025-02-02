package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.Gender;
import chispa.chispa.models.enums.PreferredRelationship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfileRequestDTO {
    private final Users user;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private final String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres")
    private final String lastName;

    @NotNull(message = "El género no puede estar vacío")
    private final Gender gender; // Enum: 'male', 'female', 'other'

    @Size(max = 255, message = "La ubicación no puede tener más de 255 caracteres")
    private final String location;

    @Size(max = 2000, message = "La biografía no puede tener más de 2000 caracteres")
    private final String bio;

    @Size(max = 1000, message = "Los intereses no pueden tener más de 1000 caracteres")
    private final String interests;

    private final String profilePhoto;

    @NotNull(message = "El estado en línea no puede estar vacío")
    private final Boolean isOnline;

    @NotNull(message = "El tipo de relación preferida no puede estar vacío")
    private final PreferredRelationship preferredRelationship;  // Enum: 'friendship', 'casual', 'serious'
}
