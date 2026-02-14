package es.codeurjc.board.controller;
import es.codeurjc.board.model.ButtonsHeader;

import es.codeurjc.board.model.Plant;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.PlantService;
import es.codeurjc.board.service.UserSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Controller
public class PlantController {
    @Autowired
    private ButtonsHeader btnsHeader;
    private static final String PLANTS_FOLDER = "plants";

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserSession userSession;

    @Autowired
    private ImageService imageService;


    @GetMapping("/Plantas/catalogoPlantas")
    public String catalogoPlantas(Model model) {
        btnsHeader.hideBtnHeader(model,"plants");
        List<Plant> plants = new ArrayList<>(plantService.findAll());
        model.addAttribute("plants", plants);
        return "Plantas/catalogoPlantas";
    }

    @GetMapping("/Plantas/nuevaPlanta")
    public String nuevaPlanta() {
        return "Plantas/nuevaPlanta";
    }

    @GetMapping("/Plantas/editPlantita")
    public String editPlantita() {
        return "Plantas/editPlantita";
    }

    @PostMapping("/plants/new")
    public String newPost(Model model, Plant plant, MultipartFile image) throws IOException {

        plantService.save(plant);

        imageService.saveImage(PLANTS_FOLDER, plant.getId(), plant.getCounterImages(),image);

        userSession.setUser(plant.getUser());
        userSession.incNumPosts();

        model.addAttribute("numPosts", userSession.getNumPosts());

        return "Plantas/catalogoPlantas";
    }

    @GetMapping("/plants/{id}")
    public String showPost(Model model, @PathVariable long id) {

        Plant plant =    plantService.findById(id);

        model.addAttribute("plant", plant);

        return "show_post";
    }
/*
    @GetMapping("/plants/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {
        return imageService.createResponseFromImage(PLANTS_FOLDER, id);
    }*/
/*
    @PostMapping("/plants/{id}/delete")
    public String deletePost(Model model, @PathVariable long id) throws IOException {

        plantService.deleteById(id);

        imageService.deleteImage(PLANTS_FOLDER, id);

        return "deleted_post";
    }*/


}
