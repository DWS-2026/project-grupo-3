package es.codeurjc.board.webController;

import es.codeurjc.board.model.Product;
import es.codeurjc.board.service.CartService;
import es.codeurjc.board.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable long productId,
                            @RequestParam(defaultValue = "1") int quantity) {

        Product product = productService.findById(productId);
        cartService.addItem(product.getId(), product.getName(), product.getPrice(), quantity);
        return "redirect:/Products/catalogProducts";
    }


    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable long productId) {
        cartService.removeItem(productId);
        return "redirect:/Orders/shoppingCart";
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/Orders/shoppingCart";
    }
}