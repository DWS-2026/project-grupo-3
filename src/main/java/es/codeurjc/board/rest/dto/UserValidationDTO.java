package es.codeurjc.board.rest.dto;
import jakarta.validation.constraints.*;

public record UserValidationDTO ( @NotBlank(message = "El username es obligatorio")String username,

    String description,
    @NotNull;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    String email,

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "Mínimo 8 caracteres")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-]).*$",
        message = "Debe tener mayúsculas, minúsculas, números y un carácter especial"
    )
    String password

) {}