package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.management.monitor.MonitorSettingException;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
@RequestMapping("/zonaAdmin")
public class AdminController {
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping("/ver_usuarios")
    public String index(Model model){
        model.addAttribute("",usuarioService.buscarUsuarios());
        return "ver_usuarios";
    }

    @RequestMapping("/registrar")
    public  String agregar(Model model){
        model.addAttribute("usuario",new Usuario());
        return "/registrar";
    }
    @PostMapping("/registrar")
    public ModelAndView agregar(@ModelAttribute Usuario usuario, Model model){
        usuario.setAdmin(false);
        if (usuarioService.guardarUsuario(usuario)!=null){
            return new ModelAndView( "redirect:/");
        }
        model.addAttribute("error", "no se pudo guardar el usuario");
        return new ModelAndView("/registrar");
    }

    @RequestMapping("/editar")
    public  String modificar(Model model, @RequestParam("id")Long id){
        model.addAttribute("usuario",usuarioService.buscarUnUsuario(id));
        return "/editar";
    }

    @PostMapping("/editar")
    public ModelAndView modificar(@ModelAttribute Usuario usuario, Model model){
        if (usuarioService.guardarUsuario(usuario)!=null){
            return new ModelAndView( "redirect:/ver_usuarios");
        }
        model.addAttribute("error", "Averigua bien los campos");
        return new ModelAndView("/editar");
    }
    @RequestMapping("/eliminar_usuario")
    public String eliminarUsuario(@RequestParam("id")Long id){
        usuarioService.eliminarUsuario(id);
        return "redirect:/ver_usuarios";
    }

}
