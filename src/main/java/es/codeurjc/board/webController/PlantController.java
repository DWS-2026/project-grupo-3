package es.codeurjc.board.webController;

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
import java.util.Optional;
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
        Optional<Plant> plant = plantService.findById(id);
        if(plant.isPresent()){
            model.addAttribute("plant", plant.get());
            return "Plants/viewPlant";
        }else{
            return "/error";
        }
            



    }





    @GetMapping("/catalogPlants")
    public String catalogPlants(Model model, @RequestParam(required = false) String search, @PageableDefault(size = 6) Pageable page,
                                HttpServletRequest session, @RequestParam(required = false) String whatToShow, @RequestParam(required = false) String order) {
        btnsHeader.hideBtnHeader(model,"plantIcon");
        Pageable sortedPage = PageRequest.of(page.getPageNumber(), 6, plantService.sortPlants(order));
        if(userService.isUserAdmin(session) || userService.isUserUser(session)) {
            Page<Plant> plantsPage = plantService.returnPlantsDependingInput(userService.getUser(session).getUsername(),
                    userService.isUserUser(session), whatToShow, search, sortedPage);
            model.addAttribute("plants", plantsPage.getContent());
            model.addAttribute("hasPrev", plantsPage.hasPrevious());
            model.addAttribute("prev", plantsPage.getNumber() - 1);
            model.addAttribute("hasNext", plantsPage.hasNext());
            model.addAttribute("next", plantsPage.getNumber() + 1);
        } else {
            model.addAttribute("example",true);
        }

        if("moreRecent".equals(order)){model.addAttribute("isCreatedAtSort", true);}

        if(search != null) { model.addAttribute("search", search);}

        if(userService.isUserUser(session) && "misPlantas".equals(whatToShow)){model.addAttribute("editPlant", true);}

        return "Plants/catalogPlants";
    }


    @GetMapping("/editPlant/{id}")
    public String editPlant(Model model, @PathVariable Long id, HttpServletRequest session) {
        Optional <Plant> plantOp = plantService.findById(id);
        if(plantOp.isPresent() && plantService.seeIfPlantBelongsToUser(plantOp.get(),userService.getUser(session))){
            Plant plant = plantOp.get();
            model.addAttribute("plant", plant);
            model.addAttribute("plantID", plant.getId());
            return "Plants/editPlant";
        }else{
            return "/accessDenied";
        }
    }

    @PostMapping("/editPlant/{id}")
    public String editPlant(RedirectAttributes redirectAttributes,@PathVariable Long id, @RequestParam String name, @RequestParam String cares,
                            @RequestParam String description, @RequestParam String species,HttpServletRequest session) throws Exception {

        Optional<Plant> plant = plantService.findById(id);
        if(!plantService.existsByNamePlant(name) && plantService.seeIfPlantBelongsToUser(plant.get(),userService.getUser(session))){
            plantService.editPlant(name, cares , description, id, species);
            return "redirect:/Plants/editPlant/" + id;
        }else if (plantService.existsByNamePlant(name)){
            redirectAttributes.addFlashAttribute("plantNameExists", true);
            return "redirect:/Plants/editPlant/" + id;
        } else{
            return "/accessDenied";

        }
    }
    @GetMapping("/new")
    public String newPlant() {
        return "Plants/newPlant";
    }

    @PostMapping("/new")
    public String newPlant(@RequestParam String name, @RequestParam String description, @RequestParam String cares, MultipartFile imageFile, @RequestParam String type, HttpServletRequest session, RedirectAttributes redirectAttributes) throws IOException {

        if(plantService.existsByNamePlant(name)){ //if it already exist that plant
            redirectAttributes.addFlashAttribute("plantNameExists", true);
            redirectAttributes.addFlashAttribute("cares",cares);
            redirectAttributes.addFlashAttribute("description", description);
            return "redirect:/Plants/new";
        }
        Plant plant = new Plant(name, cares,description);
        plantService.save(plant,userService.getUser(session), type);

        if (imageFile!=null && !imageFile.isEmpty()) {
            Image imageOne = imageService.createImage(imageFile);
            plantService.addImageToPlant(plant.getId(), imageOne);
        }
        

        return "redirect:/Plants/catalogPlants";
    }

    @PostMapping("/{id}/addImageToPlant")
    public String addImageToPlant(@PathVariable long id, MultipartFile newImage, HttpServletRequest session) throws IOException {
        Optional<Plant> plant = plantService.findById(id);
        if(plant.isPresent() && plantService.seeIfPlantBelongsToUser(plant.get(),userService.getUser(session))){
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
    public String deleteImage(@PathVariable long plantId, @PathVariable long imageId, HttpServletRequest session){

        Optional<Plant> plant = plantService.findById(plantId);
        if(plant.isPresent() && plantService.seeIfPlantBelongsToUser(plant.get(),userService.getUser(session))){
            plantService.deleteImageFromPlant(plantId, imageId);
            return "redirect:/Plants/catalogPlants";
        }else{
            return "/accessDenied";
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePlant(@PathVariable long id, HttpServletRequest session) throws IOException {
        Optional<Plant> plant = plantService.findById(id);
        if(plant.isPresent() && plantService.seeIfPlantBelongsToUser(plant.get(),userService.getUser(session))){
            plantService.deleteById(id);
            return "redirect:/Plants/catalogPlants";
        }else if(userService.isUserAdmin(session)){
            plantService.deleteById(id);
            return "redirect:/Plants/catalogPlants";
        }else{
            return "/accessDenied";
        }

    }
    @PostMapping("/ratingPlant")
    public String ratePlant(@RequestParam("plantId") Long plantId,
                            @RequestParam("rating") int rating) {

        Optional<Plant> plantOp = plantService.findById(plantId);
        if(plantOp.isPresent()) {
            Plant plant = plantOp.get();
            plant.setTotalRating(plant.getTotalRating() + rating);
            plant.setCount(plant.getCount() + 1);
            plant.setRating(plant.getTotalRating() / plant.getCount());
            plantService.save(plant);
        }

        return "redirect:/Plants/catalogPlants";
    }
}
