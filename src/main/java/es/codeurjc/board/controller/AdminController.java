package es.codeurjc.board.controller;
import es.codeurjc.board.model.Order;
import es.codeurjc.board.model.OrderStatus;
import es.codeurjc.board.model.Product;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.modelAttributes.SessionAttributes;
import es.codeurjc.board.repositories.OrderRepository;
import es.codeurjc.board.repositories.ProductRepository;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private ButtonsHeader btnsHeader;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/adminPanel")
    public String adminPanel(Model model) {
        SessionAttributes.hideAdminPanel(model);
        return "Admin/adminPanel";
    }

    @GetMapping("/productManagement")
    public String productManagement(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "Admin/productManagement";
    }

    @GetMapping("/managementOrders")
    public String managementOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            order.setProcessing(order.getStatus() == OrderStatus.PENDING);
            order.setShipped(order.getStatus()   == OrderStatus.SHIPPED);
            order.setDelivered(order.getStatus() == OrderStatus.DELIVERED);
            order.setCancelled(order.getStatus() == OrderStatus.CANCELLED);
        }
        model.addAttribute("orders", orders);
        return "Admin/managementOrders";
    }

    @GetMapping("/userManagement")
    public String userManagement(Model model) {
        model.addAttribute("users", userService.findAll());
        return "Admin/userManagement";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUserByAdmin(@PathVariable Long id, HttpServletRequest request){
        if(!userService.isUserAdmin(request)){
            return "/accessDenied";
        }
        userService.deleteUser(id);
        return "redirect:/Admin/userManagement";
    }
}