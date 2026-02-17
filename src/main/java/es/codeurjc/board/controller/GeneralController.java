package es.codeurjc.board.controller;


import es.codeurjc.board.model.ButtonsHeader;
import jakarta.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GeneralController {
	@Autowired
	private ButtonsHeader btnsHeader;
	@GetMapping("/")
	public String showMainPage(Model model, HttpSession session) {
		btnsHeader.hideBtnHeader(model,"plants");
		btnsHeader.hideBtnHeader(model,"goBackBtn");
		btnsHeader.hideBtnHeader(model,"products");
		return "index";
	}

	@GetMapping("/sign-in")
	public String signIn(Model model) {
		btnsHeader.hideBtnHeader(model,"changeUserBtn");
		return "sign-in";
	}


	@GetMapping("/quizzPlants")
	public String quizPlants() {
		return "quizzPlants";
	}




	@GetMapping("/error")
	public String error() {

		return "error";

	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

}