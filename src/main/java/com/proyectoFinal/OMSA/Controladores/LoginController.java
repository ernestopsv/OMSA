package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UsuarioServices usuarioServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam Optional<String> error){
        return new ModelAndView("login", "error", error);
    }
//
//    @PostMapping("/iniciar_sesion")
//    public ModelAndView login(HttpServletRequest request, @ModelAttribute Usuario us, Model model){
//        Usuario usuario = usuarioService.buscarUsuarioPorUsername(us.getUsername());
//        if(usuario!=null){
//            if(usuario.getUsername().equals(us.getPassword())){
//                request.getSession(true).setAttribute("usuario", usuario);
//                return new ModelAndView("redirect:/");
//            }
//            model.addAttribute("error", "Username or Password Incorrect");
//            return new ModelAndView("login");
//        }
//        model.addAttribute("error", "este usurio no existe");
//        return new ModelAndView("login");
//    }

//    @RequestMapping("/logout")
//    public String logout(HttpServletRequest request){
//        request.getSession().setAttribute("usuario", null);
//        return "redirect:/cer";
//
//    }
}
