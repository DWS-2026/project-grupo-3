package es.codeurjc.board.service;

import es.codeurjc.board.model.PlantType;
import es.codeurjc.board.model.Product;
import es.codeurjc.board.model.Review;
import es.codeurjc.board.model.User;
import es.codeurjc.board.repositories.ReviewsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
@Transactional
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private PlantTypeService plantTypeService;

    @Autowired
    private ProductService productService;

    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    public Page<Review> findAll(Pageable page) {
        return reviewsRepository.findAll(page);
    }

    public long count() {
        return reviewsRepository.count();
    }

    public Review findById(long id) {
        return reviewsRepository.findById(id).orElseThrow();
    }

    public void deleteById(long id) {
        reviewsRepository.deleteById(id);
    }


    public boolean editReview(String title, String description, String productOrplant, Long id) throws Exception{
        Review review = reviewsRepository.findById(id).orElseThrow();

        boolean associationOk = false;
        if(productOrplant != null && !productOrplant.isBlank()){
            if ("PLANT".equals(review.getType().name())) {
                associationOk = this.handlePlant(review, productOrplant);
            } else if ("PRODUCT".equals(review.getType().name())) {
                associationOk = this.handleProduct(review, productOrplant);
            }
            
        }  
        if(associationOk){
            if (title != null && !title.isBlank()){
            review.setTitle(title);
            }
            if (description !=null && !description.isBlank()){
                review.setDescription(description);
            } 
        }
         
            
        return associationOk;
    }

    public List<Review> findByType(Review.ReviewType type) {
        return reviewsRepository.findByType(type);
    }

    public long numberOfOrders() {
        return reviewsRepository.count();
    }

    public List<Review> findByUserAndType(User user, Review.ReviewType type) {
        return reviewsRepository.findByUserAndType(user, type);
    }
        public List<Review> findByUser(User user) {
        return reviewsRepository.findByUser(user);
    }
    public void save(Review review, User user) {
            review.setUser(user);
            user.addReview(review);
            reviewsRepository.save(review);
    }

    public void intialSave(Review review, User user, String productOrplant) {

            if ("PLANT".equals(review.getType().name())) {
                plantTypeService.getOrCreateType(productOrplant);
                this.handlePlant(review, productOrplant);
            } else if ("PRODUCT".equals(review.getType().name())) {
                this.handleProduct(review, productOrplant);
            }
            review.setUser(user);
            user.addReview(review);
            reviewsRepository.save(review);
    }

    public boolean handlePlant(Review review, String productOrplant) {
        PlantType species = plantTypeService.findBySpecies(productOrplant);
        if (species == null) {
            return false;
        }else{
            review.setPlantType(species);
            review.setProduct(null);
            return true;
        }

    }
        public boolean handleProduct(Review review, String productOrplant) {
            Product product = productService.findByNameProduct(productOrplant);

            if (product == null) {
                return false;
            }else{
                review.setProduct(product);
                review.setPlantType(null);
                return true;
            }


        }
}