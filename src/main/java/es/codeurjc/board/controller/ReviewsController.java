package es.codeurjc.board.controller;
import es.codeurjc.board.model.Image;
import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.Reviews;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.repositories.ReviewsRepository;
import es.codeurjc.board.service.ReviewsService;
import es.codeurjc.board.service.UserService;
import es.codeurjc.board.service.UserSession;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller

public class ReviewsController {
    @Autowired
    private UserSession userSession;

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private ButtonsHeader btnsHeader;
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/Reviews/forum")
    public String forum(Model model,
                        @RequestParam(required=false) Reviews.ReviewType type) {

        List<Reviews> reviews;

        if (type != null) {
            reviews = reviewsRepository.findByType(type);
        } else {
            reviews = reviewsRepository.findAll();
        }

        model.addAttribute("reviews", reviews);

        model.addAttribute("type", type);
        model.addAttribute("isPlant", type == Reviews.ReviewType.PLANT);
        model.addAttribute("isProduct", type == Reviews.ReviewType.PRODUCT);

        if (reviews.isEmpty()) {
            model.addAttribute("example", true);
        }

        return "Reviews/forum";
    }

    @GetMapping("/Reviews/newreview")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new Reviews());
        return "Reviews/newreview";
    }

    @PostMapping("/Reviews/newreview")
    public String saveReview(Reviews review, Principal principal) {
        if (principal != null) {
            review.setUser(principal.getName());
        }

        reviewsRepository.save(review);

        return "redirect:/Reviews/forum";
    }

    @GetMapping("/editReview/{id}")
    public String editPlant(Model model, @PathVariable Long id) {
        Reviews review = reviewsService.findById(id);
        model.addAttribute("review",review);
        model.addAttribute("reviewId", review.getId());

        return "Reviews/editReview";
    }
    @PostMapping("/Reviews/editReview/{id}")
    public String editReview(@PathVariable Long id,@RequestParam String title, @RequestParam String description, @RequestParam Reviews.ReviewType type)throws Exception{
        reviewsService.editReview(title,description,id);
        return "redirect:/Reviews/forum";
    }

    @PostMapping("/Reviews/{id}/delete")
    public String deleteReview(@PathVariable long id,
                               Principal principal,
                               HttpServletRequest request) {

        Reviews review = reviewsService.findById(id);

        String currentUser = principal.getName();

        boolean isOwner = review.getUser().equals(currentUser);
        boolean isAdmin = userService.isUserAdmin(request);

        if (isOwner || isAdmin) {
            reviewsService.deleteById(id);
        }

        return "redirect:/Reviews/forum";
    }


    //editar review, crear una nueva review (post mapping) eliminar review tanto por parte de admin,
    //como usuario

}
