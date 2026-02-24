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
            Plant plant = new Plant("Pepito", "Rosa", "Regar cada 5 días", "Necesita abono x");
            plantService.save(plant);
            plantService.addImageToPlant(plant.getId(), "/static/assets/images/plants/rosebush.jpg");

        }
        for(int i = 0; i < 5; i++) {
            Plant plant = new Plant("Pepito", "Rosa2", "Regar cada 5 días", "Necesita abono x");
            plantService.save(plant);
            plantService.addImageToPlant(plant.getId(), "/static/assets/images/plants/rosebush.jpg");

        }
    }
}

