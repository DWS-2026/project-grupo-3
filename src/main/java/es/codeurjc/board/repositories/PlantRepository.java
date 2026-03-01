package es.codeurjc.board.repositories;


import es.codeurjc.board.model.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant,Long> {
    Page<Plant> findAll(Pageable page);
    Page<Plant> findByExampleEquals(boolean example, Pageable page);
}
