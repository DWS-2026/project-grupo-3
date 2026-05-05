package es.codeurjc.board.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Review {

    public enum ReviewType {
        PLANT,
        PRODUCT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Video video;

    @Enumerated(EnumType.STRING)
    private ReviewType type;

    @ManyToOne
    private Product product;

    @ManyToOne
    private PlantType plantType;

    @ManyToOne
    private User user;

    public Review() {
    }

    public Review(String title, String description, ReviewType type) {
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReviewType getType() {
        return type;
    }

    public void setType(ReviewType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Review [id =" + id + "title = " + title + "description = " + description + "]";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    public boolean isPlantReview() {
        return this.type == ReviewType.PLANT;
    }

    public boolean isProductReview() {
        return this.type == ReviewType.PRODUCT;
    }

}
