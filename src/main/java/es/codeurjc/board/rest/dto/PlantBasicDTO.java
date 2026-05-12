package es.codeurjc.board.rest.dto;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlantBasicDTO (Long id, String cares,@NotBlank(message = "El nombre no puede estar en blanco") @NotNull(message = "La especie no puede ser nula") String name,String description, 
@NotBlank(message = "La especie no puede estar en blanco") @NotNull(message = "La especie no puede ser nula")  String species,
    List<ImageDTO> images
){
    
}
