package es.codeurjc.board.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
@ControllerAdvice //añade acciones antes de ejecutar cualquier controlador y es necesario para poder hacer ModelAttribute
//NO funciona con @Component
public class ButtonsHeader {
    private void intializeNavBtns(Model model){
        model.addAttribute("goBackBtn", true);
        model.addAttribute("products", true);
        model.addAttribute("orders", true);
        model.addAttribute("changeUserBtn", false);
        model.addAttribute("plants", true);
        model.addAttribute("profile", true);
        model.addAttribute("shoppingCart", true);
        model.addAttribute("review",true);
    }

    private void intializeSpecialBtns(Model model){
        model.addAttribute("loginOptions", false);
        model.addAttribute("userOnly", false); //aquí por defecto no lo mostramos, salvo que se haya loggeado
        model.addAttribute("adminBtn", false); //aquí por defecto no lo mostramos, salvo que sea admin
    }


    @ModelAttribute //esto se añade para que se haga por cada request,
    // por cada model que pidamos y solo afecta al metodo de debajo
    public void buttonsHeader(Model model) {
        intializeNavBtns(model);
        intializeSpecialBtns(model);
    }

    public void hideBtnHeader(Model model, String name) {
        model.addAttribute(name, false);
    }

    private void showBtnHeader (Model model, String name){
        model.addAttribute(name, true);
    }


}
