package es.codeurjc.board.service;

import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Plant;
import es.codeurjc.board.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PlantService {
    @Autowired
    private PlantRepository plantRepository;

        public List<Plant> findAll() {
            return plantRepository.findAll();
        }

        public Plant findById(long id) {
            return plantRepository.findById(id).orElseThrow();
        }

        public void save(Plant plant) {
            plantRepository.save(plant);
        }

        public void deleteById(long id) {
            plantRepository.deleteById(id);
        }

    public Plant addImageToPlant(long id, Image image) {
        Plant plant = plantRepository.findById(id).orElseThrow();

        plant.addImage(image);

        return plantRepository.save(plant);
    }

}
