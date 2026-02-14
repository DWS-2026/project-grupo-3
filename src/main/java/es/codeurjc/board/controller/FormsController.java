package es.codeurjc.board.controller;

import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.Post;
import es.codeurjc.board.model.SessionAttributes;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.PlantService;
import es.codeurjc.board.service.PostService;
import es.codeurjc.board.service.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@Controller
public class FormsController {
    private static final String PLANTS_FOLDER = "plants";
    @Autowired
    private PlantService plantService;

    @Autowired
    private UserSession userSession;

    @Autowired
    private ImageService imageService;

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
