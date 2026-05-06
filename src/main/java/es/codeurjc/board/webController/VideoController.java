package es.codeurjc.board.webController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.board.model.Video;
import es.codeurjc.board.service.ReviewsService;
import es.codeurjc.board.service.VideoService;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private ReviewsService reviewsService;

    @PostMapping("/upload/{reviewId}")
    public String uploadVideo(@PathVariable Long reviewId,
            @RequestParam MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            videoService.addVideoToReview(reviewId, file);
        }

        return "redirect:/reviews/editReview/" + reviewId;
    }

    @GetMapping("/videos/{id}")
    public ResponseEntity<Resource> getVideo(@PathVariable Long id) throws IOException {

        Video video = videoService.findById(id);
        Resource file = videoService.getVideo(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(video.getContentType()))
                .contentLength(file.contentLength())
                .header("Content-Disposition",
                        "inline; filename=\"" + video.getFileName() + "\"")
                .body(file);
    }

}