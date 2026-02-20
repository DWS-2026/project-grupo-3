package es.codeurjc.board.controller;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.modelAttributes.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
    @Autowired
    private ButtonsHeader btnsHeader;
    @GetMapping("/Admin/adminPanel")
    public String adminPanel(Model model) {
        SessionAttributes.hideAdminPanel(model);
        return "Admin/adminPanel";
    }

    @GetMapping("/Admin/productManagement")
    public String productManagement() {
        return "Admin/productManagement";
    }

    @GetMapping("/Admin/managementOrders")
    public String managementOrders() {
        return "Admin/managementOrders";
    }

    @GetMapping("/Admin/userManagement")
    public String userManagement() {
        return "Admin/userManagement";
    }


}