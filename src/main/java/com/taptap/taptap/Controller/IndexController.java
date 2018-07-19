package com.taptap.taptap.Controller;

import com.taptap.taptap.Entity.Usuario;
import com.taptap.taptap.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
        String username = request.getSession().getAttribute("username").toString();
        Usuario usuario = usuarioServices.buscarUsuarioPorUsername(username);
        usuario.setRoles(rolServices.rolesUsuario(usuario));
        model.addAttribute("usuario", usuario);

        return new ModelAndView("charts");
   }

}
