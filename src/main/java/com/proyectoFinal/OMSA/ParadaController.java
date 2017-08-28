package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Coordenada;
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
    public String guardarParadaCreada(@RequestParam("nombre")String nombre,@RequestParam("latitude")Double latitude,@RequestParam("longitud")Double longitud,
                                      @RequestParam(value = "paradaAnterior", required = false)Long paradaAnterior, @RequestParam(value = "paradaSiguiente", required = false)Long paradaSiguiente,
                                      Model model,  @RequestParam("ruta")Long id_ruta){

       // System.out.println(parada.getId()+"/"+parada.getParadaAnterior()+"/"+parada.getParadaSiguiente()+"/"+parada.getCoordenada().getLongitud()+"/"+parada.getCoordenada().getLatitude()+"================================================================");

        Ruta ruta = rutaServices.buscarRutaPorId(id_ruta);
        Parada parada = new Parada();
        parada.setRuta(ruta);
        parada.setCoordenada(new Coordenada(latitude, longitud));
        parada.setNombre(nombre);
        if(paradaAnterior!=null||paradaSiguiente!=null){
            parada.setParadaAnterior(paradaAnterior);
            parada.setParadaSiguiente(paradaSiguiente);
        }
            if(paradaServices.guardarParada(parada)!=null){
                model.addAttribute("message", "success");
            }else {
                model.addAttribute("message", "error");
            }

        model.addAttribute("id_ruta", id_ruta);
        model.addAttribute("parada", new Parada());
        model.addAttribute("paradas", paradaServices.buscarParadaPorRutaId(id_ruta));
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id_ruta));
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
