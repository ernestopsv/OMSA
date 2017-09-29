package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Entities.Coordenada;
import com.proyectoFinal.OMSA.Entities.Ruta;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.CoordenadaServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import com.proyectoFinal.OMSA.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

   @Autowired
    UsuarioServices usuarioServices;

    @RequestMapping("/crear/{id}")
    public String crearCoordenada(@PathVariable("id")Long id, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);

        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        model.addAttribute("coordenada", new Coordenada());
        return "crear_coordenada";
    }

    @PostMapping("/crear")
    public String guardarCoordenadaCreada(@RequestParam("latitude")double latitud, @RequestParam("longitud")double longitud, @RequestParam("ruta")Long id, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);

        Ruta currentRuta = rutaServices.buscarRutaPorId(id);
        List<Ruta> rutas = rutaServices.buscarRutaPorNombreCorredor(currentRuta.getNombreCorredor());

        boolean guardado = false;
        Coordenada coordenada = new Coordenada();
        coordenada.setLatitude(latitud);
        coordenada.setLongitud(longitud);
        rutas.get(0).getCoordenadas().add(coordenada);
        System.out.println(coordenada.getLatitude()+"/"+coordenada.getLongitud());
        if(rutaServices.guardarRuta(rutas.get(0))!=null){

            guardado= true;
        }
        if(rutas.size()>1){

            Coordenada coordenada1 = new Coordenada();
            coordenada1.setLongitud(coordenada.getLongitud());
            coordenada1.setLatitude(coordenada.getLatitude());
            rutas.get(1).getCoordenadas().add(coordenada1);
            if(rutaServices.guardarRuta(rutas.get(1))!=null){
                guardado= true;
            }
        }

        if(guardado){

            model.addAttribute("message", "success");
        }else {

            model.addAttribute("message", "error");
        }
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        model.addAttribute("coordenada", new Coordenada());
        return "crear_coordenada";
    }

    @RequestMapping("/editar/{id_ruta}/{id_coordenada}")
    public String editarCoordenada( @PathVariable("id_ruta")Long id_ruta, @PathVariable("id_coordenada") Long id_coordenada,  HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id_ruta));
        Coordenada coordenada = coordenadaServices.buscarUnaCoordenada(id_coordenada);
        model.addAttribute("id_coordenada",String.valueOf(coordenada.getId()));
        model.addAttribute("longitud",String.valueOf(coordenada.getLongitud()));
        model.addAttribute("latitude",String.valueOf(coordenada.getLatitude()));
        return "editar_coordenada";
    }

    @PostMapping("/editar")
    public ModelAndView guardarCoordenadaModificada(@RequestParam("latitude")double latitude,@RequestParam("longitud")double longitud,
                                                    @RequestParam("oldLat")double oldLat, @RequestParam("oldLong") double oldLong ,@RequestParam("ruta")Long id_ruta,
                                                    @RequestParam("coordenada")Long id_coordenada, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);

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
