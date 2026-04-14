package es.codeurjc.board.webController;


import es.codeurjc.board.modelAttributes.ButtonsHeader;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class GeneralController {
	@Autowired
	private ButtonsHeader btnsHeader;


	@GetMapping("/")
	public String showMainPage(Model model) {
		btnsHeader.hideBtnHeader(model,"plantIcon");
		btnsHeader.hideBtnHeader(model,"goBackBtn");
		btnsHeader.hideBtnHeader(model,"images_example_products");
		return "index";
	}

	@GetMapping("/login")
	public String signIn(Model model) {
		btnsHeader.hideBtnHeader(model,"loginButton");
		return "login";
	}

	@GetMapping("/loginerror")
	public String signInError(Model model) {
		btnsHeader.hideBtnHeader(model,"loginButton");
		model.addAttribute("loginError", true);
		return "login";
	}


	@GetMapping("/quizzPlants")
	public String quizPlants() {
		return "/QuizzPlants/quizzPlants";
	}
	@PostMapping("/quizzPlants")
	public String resultTest(@RequestParam Map<String, String> answers, RedirectAttributes redirectAttributes, HttpSession session) {
		        int a = 0, b = 0, c = 0;

        for (String value : answers.values()) {
            if (value.equals("a")) a++;
            else if (value.equals("b")) b++;
            else if (value.equals("c")) c++;
        }

        String winner = "a";
        if (b > a && b > c) winner = "b";
        else if (c > a && c > b) winner = "c";

		session.setAttribute("winnerPlant", winner);
		return "redirect:/quizzPlants/result";
    
	}

	@GetMapping("/quizzPlants/result")
	public String showResult(HttpSession session, Model model) {


			return "/QuizzPlants/result";
	}
	
	

	@GetMapping("/403")
	public String accessDeniedPage() {
		return "accessDenied";
	}

	@GetMapping("/check")
	public String testError() {
		throw new RuntimeException("Error");
	}

}