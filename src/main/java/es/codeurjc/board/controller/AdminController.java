package es.codeurjc.board.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
    @GetMapping("/Admin/admin-panel")
    public String adminPanel() {
        return "Admin/admin-panel";
    }

    @GetMapping("/Admin/gestionProductos")
    public String gestionProductos() {
        return "Admin/gestionProductos";
    }

    @GetMapping("/Admin/gestionPedidos")
    public String gestionPedidos() {
        return "Admin/gestionPedidos";
    }

    @GetMapping("/Admin/gestionUsuarios")
    public String gestionUsuarios() {
        return "Admin/gestionUsuarios";
    }


}