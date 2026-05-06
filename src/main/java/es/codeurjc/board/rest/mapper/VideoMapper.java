package es.codeurjc.board.rest.mapper;

import es.codeurjc.board.model.Video;
import es.codeurjc.board.rest.dto.VideoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    default VideoDTO toDTO(Video video, Long reviewId) {
        return new VideoDTO(
                video.getId(),
                video.getFileName(),
                video.getContentType(),
                "/api/v1/reviews/" + reviewId + "/video"
        );
    }
}
