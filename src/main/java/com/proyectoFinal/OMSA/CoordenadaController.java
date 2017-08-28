package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Coordenada;
import com.proyectoFinal.OMSA.Entities.Ruta;
import com.proyectoFinal.OMSA.Services.CoordenadaServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.plaf.ColorUIResource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anyderre on 31/07/17.
 */
@Controller
@RequestMapping("/coordenada")
public class CoordenadaController {
   @Autowired
    RutaServices rutaServices;
   @Autowired
    CoordenadaServices coordenadaServices;

    @RequestMapping("/crear/{id}")
    public String crearCoordenada(Model model, @PathVariable("id")Long id){
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        model.addAttribute("coordenada", new Coordenada());
        return "crear_coordenada";
    }

    @PostMapping("/crear")
    public String guardarCoordenadaCreada(@RequestParam("latitude")double latitud, @RequestParam("longitud")double longitud, Model model, @RequestParam("ruta")Long id){
        System.out.println("Was there 1------------========================================================");
        Ruta currentRuta = rutaServices.buscarRutaPorId(id);
        List<Ruta> rutas = rutaServices.buscarRutaPorNombreCorredor(currentRuta.getNombreCorredor());
        System.out.println("Was there 2------------========================================================");
        boolean guardado = false;
        Coordenada coordenada = new Coordenada();
        coordenada.setLatitude(latitud);
        coordenada.setLongitud(longitud);
        rutas.get(0).getCoordenadas().add(coordenada);
        System.out.println(coordenada.getLatitude()+"/"+coordenada.getLongitud());
        if(rutaServices.guardarRuta(rutas.get(0))!=null){
            System.out.println("Was there 3------------========================================================");
            guardado= true;
        }
        if(rutas.size()>1){
            System.out.println("Was there 4------------========================================================");
            Coordenada coordenada1 = new Coordenada();
            coordenada1.setLongitud(coordenada.getLongitud());
            coordenada1.setLatitude(coordenada.getLatitude());
            rutas.get(1).getCoordenadas().add(coordenada1);
            if(rutaServices.guardarRuta(rutas.get(1))!=null){
                System.out.println("Was there Saving 5------------========================================================");

                guardado= true;
            }
        }
        System.out.println("Was there 6------------========================================================");
        if(guardado){
            System.out.println("Was there 7------------========================================================");
            model.addAttribute("message", "success");
        }else {
            System.out.println("Was there 8------------========================================================");
            model.addAttribute("message", "error");
        }
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        model.addAttribute("coordenada", new Coordenada());
        System.out.println("Was there 9------------========================================================");
        return "crear_coordenada";
    }

    @RequestMapping("/editar/{id_ruta}/{id_coordenada}")
    public String editarCoordenada(Model model, @PathVariable("id_ruta")Long id_ruta, @PathVariable("id_coordenada") Long id_coordenada ){
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id_ruta));
        Coordenada coordenada = coordenadaServices.buscarUnaCoordenada(id_coordenada);
        model.addAttribute("id_coordenada",String.valueOf(coordenada.getId()));
        model.addAttribute("longitud",String.valueOf(coordenada.getLongitud()));
        model.addAttribute("latitude",String.valueOf(coordenada.getLatitude()));


        return "editar_coordenada";
    }

    @PostMapping("/editar")
    public ModelAndView guardarCoordenadaModificada(@RequestParam("latitude")double latitude,@RequestParam("longitud")double longitud,
                                                    @RequestParam("oldLat")double oldLat, @RequestParam("oldLong") double oldLong ,Model model, @RequestParam("ruta")Long id_ruta, @RequestParam("coordenada")Long id_coordenada){
        Ruta currentRuta = rutaServices.buscarRutaPorId(id_ruta);
        boolean modificado = false;
        List<Coordenada> coordenadas = coordenadaServices.buscarCoordenadaPorLatitudLongitud(oldLat,oldLong);

        for(Coordenada c :coordenadas){
            c.setLatitude(latitude);
            c.setLongitud(longitud);
            if(coordenadaServices.guardarCoordenada(c)!=null){
                modificado= true;
            }
        }

        if(modificado){
            return new ModelAndView("redirect:/ruta/listar/coordenadas/"+currentRuta.getId());
        }else {

            model.addAttribute("ruta", rutaServices.buscarRutaPorId(id_ruta));
            model.addAttribute("coordenada", coordenadaServices.buscarUnaCoordenada(id_coordenada));
            return new ModelAndView("crear_coordenada");
        }

    }

    @Transactional
    @RequestMapping("/eliminar/{id_ruta}/{id_coordenada}")
    public ModelAndView eliminarRuta(@PathVariable("id_ruta")Long id_ruta, @PathVariable("id_coordenada")Long id_coordenada ){
        List<Coordenada> coordenadas= rutaServices.buscarRutaPorId(id_ruta).getCoordenadas();
        for(Coordenada coordenada:coordenadas) {
            if (coordenada.getId().equals(id_coordenada)) {
                coordenadas.remove(coordenada);
            }
        }
        Ruta ruta =  rutaServices.buscarRutaPorId(id_ruta);
        ruta.setCoordenadas(coordenadas);
        rutaServices.guardarRuta(ruta);
        return new ModelAndView("redirect:/ruta/listar/coordenadas/"+id_ruta);
    }

}
