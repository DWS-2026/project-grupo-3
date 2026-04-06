package es.codeurjc.board.controller;
import es.codeurjc.board.model.*;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.CartService;
import es.codeurjc.board.service.OrderService;
import es.codeurjc.board.service.ProductService;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrdersController {
    @Autowired
    private ButtonsHeader btnsHeader;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/Orders/shoppingCart")
    public String shoppingCart(Model model) {
        btnsHeader.hideBtnHeader(model,"shoppingCart");
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("cartTotal", cartService.getFormattedTotal());
        model.addAttribute("cartEmpty", cartService.isEmpty());
        return "Orders/shoppingCart";
    }

    @GetMapping("/Orders/payment")
    public String payment(Model model) {
        if (cartService.isEmpty()) {
            return "redirect:/Orders/shoppingCart";
        }
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("cartTotal", cartService.getTotal());
        return "Orders/payment";
    }

    @GetMapping("/Orders/success")
    public String orderSuccess() {
        return "Orders/orderSuccess";
    }


    @PostMapping("/Orders/checkout")
    public String checkout(HttpServletRequest request) {

        if (cartService.isEmpty()) {
            return "redirect:/Orders/shoppingCart";
        }

        User user = userService.getUser(request);

        List<OrderItems> items = cartService.getItems().stream().map(cartItem -> {
            Product product = productService.findById(cartItem.getProductId());
            return new OrderItems(product, cartItem.getQuantity());
        }).collect(Collectors.toList());

        Order order = new Order(user, items);
        orderService.save(order);

        cartService.clear();

        return "redirect:/Orders/success";
    }

    @PostMapping("/Orders/{id}/cancel")
    public String cancelOrder(@PathVariable long id, HttpServletRequest request) {
        User user = userService.getUser(request);
        orderService.cancelOrder(id, user.getUsername());
        return "redirect:/User/ordersUser";
    }

    @PostMapping("/Orders/{id}/status")
    public String updateOrderStatus(@PathVariable long id,
                                    @RequestParam OrderStatus status) {
        orderService.updateStatus(id, status);
        return "redirect:/Admin/managementOrders";
    }

    @PostMapping("/Orders/{id}/delete")
    public String deleteOrder(@PathVariable long id) {
        orderService.deleteById(id);
        return "redirect:/Admin/managementOrders";
    }


}
