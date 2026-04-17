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
@RequestMapping("/api/v1/Plants")
public class PlantRestController {
    @Autowired
    private PlantMapper mapper;

    @Autowired
    private ButtonsHeader btnsHeader;

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;


    @GetMapping("/")
    public List<Plant> getItemRepository(Pageable page) {
        Page<Plant> plantsPage = plantService.findAll(page);
        return plantsPage.getContent();
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable long id){
        plantService.deleteById(id);
    }

	@GetMapping("/{id}")
	public PlantExtendedDTO getPost(@PathVariable long id) {
            Plant plant = plantService.findById(id);
            return mapper.extendedToDTO(plant);
	}
    

   
}
