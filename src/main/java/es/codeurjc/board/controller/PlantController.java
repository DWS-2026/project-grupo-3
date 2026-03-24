package es.codeurjc.board.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String showPost(Model model, @PathVariable long id, HttpServletRequest session) {
        Plant plant = plantService.findById(id,model);

            return "Plants/viewPlant";



    }

    @GetMapping("/catalogPlants")
    public String catalogPlants(Model model, @RequestParam(required = false) String search, @PageableDefault(size = 6) Pageable page,
                                HttpServletRequest session, @RequestParam(required = false) String whatToShow, @RequestParam(required = false) String order) {
        btnsHeader.hideBtnHeader(model,"plantIcon");
        Pageable sortedPage = PageRequest.of(page.getPageNumber(), 6, plantService.sortPlants(order, model));
        if(userService.getUser(session) != null){
            plantService.returnPlantsDependingInput(userService.getUser(session).getUsername(), userService.isUserUser(session),
                    userService.isUserAdmin(session), whatToShow, search, sortedPage, model);
        } else{
            plantService.returnPlantsDependingInput(null, false, false,
                   whatToShow, search, sortedPage, model);
        }


        return "Plants/catalogPlants";
    }


    @GetMapping("/editPlant/{id}")
    public String editPlant(Model model, @PathVariable Long id, HttpServletRequest session) {
        Plant plant = plantService.findById(id, model);
        if(plantService.seeIfPlantBelongsToUser(plant,userService.getUser(session))){
            return "Plants/editPlant";
        }else{
            return "/accessDenied";
        }
    }

    @PostMapping("/editPlant/{id}")
    public String editPlant(@PathVariable Long id, @RequestParam String name, @RequestParam String cares,
                            @RequestParam String description, HttpServletRequest session, Model model) throws Exception {

        Plant plant = plantService.findById(id, model);
        if(plantService.seeIfPlantBelongsToUser(plant,userService.getUser(session))){
            plantService.editPlant(name, cares , description, id);
            return "redirect:/Plants/catalogPlants";
        }else{
            return "/accessDenied";
        }
    }
    @GetMapping("/new")
    public String newPlant() {
        return "Plants/newPlant";
    }

    @PostMapping("/new")
    public String newPlant(Plant plant, MultipartFile imageFile, HttpServletRequest session,
                           RedirectAttributes redirectAttributes, Model model) throws IOException {

        if(plantService.existsByNamePlant(plant.getName())){
            plantService.showSavedFormsNewPlant(model, plant,redirectAttributes);
            return "redirect:/Plants/new";
        }
        plantService.save(plant,userService.getUser(session));

        if (!imageFile.isEmpty()) {
            Image imageOne = imageService.createImage(imageFile);
            plantService.addImageToPlant(plant.getId(), imageOne);
        }
        return "redirect:/Plants/catalogPlants";
    }

    @PostMapping("/{id}/addImageToPlant")
    public String addImageToPlant(@PathVariable long id, MultipartFile newImage, HttpServletRequest session, Model model) throws IOException {
        Plant plant = plantService.findById(id,model);
        if(plantService.seeIfPlantBelongsToUser(plant,userService.getUser(session))){
            if (!newImage.isEmpty()) {
                Image imageOne = imageService.createImage(newImage);
                plantService.addImageToPlant(id, imageOne);
            }

            return "redirect:/Plants/editPlant/" + id;
        }else{
            return "/accessDenied";
        }

    }

    @PostMapping("{plantId}/delete/image/{imageId}")
    public String deleteImage(@PathVariable long plantId, @PathVariable long imageId, HttpServletRequest session,Model model){

        Plant plant = plantService.findById(plantId, model);
        if(plantService.seeIfPlantBelongsToUser(plant,userService.getUser(session))){
            plantService.deleteImageFromPlant(plantId, imageId);
            return "redirect:/Plants/catalogPlants";
        }else{
            return "/accessDenied";
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePlant(@PathVariable long id, HttpServletRequest session, Model model) throws IOException {
        Plant plant = plantService.findById(id, model);
        if(plantService.seeIfPlantBelongsToUser(plant,userService.getUser(session))){
            plantService.deleteById(id);
            return "redirect:/Plants/catalogPlants?type=misPlantas";
        }else{
            return "/accessDenied";
        }

    }
    @PostMapping("/ratingPlant")
    public String ratePlant(@RequestParam("plantId") Long plantId,
                            @RequestParam("rating") int rating, Model model) {

        Plant plant = plantService.findById(plantId, model);
        if(plant != null) {
            plant.setTotalRating(plant.getTotalRating() + rating);
            plant.setCount(plant.getCount() + 1);
            plant.setRating(plant.getTotalRating() / plant.getCount());
            plantService.save(plant);
        }

        return "redirect:/Plants/catalogPlants";
    }
}
