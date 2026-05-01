package es.codeurjc.board.rest.restController;


import es.codeurjc.board.model.Order;
import es.codeurjc.board.model.OrderItems;
import es.codeurjc.board.model.OrderStatus;
import es.codeurjc.board.model.Product;
import es.codeurjc.board.rest.dto.CreateOrderItemDTO;
import es.codeurjc.board.rest.dto.OrderDTO;
import es.codeurjc.board.rest.mapper.OrderMapper;
import es.codeurjc.board.service.OrderItemService;
import es.codeurjc.board.service.OrderService;
import es.codeurjc.board.service.ProductService;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<OrderDTO>> getAllOrders(HttpServletRequest request){
        if(!userService.isUserAdmin(request)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(orderMapper.toDTOs(orderService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable long id, HttpServletRequest request){
        if(!userService.isUserAdmin(request)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Order order = orderService.findById(id);
        return ResponseEntity.ok(orderMapper.toDTO(order));
    }

    @GetMapping("/my")
    public ResponseEntity<List<OrderDTO>> getUserOrders(HttpServletRequest request){
        if(!userService.seeIfUserIsLoggedIn(request)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = userService.getUser(request).getUsername();
        return ResponseEntity.ok(orderMapper.toDTOs(orderService.findByUser(username)));
    }

    @PostMapping("/")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody List<CreateOrderItemDTO> itemsDTOs, HttpServletRequest request){
        if(userService.isUserAdmin(request) && !userService.seeIfUserIsLoggedIn(request)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<OrderItems> items = itemsDTOs.stream().map(dto -> {
            Product product = productService.findById(dto.productId());
            return new OrderItems(product, dto.quantity());
        }).toList();

        Order order = new Order(userService.getUser(request), items);
        orderService.save(order);
        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        return ResponseEntity.created(location).body(orderMapper.toDTO(order));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable long id, @RequestParam OrderStatus status, HttpServletRequest request){
        if(!userService.isUserAdmin(request)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        orderService.updateStatus(id, status);
        return ResponseEntity.ok(orderMapper.toDTO(orderService.findById(id)));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable long id, HttpServletRequest request){
        if(!userService.seeIfUserIsLoggedIn(request)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = userService.getUser(request).getUsername();
        boolean cancelled = orderService.cancelOrder(id, username);
        if(cancelled){
            return ResponseEntity.ok(orderMapper.toDTO(orderService.findById(id)));
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDTO> deleteOrder(@PathVariable long id, HttpServletRequest request){
        if(!userService.isUserAdmin(request)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Order order = orderService.findById(id);
        orderService.deleteById(id);
        return ResponseEntity.ok(orderMapper.toDTO(order));
    }
}
