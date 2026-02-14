package es.codeurjc.board.controller;
import es.codeurjc.board.model.ButtonsHeader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class PlantController {
    @Autowired
    private ButtonsHeader btnsHeader;
    @GetMapping("/Plants/catalogPlants")
    public String catalogPlants(Model model) {
        btnsHeader.hideBtnHeader(model,"plants");
        return "Plants/catalogPlants";
    }

    @GetMapping("/Plants/newPlant")
    public String newPlant() {
        return "Plants/newPlant";
    }

    @GetMapping("/Plants/editPlant")
    public String editPlant() {
        return "Plants/editPlant";
    }


}
