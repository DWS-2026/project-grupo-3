package es.codeurjc.board.repositories;


import es.codeurjc.board.model.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant,Long> {
    Page<Plant> findPlantsByUserUsername(String username, Pageable pageable);
    boolean existsByNameIgnoreCase(String name);
    Optional<Plant> findById(long id);
}
