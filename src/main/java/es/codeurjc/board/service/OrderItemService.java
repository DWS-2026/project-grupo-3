package es.codeurjc.board.service;

import es.codeurjc.board.model.OrderItems;
import es.codeurjc.board.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItems save(OrderItems orderItems) {return orderItemRepository.save(orderItems);}

    public void deleteById(Long id) {orderItemRepository.deleteById(id);}
}
