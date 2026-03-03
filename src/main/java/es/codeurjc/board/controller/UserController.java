package es.codeurjc.board.controller;
import es.codeurjc.board.model.Username;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private ButtonsHeader btnsHeader;

    private boolean passwordsDontMatch= false;
    private boolean existsUsername = false;

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

    @PostMapping("/User/register")
    public String register(Model model,@RequestParam String password, @RequestParam String email, @RequestParam String username, @RequestParam String repeatpassword){
        model.addAttribute("passwords",passwordsDontMatch);
        Username user = new Username();
        user.setPassword(password);
        user.setEmail(email);
        user.setUsername(username);
       //user.setRoles(); //Hay que añadirle un lista de string

        if(!password.equals(repeatpassword)){ //mirar si las contraseñas coinciden
            passwordsDontMatch = true;
            return "redirect:/User/register";
        }

        if(!existsUsername){

        }

        userService.saveUser(user);
        return "redirect:index";}

    //@PostMapping --> aquí tengo que el postmapping para eliminar un usuario desde el panel del admin



}
