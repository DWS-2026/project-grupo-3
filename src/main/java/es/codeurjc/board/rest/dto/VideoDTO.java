package es.codeurjc.board.rest.dto;

public record VideoDTO(
        Long id,
        String fileName,
        String contentType,
        String url
) {
}
