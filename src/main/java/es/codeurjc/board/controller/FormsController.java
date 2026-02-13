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

    @PostMapping("/plants/new")
    public String newPost(Model model, Plant plant, MultipartFile image) throws IOException {

        plantService.save(plant);

        imageService.saveImage(PLANTS_FOLDER, plant.getId(), image);

        userSession.setUser(plant.getUser());
        userSession.incNumPosts();

        model.addAttribute("numPosts", userSession.getNumPosts());

        return "saved_post";
    }





    @GetMapping("/plants/{id}")
    public String showPost(Model model, @PathVariable long id) {

        //Post post = plantService.findById(id);

        //model.addAttribute("post", post);

        return "show_post";
    }

    @GetMapping("/plants/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {

        return imageService.createResponseFromImage(PLANTS_FOLDER, id);
    }

    @PostMapping("/plants/{id}/delete")
    public String deletePost(Model model, @PathVariable long id) throws IOException {

        plantService.deleteById(id);

        imageService.deleteImage(PLANTS_FOLDER, id);

        return "deleted_post";
    }
}
