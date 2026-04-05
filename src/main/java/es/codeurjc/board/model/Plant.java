package es.codeurjc.board.model;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import java.util.ArrayList;
@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime createdAt;
    private String name;
    private String cares;
    private String description;
    private int rating = 0;
    private int totalRating = 0;
    private int count = 0;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true) //we don´t need it to be bidirectional, we only need it in one direction
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    private PlantType plantType;

    public Plant(){
        this.createdAt = LocalDateTime.now();
    }
    public Plant(String name, String cares, String description) {
        this.createdAt = LocalDateTime.now();
        this.name= name;
        this.cares = cares;
        this.description = description;
    }
    
    public void addImage(Image image){
        images.add(image);
    }
    public List<Image> getImages() {
        return images;
    }

    public boolean isRate1() { return rating >= 1; }
    public boolean isRate2() { return rating >= 2; }
    public boolean isRate3() { return rating >= 3; }
    public boolean isRate4() { return rating >= 4; }
    public boolean isRate5() { return rating >= 5; }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Plant [id="+id+", user=" + user.getUsername() + ", name=" + name + ", cares=" + cares + "]";
    }
    public PlantType getType() {
        return plantType;
    }
    public void setType(PlantType type) {
        this.plantType = type;
    }

    

}
