package es.codeurjc.board.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
    @GetMapping("/Admin/adminPanel")
    public String adminPanel() {
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