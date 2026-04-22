package es.codeurjc.board.rest.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import es.codeurjc.board.model.Plant;
import es.codeurjc.board.rest.dto.PlantBasicDTO;
import es.codeurjc.board.rest.dto.PlantExtendedDTO;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PlantMapper {    
    // MapStruct navigates nested objects via getters: plant.getType().getSpecies() → dto.species()

    @Mapping(source = "type.species", target = "species")  //in every . on type . species there is a getter -> we are forcing that the field species comes
    //from doing plant.getType().getSpecies() so Species is a string and it can be easily shown
    PlantBasicDTO ToDTO(Plant plant);

    List<PlantBasicDTO> ToDTOs(Collection<Plant> plants);

    Plant ToDomain(PlantBasicDTO plantDTO);

    PlantExtendedDTO extendedToDTO(Plant plant);

    Plant extendedToDomin(PlantExtendedDTO plantDTO);
    
}
