package es.codeurjc.board.rest.mapper;

import es.codeurjc.board.model.Video;
import es.codeurjc.board.rest.dto.VideoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoDTO toDTO(Video video);
}
