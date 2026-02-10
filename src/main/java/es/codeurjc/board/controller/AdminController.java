package es.codeurjc.board.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.board.model.Post;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.PostService;
import es.codeurjc.board.service.UserSession;

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