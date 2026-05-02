package es.codeurjc.board.model;

import jakarta.persistence.*;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;
    private String storedFileName;
    private String contentType;

    public Video() {}

    public Video(String fileName, String storedFileName, String contentType) {
        this.fileName = fileName;
        this.storedFileName = storedFileName;
        this.contentType = contentType;
    }

    public Long getId() { return id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getStoredFileName() { return storedFileName; }
    public void setStoredFileName(String storedFileName) { this.storedFileName = storedFileName; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
}