package es.codeurjc.board.rest.dto;

public record CreateOrderItemDTO(
        long productId,
        int quantity
) {}