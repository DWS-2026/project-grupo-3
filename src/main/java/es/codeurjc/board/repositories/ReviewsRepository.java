package es.codeurjc.board.repositories;

import es.codeurjc.board.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import es.codeurjc.board.model.User;
import java.util.List;
import java.util.Optional;


public interface ReviewsRepository extends JpaRepository<Review, Long> {

    List<Review> findByType(Review.ReviewType type);

    List<Review> findByUserAndType(User user, Review.ReviewType type);
    Page<Review> findAll(Pageable page);

    
    Optional<Review> findById (long id);

    public void deleteById(long id);

    List<Review> findByUser(User user);
}