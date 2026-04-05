package es.codeurjc.board.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlantType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; 

    private String species;

    
    public PlantType() {
    }

    public PlantType(String type) {
        this.species = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    

    
}
