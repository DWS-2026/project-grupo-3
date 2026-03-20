package es.codeurjc.board.repositories;

import es.codeurjc.board.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
}
