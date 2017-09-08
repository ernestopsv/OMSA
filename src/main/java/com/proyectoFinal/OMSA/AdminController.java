package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.Rol;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.RolServices;
import com.proyectoFinal.OMSA.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.management.monitor.MonitorSettingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
//@EnableWebMvc
@RequestMapping("/zonaAdmin")
public class AdminController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolServices rolServices;


    @RequestMapping("/ver/usuarios")
    public String index(Model model){
        model.addAttribute("size",usuarioService.buscarTodosUsuarios().size());
        return "ver_usuarios";
    }

    @RequestMapping("/registrar")
    public  String agregar(Model model){
        model.addAttribute("usuario",new Usuario());
        return "crear_usuarios";
    }
    @PostMapping("/registrar")
    public ModelAndView agregar(@ModelAttribute Usuario usuario, Model model, @RequestParam("roles")String[] roles){

        System.out.println(usuario.getName()+"/"+usuario.getPassword()+"/"+usuario.getUsername());
        List<Rol> rols= new ArrayList<>();
        if(roles!=null){
            for(String rol : roles){
                Rol r = new Rol();
                r.setRol(rol);
                r.setUsuario(usuario);

                rols.add(r);
            }
        }
        usuario.setRoles(rols);
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
    public ModelAndView modificar(@RequestParam("roles") String[] roles, @RequestParam("id")Long id, @RequestParam("username") String username,
                                  @RequestParam("password") String password, @RequestParam("name") String nombre, Model model){
        Usuario usuario = new Usuario();
        usuario.setName(nombre);
        usuario.setPassword(password);
        usuario.setUsername(username);
        if (usuarioService.guardarUsuario(usuario)!=null){
            return new ModelAndView( "redirect:/ver/usuarios");
        }

        if(roles!=null){
            List<Rol> rolList= rolServices.rolesUsuario(usuario);
                switch (rolList.size()){
                    case 1:
                        for(Rol rol1 : rolList){
                            for(String rol : roles){
                                if (!rol1.getRol().equals(rol)){
                                    Rol r = new Rol();
                                    r.setRol(rol);
                                    r.setUsuario(usuario);
                                    rolServices.creacionRol(r);
                                }
                            }
                        }
                        break;
                    case 2:
                        if(roles.length==1){
                            for(Rol rol1: rolList){
                                if(!roles[0].equals(rol1.getRol())){
                                    rolServices.elimarRolPorId(rol1.getId());
                                }
                            }
                        }
                        break;
                }
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
