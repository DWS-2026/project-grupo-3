package es.codeurjc.board.rest.dto;
import jakarta.validation.constraints.Pattern;

public record UserEditDTO (String username,

    String description,
    
    String email,
    
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-]).*$",
        message = "Debe tener mayúsculas, minúsculas, números y un carácter especial"
    )
    String password

) {}