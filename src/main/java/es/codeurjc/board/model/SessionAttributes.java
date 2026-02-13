package es.codeurjc.board.model;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class SessionAttributes {
    private List<String> attributes = List.of("loginOptions", "userOnly");
//cambiar en un futuro de httpSession a una sesion como tal de un usuario legítimo que además te diga si es admin o no
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
        model.addAttribute("adminOnly", false);
    }

    @ModelAttribute
    public void sessionAttributes(Model model, HttpSession session) {
        this.openOrCloseSession(model, session);

    }


}
