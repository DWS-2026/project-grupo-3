package es.codeurjc.board.rest.mapper;

import es.codeurjc.board.model.Review;
import es.codeurjc.board.rest.dto.ReviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = VideoMapper.class)
public interface ReviewMapper {

    @Mapping(source = "user.username", target = "username")
    ReviewDTO toDTO(Review review);

    List<ReviewDTO> toDTOs(List<Review> reviews);
}