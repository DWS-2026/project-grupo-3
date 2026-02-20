package es.codeurjc.board.controller;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@Controller
public class ProductController {
    @Autowired
    private ButtonsHeader btnsHeader;
    @GetMapping("/Products/catalogProducts")
    public String catalogProducts(Model model) {
        btnsHeader.hideBtnHeader(model,"products");
        return "Products/catalogProducts";
    }

    @GetMapping("/Products/newProduct")
    public String newProduct() {
        return "Products/newProduct";
    }

    @GetMapping("/Products/editProduct")
    public String editProduct() {
        return "Products/editProduct";
    }


}
