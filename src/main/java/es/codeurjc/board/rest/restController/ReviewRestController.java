package es.codeurjc.board.rest.restController;

import es.codeurjc.board.model.Review;
import es.codeurjc.board.model.User;
import es.codeurjc.board.rest.dto.ReviewDTO;
import es.codeurjc.board.rest.mapper.ReviewMapper;
import es.codeurjc.board.service.ReviewsService;

import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private UserService userService;


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) {

        reviewsService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewMapper.toDTOs(
                reviewsService.findAll(PageRequest.of(0, 100)).getContent()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {

        Review review = reviewsService.findById(id);

        return ResponseEntity.ok(
                reviewMapper.toDTO(review)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewDTO dto) {

        Review review = reviewsService.findById(id);

        review.setTitle(dto.title());
        review.setDescription(dto.description());
        review.setType(dto.type());

        reviewsService.save(review, review.getUser());

        return ResponseEntity.ok(
                reviewMapper.toDTO(review)
        );
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(
            @RequestBody ReviewDTO dto,
            HttpServletRequest request) {

        User user = userService.getUser(request);

        Review review = new Review(
                dto.title(),
                dto.description(),
                dto.type()
        );

        reviewsService.save(review, user);

        return ResponseEntity.status(201)
                .body(reviewMapper.toDTO(review));
    }


}









