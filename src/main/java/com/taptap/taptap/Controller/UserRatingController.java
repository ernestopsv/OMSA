package com.taptap.taptap.Controller;

import com.taptap.taptap.Entity.Usuario;
import com.taptap.taptap.Service.RolServices;
import com.taptap.taptap.Service.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/rating")
public class UserRatingController {
    @Autowired
    private UsuarioServices usuarioServices;
    @Autowired
    private RolServices rolServices;
    @RequestMapping("/verComentarios/")
    public String verUserRating(HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        user.setRoles(rolServices.rolesUsuario(user));
        model.addAttribute("usuario", user);
        return "user_rating";
    }
}
