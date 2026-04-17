package es.codeurjc.board.rest.mapper;


import org.mapstruct.Mapper;

import es.codeurjc.board.model.Plant;
import es.codeurjc.board.rest.dto.PlantBasicDTO;
import es.codeurjc.board.rest.dto.PlantExtendedDTO;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PlantMapper {
    PlantBasicDTO basicToDTO(Plant plant);

    List<PlantBasicDTO> basicToDTOs(Collection<Plant> plants);

    Plant basicToDomain(PlantBasicDTO plantDTO);

    PlantExtendedDTO extendedToDTO(Plant plant);

    Plant extendedToDomin(PlantExtendedDTO plantDTO);
    
}
