package es.codeurjc.board.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlantController {
    @GetMapping("/Plantas/catalogoPlantas")
    public String catalogoPlantas() {
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
