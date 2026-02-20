package es.codeurjc.board.repositories;


import es.codeurjc.board.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant,Long> {
    List<Plant> findAll();
    Plant findAllById(Long id);

    @Override
    void deleteById(Long aLong);
}
