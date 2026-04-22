package es.codeurjc.board.rest.restController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus; 
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import es.codeurjc.board.rest.dto.PlantBasicDTO;
import es.codeurjc.board.rest.mapper.PlantMapper;
import es.codeurjc.board.model.Plant;
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
    private PlantService plantService;

    @Autowired
    private UserService userService;

    

    @GetMapping("/")
    public Page<PlantBasicDTO> getItemRepository(@RequestParam(required = false) String search, @PageableDefault(size = 6) Pageable page,
                                HttpServletRequest session, @RequestParam(required = false) String whatToShow, @RequestParam(required = false) String order) {
        Pageable sortedPage = PageRequest.of(page.getPageNumber(), 6, plantService.sortPlants(order));
        if(userService.isUserAdmin(session) || userService.isUserUser(session)) {
            Page<Plant> plantsPage = plantService.returnPlantsDependingInput(userService.getUser(session).getUsername(),
                    userService.isUserUser(session), whatToShow, search, sortedPage);
            return plantsPage.map(mapper::ToDTO);
        } else{
            return Page.empty();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlantBasicDTO> deletePlant(@PathVariable long id,HttpServletRequest session){
        Optional<Plant> plant = plantService.findById(id);
        if(plant.isPresent() || plantService.seeIfPlantBelongsToUser(plant.get(),userService.getUser(session)) || userService.isUserAdmin(session)){
            plantService.deleteById(id);
            return ResponseEntity.ok(mapper.ToDTO(plant.get()));
        }else{
            if(plant.isPresent()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
            }
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlantBasicDTO> editPlant(@PathVariable Long id, HttpServletRequest session, @RequestBody PlantBasicDTO updatedPlant) throws Exception {
        Optional<Plant> plantOp = plantService.findById(id);
        if(plantOp.isPresent() && plantService.seeIfPlantBelongsToUser(plantOp.get(),userService.getUser(session))){
            plantService.editPlant(updatedPlant.name(),updatedPlant.cares(),updatedPlant.description(),id,updatedPlant.species());
            return ResponseEntity.ok(mapper.ToDTO(plantOp.get()));
        }else{
            if(plantOp.isPresent()){
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
            }
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<PlantBasicDTO> newPlant(@RequestBody PlantBasicDTO plantDTO,  HttpServletRequest session){
        if(userService.seeIfUserIsLoggedIn(session) && userService.isUserUser(session)){
            Plant plant = mapper.ToDomain(plantDTO);
            plant.setUser(userService.getUser(session));
            plantService.save(plant, userService.getUser(session), plantDTO.species());
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(plant.getId()).toUri();
            return ResponseEntity.created(location).body(mapper.ToDTO(plant));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
    }

	@GetMapping("/{id}")
	public ResponseEntity<PlantBasicDTO> getPlant(@PathVariable long id,  HttpServletRequest session) {
        Optional<Plant> plant = plantService.findById(id);
        if(plant.isPresent()){
            return ResponseEntity.ok(mapper.ToDTO(plant.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
            

	}

 
    

   
}
