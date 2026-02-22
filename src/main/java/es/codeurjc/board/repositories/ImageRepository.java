package es.codeurjc.board.repositories;

import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import es.codeurjc.board.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
