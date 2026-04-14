package es.codeurjc.board.repositories;


import es.codeurjc.board.model.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant,Long> {
    Page<Plant> findPlantsByUserUsername(String username, Pageable pageable);
    boolean existsByNameIgnoreCase(String name);
    Optional<Plant> findById(long id);
    List<Plant> findAll();
    @Query("SELECT p FROM Plant p WHERE LOWER(p.plantType.species) LIKE LOWER(CONCAT('%', :species, '%'))")
    Page<Plant> findByPlantTypeSpeciesContainingIgnoreCase(String species,  Pageable pageable);
    @Query("SELECT p FROM Plant p WHERE LOWER(p.plantType.species) LIKE LOWER(CONCAT('%', :species, '%')) AND p.user.username = :username")
    Page<Plant> findByPlantTypeTypeContainingIgnoreCaseAndUserUsername(String species, String username, Pageable pageable);

}
