package es.codeurjc.board.service;

import es.codeurjc.board.model.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;



@Service
public class SampleDataService {

    @Autowired
    private PlantService plantRepository;

    @PostConstruct
    public void init() {
        for(int i = 0; i < 5; i++) {
            Plant plant = new Plant("Pepito", "Rosa", "Regar cada 5 días", "Necesita abono x");

            plantRepository.save(plant);
        }
        for(int i = 0; i < 5; i++) {
            Plant plant = new Plant("Pepito", "Rosa2", "Regar cada 5 días", "Necesita abono x");

            plantRepository.save(plant);
        }

    }
}

