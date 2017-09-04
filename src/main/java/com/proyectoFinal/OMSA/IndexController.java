package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Autobus;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
//@EnableWebMvc
@RequestMapping("/")
public class IndexController {
    @Autowired
    ParadaServices paradaServices;

    @Autowired
    RutaServices rutaServices;

    @Autowired
    AutobusServices autobusServices;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ChequeoServices chequeoServices;

    @RequestMapping(value = "/", method = RequestMethod.GET)
     public ModelAndView paginaPrincipal(HttpServletRequest request){
      //  Usuario usuario = (Usuario) request.getSession(true).getAttribute("usario");
//        if(usuario==null){
//            return new ModelAndView("redirect:/usuario/iniciar_sesion");
//        }
        return new ModelAndView("index");
   }

}
