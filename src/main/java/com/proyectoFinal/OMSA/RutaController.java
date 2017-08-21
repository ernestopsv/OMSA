package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Autobus;
import com.proyectoFinal.OMSA.Entities.Parada;
import com.proyectoFinal.OMSA.Entities.Ruta;
import com.proyectoFinal.OMSA.Services.AutobusServices;
import com.proyectoFinal.OMSA.Services.ParadaServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import com.sun.org.apache.bcel.internal.generic.L2I;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * Created by anyderre on 31/07/17.
 */
@Controller
//@EnableWebMvc
@RequestMapping("/ruta")
public class RutaController {
    @Autowired
    private RutaServices rutaServices;

    @Autowired
    private AutobusServices autobusServices;

    @Autowired
    private ParadaServices paradaServices;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("size", rutaServices.buscarTodasLasRutas().size());
        return "ver_ruta";
    }

    @RequestMapping("/listar/paradas/{id}")
    public String mostrarParadas(Model model, @PathVariable("id") Long id_ruta){
        List<Parada>paradas = paradaServices.buscarParadaPorRutaId(id_ruta);
        model.addAttribute("paradas",paradas);
        model.addAttribute("size", paradas.size());
        model.addAttribute("id_ruta", id_ruta);
        model.addAttribute("nombreCorredor", rutaServices.buscarRutaPorId(id_ruta).getNombreCorredor());
        return "ver_ruta_pintada";
    }

    @RequestMapping("/listar/autobus")
    public String mostrarAutobus(Model model, @RequestParam("id")Long id_ruta){
        List<Autobus> autobuses = autobusServices.buscarTodosLosAutobusporRuta(id_ruta);
        model.addAttribute("autobuses", autobuses);
        return "ver_autobus";
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = "application/json")
    public List<Ruta> buscarRuta(Model model, @RequestParam("nombre_corredor")String nombre_Corredor){
        //model.addAttribute("ruta", rutaServices.buscarRutaPorNombreCorredor(nombre_Corredor));
        return rutaServices.buscarRutaPorNombreCorredor(nombre_Corredor);
    }

    @RequestMapping("/editar")
    public String editarRuta(Model model , @RequestParam("nombre_corredor")String nombre_Corredor){
        List<Ruta> ruta = rutaServices.buscarRutaPorNombreCorredor(nombre_Corredor);
        model.addAttribute("ruta", ruta.get(0));
        if(ruta.size()>1){
            model.addAttribute("idSegRuta", ruta.get(1).getId());
        }
        return "editar_ruta";
    }

    @PostMapping("/editar")
    public String guardarRutaEditada(@ModelAttribute Ruta ruta, @RequestParam("idSegRuta")Long id){

        Ruta ruta1 = rutaServices.buscarRutaPorId(id);

        if(ruta1!=null){
            Long idSegRuta = ruta1.getId();
            Boolean direccion = ruta1.getEsDireccionSubida();
            ruta1.setEsDireccionSubida(direccion);
            ruta1.setId(idSegRuta);
            rutaServices.guardarRuta(ruta1);
        }
        rutaServices.guardarRuta(ruta);
       return "redirect:/ruta/";
    }

    @RequestMapping("/crear")
    public String crearRuta(Model model){
        model.addAttribute("ruta", new Ruta());
        return "crear_ruta";
    }
    @PostMapping("/crear")
    public String guardarRutaCreada(@ModelAttribute Ruta ruta){
        ruta.setEsDireccionSubida(true);
        rutaServices.guardarRuta(ruta);
        ruta.setEsDireccionSubida(false);
        rutaServices.guardarRuta(ruta);
        return "redirect:/ruta/";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminarRuta(@PathVariable("id")Long id){
        paradaServices.eliminarParadaPorRutaId(id);
        rutaServices.eliminarRutaPorId(id);
        return "redirect:/ruta/";
    }
}