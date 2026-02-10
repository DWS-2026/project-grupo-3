package es.codeurjc.board.controller;


import jakarta.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GeneralController {


	@GetMapping("/")
	public String showMainPage(Model model, HttpSession session) {
		return "index";
	}

	@GetMapping("/sign-in")
	public String signIn() {
		return "sign-in";
	}


	@GetMapping("/quizzPlants")
	public String quizPlants() {
		return "quizzPlants";
	}

}