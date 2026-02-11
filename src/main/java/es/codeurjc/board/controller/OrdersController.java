package es.codeurjc.board.controller;
import es.codeurjc.board.model.ButtonsHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {
    @Autowired
    private ButtonsHeader btnsHeader;
    @GetMapping("/Pedidos/carroCompra")
    public String carroCompra(Model model) {
        btnsHeader.hideBtnHeader(model,"shoppingCart");
        return "Pedidos/carroCompra";
    }

    @GetMapping("/Pedidos/pago")
    public String pago() {
        return "Pedidos/pago";
    }


}
