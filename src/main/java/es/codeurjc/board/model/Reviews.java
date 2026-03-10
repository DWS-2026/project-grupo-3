package es.codeurjc.board.model;

import java.io.IOException;
import jakarta.persistence.*;

@Entity
public class Reviews {

    public enum ReviewType {
        PLANT,
        PRODUCT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String user;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private ReviewType type;

    public Reviews(){}
    public Reviews(String username, String title, String description, ReviewType type){
        super();
        this.user = username;
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return user;
    }

    public void setUsername(String username) {
        this.user = username;
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

    public ReviewType getType() { return type; }

    public void setType(ReviewType type) { this.type = type; }

    @Override
    public String toString(){
        return "Review [id ="+id+" user = "+user+ "title = " + title + "description = " + description + "]";
    }
}
