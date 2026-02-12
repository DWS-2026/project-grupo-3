package es.codeurjc.board.controller;

import es.codeurjc.board.model.SessionAttributes;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class FormsController {

    @PostMapping("/logOut") //fijarte en como el form ha redirigido
    public String logOut(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/sign-in";
    }
    @PostMapping("/sign-in") //fijarte en como el form ha redirigido
    public String logIn(HttpSession session) {
        session.setAttribute("isSessionActive",true);
        return "redirect:/";
    }
}
