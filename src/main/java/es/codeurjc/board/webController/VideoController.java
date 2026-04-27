package es.codeurjc.board.webController;

import es.codeurjc.board.model.Review;
import es.codeurjc.board.model.Video;
import es.codeurjc.board.service.ReviewsService;
import es.codeurjc.board.service.VideoService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private ReviewsService reviewsService;

    // Añadir video a una review desde web
    @PostMapping("/upload/{reviewId}")
    public String uploadVideo(@PathVariable Long reviewId,
                              @RequestParam MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            videoService.addVideoToReview(reviewId, file);
        }

        return "redirect:/Reviews/editReview/" + reviewId;
    }

    @GetMapping("/api/videos/{id}")
    public ResponseEntity<Resource> getVideo(@PathVariable Long id) throws IOException {

        Video video = videoService.findById(id);
        Resource file = videoService.getVideo(id);

        return ResponseEntity.ok()
                .header("Content-Type", video.getContentType())
                .header("Content-Disposition", "inline; filename=\"" + video.getFileName() + "\"")
                .body(file);
    }
}