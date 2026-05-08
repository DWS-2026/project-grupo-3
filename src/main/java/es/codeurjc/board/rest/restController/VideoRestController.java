package es.codeurjc.board.rest.restController;

import es.codeurjc.board.model.Review;
import es.codeurjc.board.model.Video;
import es.codeurjc.board.rest.dto.VideoDTO;
import es.codeurjc.board.rest.mapper.VideoMapper;
import es.codeurjc.board.service.ReviewsService;
import es.codeurjc.board.service.UserService;
import es.codeurjc.board.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/reviews")
public class VideoRestController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private UserService userService;


    @PostMapping("/{reviewId}/video")
    public ResponseEntity<String> uploadVideo(
            @PathVariable Long reviewId,
            @RequestPart("imageFile") MultipartFile imageFile,
            HttpServletRequest request) throws IOException {

        Review review = reviewsService.findById(reviewId);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }

        if (!userService.seeIfUserIsLoggedIn(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!validateOwnerOrAdmin(review, request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        videoService.addVideoToReview(reviewId, imageFile);

        return ResponseEntity.ok("Video uploaded");
    }

    private boolean validateOwnerOrAdmin(
            Review review,
            HttpServletRequest request) {

        Long loggedUserId = userService.getUserID(request);

        boolean isOwner = review.getUser().getId().equals(loggedUserId);

        boolean isAdmin = userService.isUserAdmin(request);

        return isOwner || isAdmin;
    }

    @GetMapping("/{reviewId}/video/info")
    public ResponseEntity<VideoDTO> getVideoInfo(
            @PathVariable Long reviewId) {

        Review review = reviewsService.findById(reviewId);

        if (review == null || review.getVideo() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                videoMapper.toDTO(review.getVideo(), reviewId)
        );
    }

    @GetMapping("/{reviewId}/video")
    public ResponseEntity<Resource> getVideo(
            @PathVariable Long reviewId) throws IOException {

        Review review = reviewsService.findById(reviewId);

        if (review == null || review.getVideo() == null) {
            return ResponseEntity.notFound().build();
        }

        Video video = review.getVideo();

        Resource file = videoService.getVideo(video.getId());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(video.getContentType()))
                .contentLength(file.contentLength())
                .header("Content-Disposition",
                        "inline; filename=\"" + video.getFileName() + "\"")
                .body(file);
    }

    @DeleteMapping("/{reviewId}/video")
    public ResponseEntity<Void> deleteVideo(
            @PathVariable Long reviewId,
            HttpServletRequest request) {

        Review review = reviewsService.findById(reviewId);

        if (review == null) {
            return ResponseEntity.notFound().build();
        }

        if (!userService.seeIfUserIsLoggedIn(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!validateOwnerOrAdmin(review, request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (review.getVideo() != null) {
            videoService.delete(review.getVideo().getId());
            review.setVideo(null);
            reviewsService.save(review, review.getUser());
        }

        return ResponseEntity.noContent().build();
    }

}