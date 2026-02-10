package es.codeurjc.board.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/Productos/catalogoProductos")
    public String catalogoProductos() {
        return "Productos/catalogoProductos";
    }

    @GetMapping("/Productos/nuevoProducto")
    public String nuevoProducto() {
        return "Productos/nuevoProducto";
    }

    @GetMapping("/Productos/editProduct")
    public String editProduct() {
        return "Productos/editProduct";
    }


}
