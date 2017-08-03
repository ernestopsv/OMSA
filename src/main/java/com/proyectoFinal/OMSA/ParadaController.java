package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Parada;
import com.proyectoFinal.OMSA.Entities.Ruta;
import com.proyectoFinal.OMSA.Services.ParadaServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.awt.ModalExclude;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anyderre on 31/07/17.
 */
@Controller()
@RequestMapping("/parada")
public class ParadaController {
    @Autowired
    ParadaServices paradaServices;
    @Autowired
    RutaServices rutaServices;

    @RequestMapping("/")
    public String index(Model model, @RequestParam(value = "id", required = false, defaultValue = "1")Long id ){
        List<Parada> paradas = paradaServices.buscarParadaPorRutaId(id);
        model.addAttribute("paradas", paradas);
        return "";
    }

    @RequestMapping("/crear")
    public String crearParada(Model model){
        model.addAttribute("parada", new Parada());
        return "/crear";
    }

    @Transactional
    @PostMapping("/crear")
    public String guardarParadaCreada(@ModelAttribute Parada parada, @RequestParam("id")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
            parada.setRuta(ruta);
            paradaServices.guardarParada(parada);
            return "redirect:/";
    }

    @RequestMapping("/editar")
    public String modificarParada(Model model, @RequestParam("id")Long id){
        Parada parada = paradaServices.buscarParada(id);
        model.addAttribute("parada", parada);
        return "/editar";
    }

    @PostMapping("/editar")
    public String guardarParadaModificada(@ModelAttribute Parada parada, @RequestParam("id")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
            parada.setRuta(ruta);
            paradaServices.guardarParada(parada);
            return "redirect:/";
    }

    @RequestMapping("/eliminar")
    public String eliminarParada(@RequestParam("id")Long id){
        paradaServices.eliminarParadaPor(id);
        return "redirect:/parada/";
    }
}
