package es.codeurjc.board.modelAttributes;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice //adds actions before running any controller and is required to be able to make ModelAttribute
//Does NOT work with @Component
public class ButtonsHeader {

    @ModelAttribute //this is added so that it is done for each request,
    // for each model we order and only affects the method below
    public void buttonsHeader(Model model) {
        List<String> buttons = List.of("goBackBtn", "productsOption", "orders", "plantIcon","profile","shoppingCart","review", "loginButton");
        for(String btn : buttons){
            model.addAttribute(btn, true);
        }
    }



    public void hideBtnHeader(Model model, String name) {
        model.addAttribute(name, false);
    }


}
