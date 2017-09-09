package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    UsuarioServices usuarioServices;

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
