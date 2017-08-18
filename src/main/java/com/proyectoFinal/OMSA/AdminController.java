package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.management.monitor.MonitorSettingException;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
//@EnableWebMvc
@RequestMapping("/zonaAdmin")
public class AdminController {
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping("/ver/usuarios")
    public String index(Model model){
        model.addAttribute("usuarios",usuarioService.buscarUsuarios());
        return "ver_usuarios";
    }

    @RequestMapping("/registrar")
    public  String agregar(Model model){
        model.addAttribute("usuario",new Usuario());
        return "crear_usuarios";
    }
    @PostMapping("/registrar")
    public ModelAndView agregar(@ModelAttribute Usuario usuario, Model model){
        usuario.setAdmin(false);
        if (usuarioService.guardarUsuario(usuario)!=null){
            return new ModelAndView( "redirect:/");
        }
        model.addAttribute("error", "no se pudo guardar el usuario");
        return new ModelAndView("crear_usuarios");
    }

    @RequestMapping("/editar/{id}")
    public  String modificar(Model model, @PathVariable("id")Long id){
        model.addAttribute("usuario",usuarioService.buscarUnUsuario(id));
        return "editar_usuarios";
    }

    @PostMapping("/editar")
    public ModelAndView modificar(@ModelAttribute Usuario usuario, Model model){
        if (usuarioService.guardarUsuario(usuario)!=null){
            return new ModelAndView( "redirect:/ver/usuarios");
        }
        model.addAttribute("error", "Averigue bien los campos!");
        return new ModelAndView("editar_usuarios");
    }
    @RequestMapping("/eliminar/usuario/{id}")
    public String eliminarUsuario(@PathVariable("id")Long id){
        usuarioService.eliminarUsuario(id);
        return "redirect:/ver/usuarios";
    }

}
