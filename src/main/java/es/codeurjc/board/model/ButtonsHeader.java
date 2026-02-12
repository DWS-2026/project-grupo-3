package es.codeurjc.board.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.ui.Model;import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;

@ControllerAdvice //añade acciones antes de ejecutar cualquier controlador y es necesario para poder hacer ModelAttribute
//NO funciona con @Component
public class ButtonsHeader {

    @ModelAttribute //esto se añade para que se haga por cada request,
    // por cada model que pidamos y solo afecta al metodo de debajo
    public void buttonsHeader(Model model) {
        List<String> buttons = List.of("goBackBtn", "products", "orders", "plants","profile","shoppingCart","review");
        for(String btn : buttons){
            model.addAttribute(btn, true);
        }
    }

    public void hideBtnHeader(Model model, String name) {
        model.addAttribute(name, false);
    }


}
