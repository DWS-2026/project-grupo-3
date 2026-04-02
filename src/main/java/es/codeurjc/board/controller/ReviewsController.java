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
    public String forum(Model model, @RequestParam(required = false) String type,
                        @RequestParam(required = false) String whatToShow,
                        Principal principal, HttpServletRequest request) {

        List<Reviews> reviews;

        // filter by type
        if (type != null && !type.isEmpty()) {
            reviews = reviewsService.findByType(Reviews.ReviewType.valueOf(type));
        } else {
            reviews = reviewsService.findAll(PageRequest.of(0, 100)).getContent();
        }

        model.addAttribute("reviews", reviews);
        model.addAttribute("type", type);
        model.addAttribute("isPlant", "PLANT".equals(type));
        model.addAttribute("isProduct", "PRODUCT".equals(type));

        // filter by myReviews
        if (principal != null && "misReviews".equals(whatToShow)) {
            reviews = reviewsService.findByUser(principal.getName());
            model.addAttribute("reviews", reviews);
            model.addAttribute("showEditButton", true);  // ← BOTÓN EDITAR
        }

        model.addAttribute("loginOptions", principal != null);
        model.addAttribute("misReviews", "misReviews".equals(whatToShow));
        return "Reviews/forum";
    }

    @GetMapping("/Reviews/newreview")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new Reviews());
        return "Reviews/newreview";
    }

    @PostMapping("/Reviews/newreview")
    public String saveReview(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Reviews.ReviewType type,
                             Principal principal,
                             HttpServletRequest request) {

        if (principal == null) {
            return "redirect:/login";
        }

        Reviews review = new Reviews(principal.getName(), title, description, type);
        reviewsRepository.save(review);
        return "redirect:/Reviews/forum";
    }

    @GetMapping("/Reviews/editReview/{id}")
    public String editReview(Model model, @PathVariable Long id, Principal principal, HttpServletRequest request) {
        Reviews review = reviewsService.findById(id);

        // Verify that belong to the user
        if (!review.getUser().equals(principal.getName()) && !userService.isUserAdmin(request)) {
            return "redirect:/accessDenied";
        }

        model.addAttribute("review", review);
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


}
