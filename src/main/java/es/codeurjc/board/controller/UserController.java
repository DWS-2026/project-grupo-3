package es.codeurjc.board.controller;
import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Order;
import es.codeurjc.board.model.OrderStatus;
import es.codeurjc.board.model.User;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.ImageService;
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
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private ImageService imageService;


    @PostMapping("/User/delete/{id}")
    public String delete(HttpServletRequest session, @PathVariable Long id){
        if(userService.getUserID(session)==id){
            userService.deleteUser(id);
            session.getSession().invalidate();
            return "redirect:/logout";
        }else{
            return "/accessDenied";
        }
    }


    @GetMapping("/User/profile/{id}")
    public String user(@PathVariable long id, Model model, HttpServletRequest session) {
        btnsHeader.hideBtnHeader(model,"profile");
        if(userService.getUserID(session) == id || userService.isUserAdmin(session)){
            User user = userService.findById(id);
            if(user != null){
                model.addAttribute("user", userService.findById(id));
                return "User/user";
            } else{
                return "/error";
            }

        }
        return "/accessDenied";


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

    @PostMapping("/User/configuration")
    public String configuration(Model model, MultipartFile imageFile, HttpServletRequest session,@RequestParam String repeatpassword, @RequestParam String password, @RequestParam String email, @RequestParam String username, @RequestParam String description)throws IOException, Exception {
        boolean error = false;
        if(username !=null && !username.isBlank() && userService.usernameExists(username)) {
            model.addAttribute("usernameError", true);
            error = true;
        }

        if(email !=null && !email.isBlank() && userService.emailExists(email)){
            model.addAttribute("emailError", true);
            error = true;
        }
        String encodedPassword = null;
        if(password !=null && !password.isBlank() && !password.equals(repeatpassword)){
            model.addAttribute("passwordError", true);
            encodedPassword = passwordEncoder.encode(password);
            error = true;
        }
        if(error){
            return  "/User/configuration";
        }

        if(!error){
            userService.editUser( email,username, description, encodedPassword, imageFile, userService.getUserID(session));
            if(password !=null && !password.isBlank() || email !=null && !email.isBlank() || username !=null && !username.isBlank()){
                session.getSession().invalidate();
            };
        }



        return "redirect:/User/profile/"+userService.getUserID(session);

    }

    @GetMapping("/User/register")
    public String register() {
        return "register";
    }

    @PostMapping("/User/register")
    public String register(Model model, MultipartFile imageFile, @RequestParam String password, @RequestParam String email, @RequestParam String username, @RequestParam String repeatpassword, @RequestParam String description) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setDescription(description);

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);

        boolean error = false;

        if(userService.usernameExists(username)) {
            model.addAttribute("usernameError", true);
            error = true;
        }

        if(userService.emailExists(email)){
            model.addAttribute("emailError", true);
            error = true;
        }

        if(!password.equals(repeatpassword)){
            model.addAttribute("passwordError", true);// look if the passwords match
            error = true;
        }

        if(error){
            return "register";
        }


        userService.saveUser(user);
        if (imageFile!= null && !imageFile.isEmpty()) {
            Image imageOne = imageService.createImage(imageFile);
            userService.addImageToUser(user, imageOne);
        }
        return "redirect:/";
    }







}