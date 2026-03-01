package es.codeurjc.board.service;

import es.codeurjc.board.model.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;



@Service
public class SampleDataService {

    @Autowired
    private PlantService plantService;

    @PostConstruct
    public void init() throws Exception {
        for(int i = 0; i < 5; i++) {
            Plant plant = new Plant("Pepito", "Rosa-" + i, "Regar cada 5 días", "Necesita abono x y la tengo mucho cariño mimimimimimimj",false);
            plantService.save(plant);
            plantService.addImageToPlant(plant.getId(), "/static/assets/images/plants/rosebush.jpg");

        }
        for(int i = 0; i < 5; i++) {
            Plant plant = new Plant("Pepito", "Rosa2-" + i, "Regar cada 5 días", "Necesita abono x",false);
            plantService.save(plant);
            plantService.addImageToPlant(plant.getId(), "/static/assets/images/plants/rosebush.jpg");

        }
        Plant plant_ex1 = new Plant("anonimo","Plantita ejemplo 1: Suculenta","Regar cada 5 días",
                "Plantita que no requiere de mucha atención parecida al cactus.",true);
        plantService.save(plant_ex1);
        plantService.addImageToPlant(plant_ex1.getId(), "/static/assets/images/plants/succulent.webp");
        plantService.addImageToPlant(plant_ex1.getId(), "/static/assets/images/plants/succulent2.jpg");

        Plant plant_ex2 = new Plant("anonimo","Plantita ejemplo 2: Rosal","Regar cada 5 días",
                "Plantita que no requiere de mucha atención parecida al cactus.",true);
        plantService.save(plant_ex2);
        plantService.addImageToPlant(plant_ex2.getId(), "/static/assets/images/plants/rosebush.jpg");

    }
}

