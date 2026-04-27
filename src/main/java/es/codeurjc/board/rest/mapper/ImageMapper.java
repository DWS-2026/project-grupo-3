package es.codeurjc.board.rest.mapper;

import org.mapstruct.Mapper;
import es.codeurjc.board.model.Image;
import es.codeurjc.board.rest.dto.ImageDTO;

@Mapper(componentModel = "spring")
public interface ImageMapper {
	ImageDTO toDTO(Image image);
}