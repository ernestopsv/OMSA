package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Entities.Rol;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.RolServices;
import com.proyectoFinal.OMSA.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
@RequestMapping("/zonaAdmin")
public class AdminController {

    @Autowired
    RolServices rolServices;
    @Autowired
    UsuarioServices usuarioServices;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping("/usuarios")
    public String index(HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario usuario = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", usuario);
        model.addAttribute("size", usuarioServices.buscarTodosUsuarios().size());
        return "ver_usuarios";
    }

    @RequestMapping("/registrar")
    public  String agregar(HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario usuario = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuario",new Usuario());
        return "crear_usuarios";
    }
    @PostMapping("/registrar")
    public ModelAndView agregar(@ModelAttribute Usuario usuario, @RequestParam("roles")String[] roles, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);

        System.out.println(usuario.getName()+"/"+usuario.getPassword()+"/"+usuario.getUsername());
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        List<Rol> rols= new ArrayList<>();
        if(roles!=null){
            for(String rol : roles){
                Rol r = new Rol();
                r.setRol(rol);
                r.setUsername(usuario.getUsername());
                rols.add(r);
            }
        }
       // usuario.setRoles(rols);
        if (usuarioServices.guardarUsuario(usuario)!=null){
            return new ModelAndView( "redirect:/");
        }
        model.addAttribute("error", "no se pudo guardar el usuario");
        return new ModelAndView("crear_usuarios");
    }

    @RequestMapping("/editar/{id}")
    public  String modificar( @PathVariable("id")Long id, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        model.addAttribute("usuario", user);
        model.addAttribute("usuario", usuarioServices.buscarUnUsuario(id));
        return "editar_usuarios";
    }

    @PostMapping("/editar")
    public ModelAndView modificar(@RequestParam("roles") String[] roles, @RequestParam("id")Long id, @RequestParam("username") String username,
                                  @RequestParam("password") String password, @RequestParam("name") String nombre, HttpServletRequest request, Model model){
        String uname = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(uname);
        model.addAttribute("usuario", user);
        Usuario usuario = new Usuario();
        usuario.setName(nombre);
        usuario.setPassword(bCryptPasswordEncoder.encode(password));
        usuario.setUsername(username);
        if (usuarioServices.guardarUsuario(usuario)!=null){
            return new ModelAndView( "redirect:/usuarios");
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
                                    r.setUsername(usuario.getUsername());
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
        usuarioServices.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

}
