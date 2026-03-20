package es.codeurjc.board.controller;
import es.codeurjc.board.model.Order;
import es.codeurjc.board.model.OrderStatus;
import es.codeurjc.board.model.User;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.OrderService;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private ButtonsHeader btnsHeader;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private boolean passwordsDontMatch= false;
    private boolean existsUser = false;

    @GetMapping("/User/user")
    public String user(Model model) {
        btnsHeader.hideBtnHeader(model,"profile");
        return "User/user";
    }

    @GetMapping("/User/ordersUser")
    public String ordersUser(Model model, HttpServletRequest request) {
        User user = userService.getUser(request);
        List<Order> orders = orderService.findByUser(user.getUsername());

        for (Order order : orders) {
            order.setProcessing(order.getStatus() == OrderStatus.PENDING);
            order.setShipped(order.getStatus() == OrderStatus.SHIPPED);
            order.setDelivered(order.getStatus() == OrderStatus.DELIVERED);
            order.setCancelled(order.getStatus() == OrderStatus.CANCELLED);
        }

        model.addAttribute("orders", orders);
        model.addAttribute("hasOrders", !orders.isEmpty());
        return "User/ordersUser";
    }

    @GetMapping("/User/configuration")
    public String configuration() {
        return "User/configuration";
    }

    @GetMapping("/User/editProfile")
    public String editProfile() {
        return "User/editProfile";
    }

    @GetMapping("/User/register")
    public String register() {
        return "register";
    }

    @PostMapping("/User/register")
    public String register(Model model,@RequestParam String password, @RequestParam String email, @RequestParam String username, @RequestParam String repeatpassword){
        model.addAttribute("passwords",passwordsDontMatch);
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setUsername(username);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles); //Hay que añadirle un lista de string

        if(!password.equals(repeatpassword)){ //mirar si las contraseñas coinciden
            passwordsDontMatch = true;
            return "redirect:/User/register";
        }

        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("User/delete/{id}")
    public String deleteUser(@PathVariable long id) throws IOException {
        userService.deleteById(id);
        return "redirect:/Reviews/forum";
    }

    //@PostMapping --> aquí tengo que el postmapping para eliminar un usuario desde el panel del admin



}
