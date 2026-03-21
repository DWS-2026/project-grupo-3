package es.codeurjc.board.controller;


import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class GeneralController {
	@Autowired
	private ButtonsHeader btnsHeader;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;

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


	@GetMapping("/quizzPlants")
	public String quizPlants() {
		return "quizzPlants";
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