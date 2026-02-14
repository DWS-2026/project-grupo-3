package es.codeurjc.board.model;

import java.awt.*;

public class Plant {

    private Long id;
    private String user;
    private String name;
    private String cares;
    private String description;
    private int counterImages = 0;

    public Plant(){}
    public Plant(String user, String name, String cares) {
        super();
        this.user = user;
        this.name= name;
        this.cares = cares;
    }

    public int getCounterImages() {
        return counterImages;
    }

    public void setCounterImages() {
        this.counterImages ++;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
        return "Plant [id="+id+", user=" + user + ", name=" + name + ", cares=" + cares + "]";
    }

}
