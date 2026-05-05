package es.codeurjc.board.rest.restController;

import es.codeurjc.board.model.Video;
import es.codeurjc.board.rest.dto.VideoDTO;
import es.codeurjc.board.rest.mapper.VideoMapper;
import es.codeurjc.board.service.ReviewsService;
import es.codeurjc.board.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class VideoRestController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoMapper videoMapper;

    @PostMapping("/api/{id}/video")
    public ResponseEntity<String> uploadVideo(
            @PathVariable Long id,
            @RequestParam MultipartFile file) throws IOException {

        videoService.addVideoToReview(id, file);

        return ResponseEntity.ok("Video uploaded");
    }

    @GetMapping("/api/videos/{id}/info")
    public ResponseEntity<VideoDTO> getVideoInfo(@PathVariable Long id) {

        Video video = videoService.findById(id);

        return ResponseEntity.ok(
                videoMapper.toDTO(video)
        );
    }

    @GetMapping("/api/videos/{id}")
    public ResponseEntity<Resource> getVideo(
            @PathVariable Long id) throws IOException {

        Video video = videoService.findById(id);
        Resource file = videoService.getVideo(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(video.getContentType()))
                .contentLength(file.contentLength())
                .header("Content-Disposition",
                        "inline; filename=\"" + video.getFileName() + "\"")
                .body(file);
    }

    @DeleteMapping("/api/videos/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {

        videoService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
