package es.codeurjc.board.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {
    @GetMapping("/Pedidos/carroCompra")
    public String carroCompra() {
        return "Pedidos/carroCompra";
    }

    @GetMapping("/Pedidos/pago")
    public String pago() {
        return "Pedidos/pago";
    }


}
