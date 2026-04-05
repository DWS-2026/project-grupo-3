package es.codeurjc.board.controller;

import es.codeurjc.board.model.Review;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.PlantService;
import es.codeurjc.board.service.ProductService;
import es.codeurjc.board.service.ReviewsService;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import es.codeurjc.board.model.Plant;
import es.codeurjc.board.model.PlantType;
import es.codeurjc.board.model.Product;
import es.codeurjc.board.service.PlantTypeService;

@Controller

public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private PlantTypeService plantTypeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ButtonsHeader btnsHeader;

    @Autowired
    private UserService userService;


    ReviewsController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/Reviews/forum")
    public String forum(Model model, @RequestParam(required = false) String type, @RequestParam(required = false) String whatToShow,
                         HttpServletRequest request) {

        List<Review> reviews;   
        btnsHeader.hideBtnHeader(model, "review");

        // filter by type
        if (userService.seeIfUserIsLoggedIn(request) && "myReviews".equals(whatToShow)) {
            if (type != null && !type.isEmpty() ) {
                reviews = reviewsService.findByUserAndType(userService.getUser(request),Review.ReviewType.valueOf(type));
            }else{
                reviews = reviewsService.findByUser(userService.getUser(request));
            }        
            
            model.addAttribute("showEditButton", true);  // ← EDIT BOTTON
            model.addAttribute("myReviews", true);
        }else{
            if (type != null && !type.isEmpty() ) {
                reviews = reviewsService.findByType(Review.ReviewType.valueOf(type));         
            } 
            else {
                reviews = reviewsService.findAll(PageRequest.of(0, 100)).getContent();
            }
        }
        
        model.addAttribute("reviews", reviews);
        model.addAttribute("type", type);
        model.addAttribute("isPlant", "PLANT".equals(type));
        model.addAttribute("isProduct", "PRODUCT".equals(type));

        model.addAttribute("loginOptions", userService.seeIfUserIsLoggedIn(request));

        return "Reviews/forum";
    }

    @GetMapping("/Reviews/newreview")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "Reviews/newreview";
    }

    @PostMapping("/Reviews/newreview")
    public String saveReview(Model model,  RedirectAttributes redirectAttributes,@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Review.ReviewType type,   @RequestParam String productOrplant, HttpServletRequest request) {
                                        
        boolean isValidRequest = false;

        if (userService.isUserUser(request) && productOrplant != null && !productOrplant.isBlank() && type != null) {
            isValidRequest = true;
        }

        if (isValidRequest) {
            Review review = new Review(title, description, type);
            boolean associationOk = false;

            if ("PLANT".equals(type.name())) {
                associationOk = reviewsService.handlePlant(review, productOrplant);
            } else if ("PRODUCT".equals(type.name())) {
                associationOk = reviewsService.handleProduct(review, productOrplant);
            }

            if (!associationOk) {
                redirectAttributes.addFlashAttribute("nameNotValid", true);
                return "redirect:/Reviews/newreview";
            }else{
                reviewsService.save(review, userService.getUser(request));
                return "redirect:/Reviews/forum";
            }

        } else {
            return "redirect:/Reviews/newreview";
        }
                

    }

    @GetMapping("/Reviews/editReview/{id}")
    public String editReview(Model model, @PathVariable Long id, Principal principal, HttpServletRequest request) {
        Review review = reviewsService.findById(id);

        // Verify that belong to the user
        if (review.getUser().getId() != userService.getUserID(request) || userService.isUserAdmin(request)) {
            return "redirect:/accessDenied";
        }

        model.addAttribute("review", review);
        return "Reviews/editReview";
    }

    @PostMapping("/Reviews/editReview/{id}")
    public String editReview(@PathVariable Long id,@RequestParam String title, @RequestParam String description,@RequestParam String productOrplant, @RequestParam Review.ReviewType type, HttpServletRequest session)throws Exception{
        Review review = reviewsService.findById(id);

        if (review.getUser().getId() != userService.getUserID(session) || userService.isUserAdmin(session)) {
            return "redirect:/accessDenied";
        }

        if(reviewsService.editReview(title,description,productOrplant, type, id)){
            reviewsService.save(review, userService.getUser(session));
            return "redirect:/Reviews/forum";
        }else{
            return "redirect:/Reviews/editReview/" + review.getId();
        }

    }

    @PostMapping("/Reviews/{id}/delete")
    public String deleteReview(@PathVariable long id,
                               HttpServletRequest request) {

        Review review = reviewsService.findById(id);

        if(review != null){
            boolean isOwner = review.getUser().getId() == userService.getUserID(request);
            boolean isAdmin = userService.isUserAdmin(request);
            if (isOwner || isAdmin) {
                reviewsService.deleteById(id);
            }
        }


        return "redirect:/Reviews/forum";
    }


}
