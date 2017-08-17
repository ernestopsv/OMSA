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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<Ruta> rutas = rutaServices.buscarTodasLasRutas();
        model.addAttribute("rutas", rutas);
        return "ver_ruta";
    }

    @RequestMapping("/listar_paradas")
    public String mostrarParadas(Model model, @RequestParam("id") Long id_ruta){
        List<Parada>paradas = paradaServices.buscarParadaPorRutaId(id_ruta);
        model.addAttribute("paradas",paradas);
        return "/listar_paradas";
    }

    @RequestMapping("/listar_autobus")
    public String mostrarAutobus(Model model, @RequestParam("id")Long id_ruta){
        List<Autobus> autobuses = autobusServices.buscarTodosLosAutobusporRuta(id_ruta);
        model.addAttribute("autobuses", autobuses);
        return "/listar_autobus";
    }

    @RequestMapping("/buscar")
    public String buscarRuta(Model model, @RequestParam("nombre_corredor")String nombre_Corredor){
        model.addAttribute("ruta", rutaServices.buscarRutaPorNombreCorredor(nombre_Corredor));
        return "/buscar";
    }

    @RequestMapping("/editar_ruta")
    public String editarRuta(Model model , @RequestParam("nombre_corredor")String nombre_Corredor){
        List<Ruta> ruta = rutaServices.buscarRutaPorNombreCorredor(nombre_Corredor);
        model.addAttribute("ruta", ruta.get(0));
        if(ruta.size()>1){
            model.addAttribute("idSegRuta", ruta.get(1).getId());
        }
        return "/editar_ruta";
    }

    @PostMapping("/editar_ruta")
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

    @RequestMapping("/crear_ruta")
    public String crearRuta(Model model){
        model.addAttribute("ruta", new Ruta());
        return "/crear_ruta";
    }
    @PostMapping("/crear_ruta")
    public String guardarRutaCreada(@ModelAttribute Ruta ruta){
        ruta.setEsDireccionSubida(true);
        rutaServices.guardarRuta(ruta);
        ruta.setEsDireccionSubida(false);
        rutaServices.guardarRuta(ruta);
        return "redirect:/ruta/";
    }

    @RequestMapping("/eliminar_ruta")
    public String eliminarRuta(@RequestParam("id")String nombreCorredor){
        for(Ruta ruta: rutaServices.buscarRutaPorNombreCorredor(nombreCorredor)){
            rutaServices.eliminarRutaPorId(ruta.getId());
        }
        return "redirect:/ruta/";
    }
}