package es.codeurjc.board.service;

import es.codeurjc.board.model.Reviews;
import es.codeurjc.board.repositories.ReviewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Reviews> findByType(Reviews.ReviewType type, Pageable page){
        return reviewsRepository.findByType(type, page);
    }
}