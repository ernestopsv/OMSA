package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Entities.Rol;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
//@EnableWebMvc
public class IndexController {
    @Autowired
    ParadaServices paradaServices;

    @Autowired
    RutaServices rutaServices;

    @Autowired
    RolServices rolServices;

    @Autowired
    AutobusServices autobusServices;

    @Autowired
    UsuarioServices usuarioServices;

    @Autowired
    ChequeoServices chequeoServices;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
     public ModelAndView paginaPrincipal(HttpServletRequest request, Model model){
      //  Usuario usuario = (Usuario) request.getSession(true).getAttribute("usario");
//        if(usuario==null){
//            return new ModelAndView("redirect:/usuario/iniciar_sesion");
//        }
        HttpSession session = request.getSession();
        String username = request.getSession().getAttribute("username").toString();
        Usuario usuario = usuarioServices.buscarUsuarioPorUsername(username);

        model.addAttribute("usuario", usuario);
        List<Rol> role = rolServices.rolesUsuario(usuario);
        model.addAttribute("roles", role);
        session.setAttribute("roles",role);

        return new ModelAndView("index");
   }

}
