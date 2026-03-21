package es.codeurjc.board.service;

import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.User;
import es.codeurjc.board.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PlantService {
    @Autowired
    private PlantRepository plantRepository;

        public Page<Plant> findByUsername(String username,Pageable page) {
            return plantRepository.findPlantsByUserUsername(username, page);
        }
        public Page<Plant> findAll(Pageable page) {
            return plantRepository.findAll(page);
        }
        public long count() {
            return plantRepository.count();
        }

        public Plant findById(long id) {
            return plantRepository.findById(id).orElseThrow();
        }

        public void save(Plant plant, User username) {
            plant.setUser(username);
            username.addPlant(plant);
            plantRepository.save(plant);
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


    public void ratePlant(int rating,long id) {
            Plant plant = plantRepository.findById(id).orElseThrow();
            plant.setRating(rating);
            plantRepository.save(plant);
    }

    public boolean existsByNamePlant(String name){
            return plantRepository.existsByNameIgnoreCase(name);
    }
    public Page<Plant> searchByNamePlantAndFilterByUser(String name, String user, Pageable pageable) {
        return plantRepository.findByNameContainingIgnoreCaseAndUserUsername(name,user,pageable);
    }
    public Page<Plant> searchByNamePlant(String name, Pageable pageable) {
        return plantRepository.findByNameContainingIgnoreCase(name,pageable);
    }

    public Sort sortPlants(String order){
        Sort sort;
        if ("moreRecent".equals(order)) {
            sort = Sort.by(Sort.Order.desc("createdAt"));
        } else {
            sort = Sort.by(Sort.Order.desc("rating"));
        }
        return sort;
    }

    public Page<Plant> returnPlantsDependingInput(String username, boolean isUserInRoleUser, String whatToShow,
                                                  String search, Pageable sortedPage){
        Page<Plant> plantsPage;
        if(isUserInRoleUser && "misPlantas".equals(whatToShow)){
            if(search == null){
                plantsPage = this.findByUsername(username,sortedPage);
            }else {
                plantsPage = this.searchByNamePlantAndFilterByUser(search, username, sortedPage);
            }
        } else {
            if(search == null){
                plantsPage = this.findAll(sortedPage);
            }else {
                plantsPage = this.searchByNamePlant(search,sortedPage);
            }
        }
        return plantsPage;
    }

    public boolean seeIfPlantBelongsToUser(Plant plant, User user) {
            return user.equals(plant.getUser());
    }

}
