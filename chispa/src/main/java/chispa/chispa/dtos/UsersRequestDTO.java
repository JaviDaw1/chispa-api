package chispa.chispa.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersRequestDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private final String firstname;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres")
    private final String lastname;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(max = 100, message = "El nombre de usuario no puede tener más de 100 caracteres")
    private final String username;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private final String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    private final String password;

}
