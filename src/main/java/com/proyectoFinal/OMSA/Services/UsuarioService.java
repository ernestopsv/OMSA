package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    public void  eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }
    public List<Usuario> buscarUsuarios(){
        return usuarioRepository.findAll();
    }
    public List<Usuario> buscarUsuariosPorRoles(Boolean isadmin){
        return usuarioRepository.findByAdmin(isadmin);
    }
    public void eliminarUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }
    public Usuario buscarUnUsuario(Long id){
        return usuarioRepository.findById(id);
    }
}

