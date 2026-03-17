package es.codeurjc.board.repositories;

import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.Reviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    List<Reviews> findByType(Reviews.ReviewType type);
    Page<Reviews> findAll(Pageable page);

    Optional<Reviews> findById (long id);

    public void deleteById(long id);
}