package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.Rol;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolServices rolServices;

    @Transactional
    public void  eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    public List<Usuario> buscarUsuarios(int page, int itemPerPage){
        Pageable pageable = new PageRequest(page,itemPerPage);
        return usuarioRepository.findAll(pageable);
    }

    public List<Usuario> buscarTodosUsuarios(){
        return usuarioRepository.findAll();
    }
    public List<Usuario> buscarUsuariosPorRoles(Boolean isadmin){
        return usuarioRepository.findByAdmin(isadmin);
    }

    public List<Usuario> buscarUsuarioPorNombreUsuario(String username){
        return usuarioRepository.findAllByUsername(username);
    }

    @Transactional
    public void eliminarUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario buscarUnUsuario(Long id){
        return usuarioRepository.findById(id);
    }

    @Transactional
    public void crearAdmin(){
        List<Usuario> usuarios = buscarUsuarioPorNombreUsuario("admin");
        if(usuarios.size()<1){
            Usuario usuario =  new Usuario();
            usuario.setName("OMSA");
            usuario.setUsername("admin");
            usuario.setPassword("omsa1234");
            guardarUsuario(usuario);
            Rol rol = new Rol();
            rol.setUsuario(usuario);
            rol.setRol("ROLE_ADMIN");
            rolServices.creacionRol(rol);
        }
    }
}

