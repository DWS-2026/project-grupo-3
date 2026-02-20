package es.codeurjc.board.model;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private Blob imageFile;

    @ManyToOne
    private Plant plant;


}
