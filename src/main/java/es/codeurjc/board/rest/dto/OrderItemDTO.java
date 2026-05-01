package es.codeurjc.board.rest.dto;

public record OrderItemDTO(
        Long id,
        String productName,
        double price,
        int quantity,
        double subtotal
) {}