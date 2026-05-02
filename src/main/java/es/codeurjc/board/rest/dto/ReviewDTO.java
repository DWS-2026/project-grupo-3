package es.codeurjc.board.rest.dto;

import es.codeurjc.board.model.Review;

public record ReviewDTO (String title, String description, Review.ReviewType type, UserBasicDTO user){
}
