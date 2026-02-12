package es.codeurjc.board.controller;
import es.codeurjc.board.model.ButtonsHeader;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private ButtonsHeader btnsHeader;

    @GetMapping("/User/user")
    public String user(Model model) {
        btnsHeader.hideBtnHeader(model,"profile");
        return "User/user";
    }

    @GetMapping("/User/pedidosUsuario")
    public String pedidosUsuario() {
        return "User/pedidosUsuario";
    }

    @GetMapping("/User/configuration")
    public String configuration() {
        return "User/configuration";
    }

    @GetMapping("/User/editProfile")
    public String editProfile() {
        return "User/editProfile";
    }


}
