package es.codeurjc.board.service;

import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.Reviews;
import es.codeurjc.board.repositories.ReviewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;

    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    public Page<Reviews> findAll(Pageable page) {
        return reviewsRepository.findAll(page);
    }

    public long count() {
        return reviewsRepository.count();
    }

    public Reviews findById(long id) {
        return reviewsRepository.findById(id).orElseThrow();
    }

    public Reviews save(Reviews review) {
        return reviewsRepository.save(review);
    }

    public void deleteById(long id) {
        reviewsRepository.deleteById(id);
    }


    public void editReview(String title, String description, Long id) throws Exception{
        Reviews review = reviewsRepository.findById(id).orElseThrow();
        if (title != null && !title.isBlank()){
            review.setTitle(title);
        }
        if (description !=null && !description.isBlank()){
            review.setDescription(description);
        }
        reviewsRepository.save(review);
    }

    public List<Reviews> findByType(Reviews.ReviewType type) {
        return reviewsRepository.findByType(type);
    }

    public long numberOfOrders() {
        return reviewsRepository.count();
    }
}