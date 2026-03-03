package es.codeurjc.board.repositories;

import es.codeurjc.board.model.Reviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    Page<Reviews> findByType (Reviews.ReviewType type, Pageable page);
}