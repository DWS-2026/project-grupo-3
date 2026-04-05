package es.codeurjc.board.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import es.codeurjc.board.model.PlantType;
import java.util.Optional;


public interface PlantTypeRepository extends JpaRepository<PlantType, Long> {
    Optional<PlantType> findBySpecies(String species);
}
