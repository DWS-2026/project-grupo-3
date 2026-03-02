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

        if(principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    private void openOrCloseSession(Model model, HttpSession session){
        boolean isSessionActive;

        if(session.getAttribute("isSessionActive") != null){
            isSessionActive = (boolean) session.getAttribute("isSessionActive");
        } else{
            isSessionActive = false;
        }
        for(String attribute : attributes) {
                model.addAttribute(attribute, isSessionActive);
        }
    }

    @ModelAttribute
    public void sessionAttributes(Model model, HttpSession session) {
        this.openOrCloseSession(model, session);

    }

    public static void hideAdminPanel(Model model){
        model.addAttribute("adminPanel", false );
    }

}
