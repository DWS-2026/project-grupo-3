package es.codeurjc.board.controller;
import es.codeurjc.board.model.Reviews;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.ReviewsService;
import es.codeurjc.board.service.UserSession;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;


@Controller

public class ReviewsController {
    @Autowired
    private ButtonsHeader btnsHeader;

    @PostConstruct
    public void init() {
    }

    @Autowired
    private UserSession userSession;

    @Autowired
    private ReviewsService reviewsService;

    @GetMapping("/Reviews/forum")
    public String forum(Model model, @PageableDefault (size = 6) Pageable page, HttpSession sesion, @RequestParam(required =false) Reviews.ReviewType type) {
        btnsHeader.hideBtnHeader(model,"review");
        Pageable sortedPage = PageRequest.of(page.getPageNumber(),6);

        Page<Reviews> reviewsPage = reviewsService.findAll(page);

        if (type != null){
            reviewsPage = reviewsService.findByType(type, page);
        }else{
            reviewsPage = reviewsService.findAll(page);
        }

        model.addAttribute("reviews", reviewsPage.getContent());
        model.addAttribute("hasPrev", reviewsPage.hasPrevious());
        model.addAttribute("prev", reviewsPage.getNumber() - 1);
        model.addAttribute("hasNext", reviewsPage.hasNext());
        model.addAttribute("next", reviewsPage.getNumber() + 1);
        model.addAttribute("reviews", reviewsPage.getContent());
        model.addAttribute("selectedType", type);

        return "Reviews/forum";
    }

    @GetMapping("/Reviews/newreview")
    public String newReview() {
        return "Reviews/newreview";
    }


}
