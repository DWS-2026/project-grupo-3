package es.codeurjc.board.rest.dto;
import java.util.List;

public record PlantBasicDTO (String cares,String name,String description, String species,
    List<ImageDTO> images
){
    
}
