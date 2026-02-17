package es.codeurjc.board.controller;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewsController {
    @Autowired
    private ButtonsHeader btnsHeader;
    @GetMapping("/Reviews/forum")
    public String forum(Model model) {
        btnsHeader.hideBtnHeader(model,"review");
        return "Reviews/forum";
    }

    @GetMapping("/Reviews/newreview")
    public String newReview() {
        return "Reviews/newreview";
    }


}
