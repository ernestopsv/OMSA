package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Autobus;
import com.proyectoFinal.OMSA.Entities.Coordenada;
import com.proyectoFinal.OMSA.Entities.Parada;
import com.proyectoFinal.OMSA.Entities.Ruta;
import com.proyectoFinal.OMSA.Services.AutobusServices;
import com.proyectoFinal.OMSA.Services.CoordenadaServices;
import com.proyectoFinal.OMSA.Services.ParadaServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import com.sun.org.apache.bcel.internal.generic.L2I;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.internal.IteratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    @Autowired
    CoordenadaServices coordenadaServices;
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
        return "ver_parada_pintada";
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
    public String editarRuta(Model model , @RequestParam("ruta")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
        model.addAttribute("ruta", ruta);

        return "editar_ruta";
    }

    @PostMapping("/editar")
    public String guardarRutaEditada(@ModelAttribute Ruta ruta, @RequestParam("corredor")String nombreCorredor){

        Ruta ruta1 = rutaServices.buscarRutaPorNombreCorredor(nombreCorredor).get(1);

        if(ruta1!=null){
            ruta1.setCiudad(ruta.getCiudad());
            ruta1.setNombreCorredor(ruta.getNombreCorredor());
            ruta1.setDistanciaTotal(ruta.getDistanciaTotal());
            ruta1.setFechaUltimaModificacion(getLongFromDate(new Date()));
            rutaServices.guardarRuta(ruta1);
        }
        ruta.setFechaUltimaModificacion(getLongFromDate(new Date()));
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
        ruta.setFechaUltimaModificacion(getLongFromDate(new Date()));
        ruta.setFechaCreada(getLongFromDate(new Date()));
        ruta.setEsDireccionSubida(true);
        rutaServices.guardarRuta(ruta);
        ruta.setEsDireccionSubida(false);
        rutaServices.guardarRuta(ruta);
        return "redirect:/ruta/";
    }
    private Long getLongFromDate(Date date){
        return date.getTime()/1000;
    }

    @Transactional
    @RequestMapping("/eliminar/{id}")
    public String eliminarRuta(@PathVariable("id")Long id){
        paradaServices.eliminarParadaPorRutaId(id);
        List<Autobus> autobuses= autobusServices.buscarTodosLosAutobusporRuta(id);
        for(Autobus autobus: autobuses){
            autobus.setRuta(null);
            autobusServices.guardarAutobus(autobus);
        }
        rutaServices.eliminarRutaPorId(id);
        return "redirect:/ruta/";
    }
//-------------------------------------------------Coordenada-----------------------------------------------------
    @RequestMapping("/listar/coordenadas/{id}")
    public String mostrarCoordenadas(Model model, @PathVariable("id") Long id_ruta){
        List<Coordenada>coordenadas = rutaServices.buscarRutaPorId(id_ruta).getCoordenadas();
        model.addAttribute("coordenadas",coordenadas);
        model.addAttribute("size", coordenadas.size());
        model.addAttribute("id_ruta", id_ruta);
        model.addAttribute("nombreCorredor", rutaServices.buscarRutaPorId(id_ruta).getNombreCorredor());
        return "ver_coordenada_pintada";
    }

    @RequestMapping("/coordenada/crear/{id}")
    public String crearCoordenada(Model model, @PathVariable("id")Long id){
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        model.addAttribute("coordenada", new Coordenada());
        return "crear_coordenada";
    }

    @PostMapping("/coordenada/crear")
    public String guardarRutaCreada(@ModelAttribute Coordenada coordenada, Model model, @RequestParam("ruta")Long id){
        Ruta currentRuta = rutaServices.buscarRutaPorId(id);
        List<Ruta> rutas = rutaServices.buscarRutaPorNombreCorredor(currentRuta.getNombreCorredor());
        boolean guardado = false;
        for(Ruta ruta : rutas){
            ruta.getCoordenadas().add(coordenada);
            if(rutaServices.guardarRuta(ruta)!=null){
                guardado= true;
            }
        }
        if(guardado){
            model.addAttribute("message", true);
        }else {
            model.addAttribute("message", false);
        }
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        model.addAttribute("coordenada", new Coordenada());
        return "crear_coordenada";
    }

    @RequestMapping("/coordenada/editar/{id_ruta}/{id_coordenada}")
    public String editarCoordenada(Model model, @PathVariable("ruta")Long id_ruta, @PathVariable("id_coordenada") Long id_coordenada ){
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id_ruta));
        model.addAttribute("coordenada", coordenadaServices.buscarUnaCoordenada(id_coordenada));
        return "crear_coordenada";
    }

    @PostMapping("/coordenada/editar")
    public String guardarCoordenadaCreada(@ModelAttribute Coordenada coordenada, Model model, @RequestParam("ruta")Long id_ruta, @RequestParam("coordenada")Long id_coordenada){
        Ruta currentRuta = rutaServices.buscarRutaPorId(id_ruta);

        List<Ruta> rutas = rutaServices.buscarRutaPorNombreCorredor(currentRuta.getNombreCorredor());
        boolean modificado = false;
//        for(Ruta ruta : rutas){
//            List<Coordenada> coordenadas= ruta.getCoordenadas();
//            for(Coordenada coor: coordenadas){
//                if(coor.getId()){}
//            }
            if(coordenadaServices.guardarCoordenada(coordenada)!=null){
                modificado= true;
            }
      //  }
        if(modificado){
            return "redirect:/listar/coordenadas/"+currentRuta.getId();
        }else {
            model.addAttribute("message", true);
            model.addAttribute("ruta", rutaServices.buscarRutaPorId(id_ruta));
            model.addAttribute("coordenada", coordenadaServices.buscarUnaCoordenada(id_coordenada));
            return "crear_coordenada";
        }

    }

    @Transactional
    @RequestMapping("/coordenada/eliminar/{id_ruta}/{id_coordenada")
    public String eliminarRuta(@PathVariable("id_ruta")Long id_ruta, @PathVariable("id_coordenada")Long id_coordenada ){
        List<Coordenada> coordenadas= rutaServices.buscarRutaPorId(id_ruta).getCoordenadas();
       for(Coordenada coordenada:coordenadas) {
           if (coordenada.getId().equals(id_coordenada)) {
               coordenadas.remove(coordenada);
           }
       }
       Ruta ruta =  rutaServices.buscarRutaPorId(id_ruta);
       ruta.setCoordenadas(coordenadas);
        rutaServices.guardarRuta(ruta);
        return "redirect:/listar/coordenadas/"+id_ruta;
    }

}