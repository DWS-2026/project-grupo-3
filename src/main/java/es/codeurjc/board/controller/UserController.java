package es.codeurjc.board.controller;
import es.codeurjc.board.model.User;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.UserService;
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


    @GetMapping("/User/user")
    public String user(Model model) {
        btnsHeader.hideBtnHeader(model,"profile");
        return "User/user";
    }

    @GetMapping("/User/ordersUser")
    public String ordersUser() {
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
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles); //Hay que añadirle un lista de string

        userService.saveUser(user);

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
            model.addAttribute("passwordError", true);//mirar si las contraseñas coinciden
            error = true;
        }

        if(error){
            return "/User/register";
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
