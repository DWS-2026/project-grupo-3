package es.codeurjc.board.controller;


import es.codeurjc.board.model.buttonsHeader;
import jakarta.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GeneralController {

	@GetMapping("/")
	public String showMainPage(Model model, HttpSession session) {
		buttonsHeader.hideBtnHeader(model,"plants");
		buttonsHeader.hideBtnHeader(model,"goBackBtn");
		buttonsHeader.hideBtnHeader(model,"products");
		return "index";
	}

	@GetMapping("/sign-in")
	public String signIn(Model model) {
		buttonsHeader.hideBtnHeader(model,"changeUserBtn");
		return "sign-in";
	}


	@GetMapping("/quizzPlants")
	public String quizPlants() {
		return "quizzPlants";
	}

}