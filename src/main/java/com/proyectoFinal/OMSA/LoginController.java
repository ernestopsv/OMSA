package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller()
@RequestMapping("/usuario")
public class LoginController {
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("usuario", new Usuario());
        return "/login";
    }

    @PostMapping("/login")
    public ModelAndView login(HttpServletRequest request, @ModelAttribute Usuario us, Model model){
        Usuario usuario = usuarioService.buscarUsuarioPorUsername(us.getUsername());
        if(usuario!=null){
            if(usuario.getUsername().equals(us.getPassword())){
                request.getSession(true).setAttribute("usuario", usuario);
                return new ModelAndView("redirect:/");
            }
            model.addAttribute("error", "Username or Password Incorrect");
            return new ModelAndView("/login");
        }
        model.addAttribute("error", "este usurio no existe");
        return new ModelAndView("/login");
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("usuario", null);
        return "redirect:/login";

    }
}
