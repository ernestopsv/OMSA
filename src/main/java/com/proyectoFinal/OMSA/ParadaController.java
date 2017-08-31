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
import org.springframework.web.servlet.ModelAndView;
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
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
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

    @RequestMapping(value = "/editar/{id_ruta}/{id_parada}", method = RequestMethod.GET)
    public String modificarParada(Model model, @PathVariable("id_parada")Long id_parada, @PathVariable("id_ruta")Long id_ruta){
        Parada parada = paradaServices.buscarParada(id_parada);
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
        model.addAttribute("parada", parada);
        model.addAttribute("paradas", paradaServices.buscarParadaPorRutaId(id_ruta));

        return "/editar_parada";
    }

    @PostMapping("/editar")
    public ModelAndView guardarParadaModificada(Model model, @RequestParam("nombre")String nombre, @RequestParam("ruta")Long id_ruta, @RequestParam("latitude")Double latitude,
                                                @RequestParam("longitud")Double longitud, @RequestParam(value = "paradaSiguiente", required = false)Long paradaSiguiente,
                                                @RequestParam(value = "paradaAnterior", required = false)Long paradaAnterior, @RequestParam("id_parada")Long id_parada){
        Ruta ruta = rutaServices.buscarRutaPorId(id_ruta);
        Parada parada = paradaServices.buscarParada(id_parada);
        parada.setRuta(ruta);
        parada.setNombre(nombre);
        parada.getCoordenada().setLatitude(latitude);
        parada.getCoordenada().setLongitud(longitud);
        parada.setParadaSiguiente(paradaSiguiente);
        parada.setParadaAnterior(paradaAnterior);
           boolean modificado = true;
        if(paradaServices.guardarParada(parada)!=null){
            modificado =true;
        };
        if (modificado){
            return new ModelAndView("redirect:/ruta/listar/paradas/"+id_ruta);

        }
       model.addAttribute("message", "error");
        model.addAttribute("rutas", rutaServices.buscarTodasLasRutas());
        model.addAttribute("parada", parada);
        model.addAttribute("paradas", paradaServices.buscarParadaPorRutaId(id_ruta));
        return  new ModelAndView("/editar_parada");

    }

//    @RequestMapping("/eliminar_parada")
//    public String eliminarParada(@RequestParam("id")Long id){
//        paradaServices.eliminarParadaPor(id);
//        return "redirect:/parada/";
//    }
}
