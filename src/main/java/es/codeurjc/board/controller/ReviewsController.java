package es.codeurjc.board.controller;
import es.codeurjc.board.model.buttonsHeader;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewsController {

    @GetMapping("/Reviews/foro")
    public String foro(Model model) {
        buttonsHeader.hideBtnHeader(model,"review");
        return "Reviews/foro";
    }

    @GetMapping("/Reviews/newreview")
    public String newReview() {
        return "Reviews/newreview";
    }


}
