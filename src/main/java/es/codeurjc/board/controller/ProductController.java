package es.codeurjc.board.controller;
import es.codeurjc.board.model.ButtonsHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@Controller
public class ProductController {
    @Autowired
    private ButtonsHeader btnsHeader;
    @GetMapping("/Productos/catalogoProductos")
    public String catalogoProductos(Model model) {
        btnsHeader.hideBtnHeader(model,"products");
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
