package es.codeurjc.board.rest.dto;

import java.util.List;

public record ProductDTO (
    Long id,
    String name,
    String description,
    double price,
    boolean active,
    List<ImageDTO> images) {
}
