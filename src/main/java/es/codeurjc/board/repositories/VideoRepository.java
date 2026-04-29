package es.codeurjc.board.repositories;

import es.codeurjc.board.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}