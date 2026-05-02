package es.codeurjc.board.rest.dto;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        String username,
        LocalDateTime orderDate,
        String status,
        double price,
        List<OrderItemDTO> items
) {}
