package es.codeurjc.board.controller;
import es.codeurjc.board.model.Image;
import es.codeurjc.board.modelAttributes.ButtonsHeader;

import es.codeurjc.board.model.Plant;
import es.codeurjc.board.repositories.PlantRepository;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.PlantService;
import es.codeurjc.board.service.UserSession;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PlantController {
    @Autowired
    private ButtonsHeader btnsHeader;


    @PostConstruct
    public void init() {
    }
    public static final String PLANTS_FOLDER = "plants";

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserSession userSession;

    @Autowired
    private ImageService imageService;


    @GetMapping("/Plants/catalogPlants")
    public String catalogoPlantas(Model model,  @PageableDefault(size = 5) Pageable page) {
        btnsHeader.hideBtnHeader(model,"plantIcon");
        Page<Plant> plantsPage = plantService.findAll(page);

        model.addAttribute("plants", plantsPage);

        model.addAttribute("hasPrev", plantsPage.hasPrevious());
        model.addAttribute("prev", plantsPage.getNumber() - 1);

        model.addAttribute("hasNext", plantsPage.hasNext());
        model.addAttribute("next", plantsPage.getNumber() + 1);
        return "/Plants/catalogPlants";
    }

    @GetMapping("/Plants/newPlant")
    public String newPlant() {
        return "Plants/newPlant";
    }

    @GetMapping("/Plants/editPlant/{id}")
    public String editPlant(Model model, @PathVariable Long id) {

        Plant plant = plantService.findById(id);
        model.addAttribute("plant", plant);
        return "Plants/editPlant";
    }

    @PostMapping("/Plants/new")
    public String newPost(Model model, Plant plant, MultipartFile imageFile) throws IOException {
        plantService.save(plant);

        if (!imageFile.isEmpty()) {
            Image imageOne = imageService.createImage(imageFile);
            plantService.addImageToPlant(plant.getId(), imageOne);
        }



        return "redirect:/Plants/catalogPlants";
    }
/*
    @GetMapping("/plants/{id}")
    public String showPost(Model model, @PathVariable long id) {

        Plant plant =    plantService.findById(id);

        model.addAttribute("plant", plant);

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
    }*/


}
