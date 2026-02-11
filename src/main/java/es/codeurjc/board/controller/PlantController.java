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
    @GetMapping("/Plantas/catalogoPlantas")
    public String catalogoPlantas(Model model) {
        btnsHeader.hideBtnHeader(model,"plants");
        return "Plantas/catalogoPlantas";
    }

    @GetMapping("/Plantas/nuevaPlanta")
    public String nuevaPlanta() {
        return "Plantas/nuevaPlanta";
    }

    @GetMapping("/Plantas/editPlantita")
    public String editPlantita() {
        return "Plantas/editPlantita";
    }


}
