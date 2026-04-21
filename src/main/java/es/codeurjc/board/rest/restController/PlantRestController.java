package es.codeurjc.board.rest.restController;

import java.io.IOException;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.codeurjc.board.rest.dto.PlantBasicDTO;
import es.codeurjc.board.rest.dto.PlantExtendedDTO;
import es.codeurjc.board.rest.mapper.PlantMapper;
import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Plant;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.PlantService;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/plants")
public class PlantRestController {
    @Autowired
    private PlantMapper mapper;

    @Autowired
    private ButtonsHeader btnsHeader;

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    

    @GetMapping("/")
    public List<Plant> getItemRepository(@RequestParam(required = false) String search, @PageableDefault(size = 6) Pageable page,
                                HttpServletRequest session, @RequestParam(required = false) String whatToShow, @RequestParam(required = false) String order) {
        Pageable sortedPage = PageRequest.of(page.getPageNumber(), 6, plantService.sortPlants(order));
        if(userService.isUserAdmin(session) || userService.isUserUser(session)) {
            Page<Plant> plantsPage = plantService.returnPlantsDependingInput(userService.getUser(session).getUsername(),
                    userService.isUserUser(session), whatToShow, search, sortedPage);
            return plantsPage.getContent();
        } else{
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public Plant deletePlant(@PathVariable long id,HttpServletRequest session){
        Plant plant = plantService.findById(id);
        if(plantService.seeIfPlantBelongsToUser(plant,userService.getUser(session)) || userService.isUserAdmin(session)){
            plantService.deleteById(id);
            return plant;
        }else{
            return null;
        }
    }
    @PostMapping("/{id}")
    public Plant editPlant(@PathVariable Long id, HttpServletRequest session) {
        Plant plant = plantService.findById(id);
        if(plantService.seeIfPlantBelongsToUser(plant,userService.getUser(session))){
            return plant;
        }else{
            return null;
        }
    }

	@GetMapping("/{id}")
	public PlantExtendedDTO getPlant(@PathVariable long id,  HttpServletRequest session) {
            Plant plant = plantService.findById(id);
        return mapper.extendedToDTO(plant);
            

	}

    

   
}
