package es.codeurjc.board.controller;
import es.codeurjc.board.model.Image;
import es.codeurjc.board.modelAttributes.ButtonsHeader;

import es.codeurjc.board.model.Plant;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.PlantService;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/Plants")
public class PlantController {

    @Autowired
    private ButtonsHeader btnsHeader;

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/viewPlant/{id}")
    public String showPost(Model model, @PathVariable long id) {

        Plant plant = plantService.findById(id);
        model.addAttribute("plant", plant);

        return "Plants/viewPlant";
    }

    @GetMapping("/catalogPlants")
    public String catalogPlants(Model model, @PageableDefault(size = 6) Pageable page, HttpServletRequest session) {

        btnsHeader.hideBtnHeader(model,"plantIcon");

        Pageable sortedPage = PageRequest.of(
                page.getPageNumber(),6, Sort.by(Sort.Order.desc("favorite"),Sort.Order.desc("rating")));

        Page<Plant> plantsPage;
        if(userService.seeIfUserIsLoggedIn(session)){
            plantsPage = plantService.findByIsExample(false, sortedPage);
        } else {
            plantsPage = plantService.findByIsExample(true, sortedPage);
        }


        model.addAttribute("plants", plantsPage.getContent());
        model.addAttribute("hasPrev", plantsPage.hasPrevious());
        model.addAttribute("prev", plantsPage.getNumber() - 1);
        model.addAttribute("hasNext", plantsPage.hasNext());
        model.addAttribute("next", plantsPage.getNumber() + 1);

        return "Plants/catalogPlants";
    }

    @GetMapping("/newPlant")
    public String newPlant() {
        return "Plants/newPlant";
    }

    @GetMapping("/editPlant/{id}")
    public String editPlant(Model model, @PathVariable Long id) {

        Plant plant = plantService.findById(id);
        model.addAttribute("plant", plant);
        model.addAttribute("plantID", plant.getId());

        return "Plants/editPlant";
    }

    @PostMapping("/editPlant/{id}")
    public String editPlant(@PathVariable Long id,
                            @RequestParam String name,
                            @RequestParam String cares,
                            @RequestParam String description) throws Exception {

        plantService.editPlant(name, cares , description, id);

        return "redirect:/Plants/catalogPlants";
    }

    @PostMapping("/new")
    public String newPost(Plant plant, MultipartFile imageFile) throws IOException {

        plantService.save(plant);

        if (!imageFile.isEmpty()) {
            Image imageOne = imageService.createImage(imageFile);
            plantService.addImageToPlant(plant.getId(), imageOne);
        }

        return "redirect:/Plants/catalogPlants";
    }

    @PostMapping("/addImageToPlant/{id}")
    public String addImageToPlant(@PathVariable long id,
                                  MultipartFile newImage) throws IOException {

        if (!newImage.isEmpty()) {
            Image imageOne = imageService.createImage(newImage);
            plantService.addImageToPlant(id, imageOne);
        }

        return "redirect:/Plants/editPlant/" + id;
    }

    @PostMapping("/delete/image/{plantId}/{imageId}")
    public String deleteImage(@PathVariable long plantId,
                              @PathVariable long imageId) {

        plantService.deleteImageFromPlant(plantId, imageId);

        return "redirect:/Plants/catalogPlants";
    }

    @PostMapping("/ratingPlant/{id}")
    public String ratingPlant(@PathVariable long id,
                              @RequestParam int rate) {

        plantService.ratePlant(rate,id);
        return "redirect:/Plants/catalogPlants";
    }

    @PostMapping("/favoritePlant/{id}")
    public String favoritePlant(@PathVariable long id) {

        plantService.favoritePlant(id);
        return "redirect:/Plants/catalogPlants";
    }

    @PostMapping("/{id}/delete")
    public String deletePlant(@PathVariable long id) throws IOException {

        plantService.deleteById(id);
        return "redirect:/Plants/catalogPlants";
    }
}
