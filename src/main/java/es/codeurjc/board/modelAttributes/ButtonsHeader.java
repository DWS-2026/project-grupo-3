package es.codeurjc.board.modelAttributes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.ui.Model;import java.util.List;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice //añade acciones antes de ejecutar cualquier controlador y es necesario para poder hacer ModelAttribute
//NO funciona con @Component
public class ButtonsHeader {

    @ModelAttribute //esto se añade para que se haga por cada request,
    // por cada model que pidamos y solo afecta al metodo de debajo
    public void buttonsHeader(Model model) {
        List<String> buttons = List.of("goBackBtn", "productsOption", "orders", "plantIcon","profile","shoppingCart","review", "loginButton");
        for(String btn : buttons){
            model.addAttribute(btn, true);
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, Model model, HttpServletRequest request) {
        if(!e.getClass().getSimpleName().isEmpty()){
            model.addAttribute("errorType", e.getClass().getSimpleName());
        }
        if(!e.getMessage().isEmpty()){
            model.addAttribute("errorMessage", e.getMessage());
        }
        Integer statusCode = (Integer) request.getAttribute(
                RequestDispatcher.ERROR_STATUS_CODE  // ← 500, 404, 403...
        );

        if(statusCode != null){
            String p3 = statusCode.toString();
            model.addAttribute("statusCode", statusCode);
        }

        return "error";
    }

    public void hideBtnHeader(Model model, String name) {
        model.addAttribute(name, false);
    }


}
