package es.codeurjc.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import es.codeurjc.board.model.PlantType;
import es.codeurjc.board.repositories.PlantTypeRepository;

@Service
public class PlantTypeService {
    @Autowired
    private PlantTypeRepository plantTypeRepository;

    public PlantType getOrCreateType(String name) {
        String normalized = name.toLowerCase().trim();

        return plantTypeRepository.findBySpecies(normalized)
            .orElseGet(() -> {
                PlantType newType = new PlantType(normalized);
                return plantTypeRepository.save(newType);
            });
    }

    public PlantType findBySpecies(String species){
        return plantTypeRepository.findBySpecies(species).orElse(null);
    }
}
