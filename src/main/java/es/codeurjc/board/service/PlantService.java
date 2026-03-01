package es.codeurjc.board.service;

import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Plant;
import es.codeurjc.board.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlantService {
    @Autowired
    private PlantRepository plantRepository;

        public Page<Plant> findAll(Pageable page) {
            return plantRepository.findAll(page);
        }
        public long count() {
            return plantRepository.count();
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
    @Transactional
    public Plant addImageToPlant(long id, Image image) {
        Plant plant = plantRepository.findById(id).orElseThrow();

        plant.addImage(image);

        return plantRepository.save(plant);
    }
    @Transactional
    public Plant addImageToPlant(long id, String source) throws Exception {
        Plant plant = plantRepository.findById(id).orElseThrow();
        Image newImage = new Image(source);
        plant.addImage(newImage);

        return plantRepository.save(plant);
    }
    public void editPlant(String name, String cares, String description, long id) throws Exception{
            Plant plant = plantRepository.findById(id).orElseThrow();
            if(name != null && !name.isBlank()){
                plant.setName(name);
            }
            if(cares != null && !cares.isBlank()){
                plant.setCares(cares);
            }
            if(description != null && !description.isBlank()){
                plant.setDescription(description);
            }
            plantRepository.save(plant);

    }
    public void deleteImageFromPlant(long plantId, long imageId) {

        Plant plant = plantRepository.findById(plantId).orElseThrow();

        plant.getImages().removeIf(image -> image.getId() == imageId);

        plantRepository.save(plant);         // orphanRemoval deletes the image that now is without a parent

    }

    public Page<Plant> findByIsExample(boolean isExample, Pageable page) {
            return plantRepository.findByExampleEquals(isExample, page);
    }

}
