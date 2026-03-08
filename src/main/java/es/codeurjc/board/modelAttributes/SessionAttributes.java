package es.codeurjc.board.modelAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;

@ControllerAdvice
public class SessionAttributes {
    private List<String> attributes = List.of("loginOptions", "userOnly","adminOnly", "adminPanel");
    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        System.out.println("URI: " + request.getRequestURI());
        if(principal != null) {
            model.addAttribute("loginOptions", true);
            //model.addAttribute("userName", principal.getName());
            model.addAttribute("adminOnly", request.isUserInRole("ADMIN"));
            model.addAttribute("userOnly", request.isUserInRole("USER"));

        } else {
            model.addAttribute("loginOptions", false);
            model.addAttribute("userOnly", false);
            model.addAttribute("adminOnly", false);
        }
    }


    public static void hideAdminPanel(Model model){
        model.addAttribute("adminPanel", false );
    }

}
