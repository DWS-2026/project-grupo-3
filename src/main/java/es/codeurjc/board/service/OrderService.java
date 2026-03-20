package es.codeurjc.board.service;

import es.codeurjc.board.model.Order;
import es.codeurjc.board.model.OrderStatus;
import es.codeurjc.board.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {return orderRepository.findAll();}

    public Order findById(long id) {return orderRepository.findById(id).orElseThrow();}

    public Order save(Order order) {return orderRepository.save(order);}

    public void deleteById(Long id) {orderRepository.deleteById(id);}

    public List<Order> findByUser(String username) {
        return orderRepository.findByUserUsername(username);
    }

    public void updateStatus(long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
    }

    public boolean cancelOrder(long id, String username) {
        Order order = orderRepository.findById(id).orElseThrow();
        boolean isOwner = order.getUser().getUsername().equals(username);
        boolean isCancellable = order.getStatus() == OrderStatus.PENDING;
        if (isOwner && isCancellable) {
            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
            return true;
        }
        return false;
    }
}
