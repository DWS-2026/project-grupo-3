package es.codeurjc.board.model;

import java.io.IOException;
import java.util.List;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.util.ArrayList;
@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String username;
    private String name;
    private String cares;
    private String description;
    private boolean example = false;
    private boolean favorite = false;
    private int rating = 0;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true) //we don´t need it to be bidirectional, we only need it in one direction
    private List<Image> images = new ArrayList<>();

    public boolean isExample() {
        return example;
    }

    public void setExample(boolean example) {
        this.example = example;
    }


    public Plant(){}
    public Plant(String user, String name, String cares, String description, boolean example) {
        super();
        this.username = user;
        this.name= name;
        this.cares = cares;
        this.description = description;
        this.example = example;
    }
    public void addImage(Image image){
        images.add(image);
    }
    public List<Image> getImages() {
        return images;
    }
    public String getUsername() {
        return username;
    }

    public boolean isFavorite() {
        return favorite;
    }
    public boolean isRate1() { return rating == 1; }
    public boolean isRate2() { return rating == 2; }
    public boolean isRate3() { return rating == 3; }
    public boolean isRate4() { return rating == 4; }
    public boolean isRate5() { return rating == 5; }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCares() {
        return cares;
    }

    public void setCares(String cares) {
        this.cares = cares;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Plant [id="+id+", user=" + username + ", name=" + name + ", cares=" + cares + "]";
    }

}
