package es.codeurjc.board.rest.dto;

import es.codeurjc.board.model.Review;

public record ReviewDTO(
        Long id,
        String title,
        String description,
        Review.ReviewType type,
        String username,
        VideoDTO video
) {
}