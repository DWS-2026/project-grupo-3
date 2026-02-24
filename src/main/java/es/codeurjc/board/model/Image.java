package es.codeurjc.board.model;

import jakarta.persistence.*;

import java.io.InputStream;
import java.sql.Blob;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import org.springframework.core.io.ClassPathResource;

import javax.sql.rowset.serial.SerialBlob;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Plant plant;

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    @Lob
    private Blob imageFile;

    private Blob loadImageAsBlob(String path) throws Exception {

        ClassPathResource resource = new ClassPathResource(path);

        try (InputStream inputStream = resource.getInputStream()) {

            byte[] bytes = inputStream.readAllBytes();

            return new SerialBlob(bytes);
        }
    }

    public Image() {
    }

    public Image(String source) throws Exception {
        this.imageFile = this.loadImageAsBlob(source);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }

    @Override
    public String toString() {
        return "Image [id=" + id + "]";
    }
}
