package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Parada;
import com.proyectoFinal.OMSA.Entities.Ruta;
import com.proyectoFinal.OMSA.Services.ParadaServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sun.awt.ModalExclude;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anyderre on 31/07/17.
 */
@Controller
//@EnableWebMvc
@RequestMapping("/parada")
public class ParadaController {
    @Autowired
    ParadaServices paradaServices;
    @Autowired
    RutaServices rutaServices;


    @RequestMapping("/crear/{id}")
    public String crearParada(Model model, @PathVariable("id")Long id){
        model.addAttribute("id_ruta", id);
        model.addAttribute("parada", new Parada());
        model.addAttribute("paradas", paradaServices.buscarParadaPorRutaId(id));
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        return "crear_parada";
    }

    @Transactional
    @PostMapping("/crear")
    public String guardarParadaCreada(@ModelAttribute Parada parada, Model model,  @RequestParam("ruta")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
            parada.setRuta(ruta);

            if(paradaServices.guardarParada(parada)!=null){
                model.addAttribute("message", true);
            }else {
                model.addAttribute("message", false);
            }

        model.addAttribute("id_ruta", id);
        model.addAttribute("parada", new Parada());
        model.addAttribute("paradas", paradaServices.buscarParadaPorRutaId(id));
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        return "crear_parada";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String modificarParada(Model model, @PathVariable("id")Long id){
        Parada parada = paradaServices.buscarParada(id);
        model.addAttribute("parada", parada);
        return "/editar_parada";
    }

    @PostMapping("/editar")
    public String guardarParadaModificada(@ModelAttribute Parada parada, @RequestParam("id")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
            parada.setRuta(ruta);
            paradaServices.guardarParada(parada);
            return "redirect:/parada?id="+id;
    }

//    @RequestMapping("/eliminar_parada")
//    public String eliminarParada(@RequestParam("id")Long id){
//        paradaServices.eliminarParadaPor(id);
//        return "redirect:/parada/";
//    }
}
