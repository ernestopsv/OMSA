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
        model.addAttribute("id_ruta", id);
        return "ver_parada";
    }

    @RequestMapping("/crear_parada")
    public String crearParada(Model model){
        model.addAttribute("parada", new Parada());
        return "/crear_parada";
    }

    @Transactional
    @PostMapping("/crear_parada")
    public String guardarParadaCreada(@ModelAttribute Parada parada, @RequestParam("id")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
            parada.setRuta(ruta);
            paradaServices.guardarParada(parada);
            return "redirect:/";
    }

    @RequestMapping("/editar_parada")
    public String modificarParada(Model model, @RequestParam("id")Long id){
        Parada parada = paradaServices.buscarParada(id);
        model.addAttribute("parada", parada);
        return "/editar_parada";
    }

    @PostMapping("/editar_parada")
    public String guardarParadaModificada(@ModelAttribute Parada parada, @RequestParam("id")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
            parada.setRuta(ruta);
            paradaServices.guardarParada(parada);
            return "redirect:/parada?id="+id;
    }

    @RequestMapping("/eliminar_parada")
    public String eliminarParada(@RequestParam("id")Long id){
        paradaServices.eliminarParadaPor(id);
        return "redirect:/parada/";
    }
}
