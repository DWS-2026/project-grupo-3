package es.codeurjc.board.service;

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


    public void editReview(String title, String description, Long id) throws Exception{
        Review review = reviewsRepository.findById(id).orElseThrow();
        if (title != null && !title.isBlank()){
            review.setTitle(title);
        }
        if (description !=null && !description.isBlank()){
            review.setDescription(description);
        }
        reviewsRepository.save(review);
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
}