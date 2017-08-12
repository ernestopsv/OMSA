package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario save(Usuario usuario);

    List<Usuario> findByAdmin(Boolean value);

    Usuario findByUsername(String username);

    Usuario findById(Long id);

    List<Usuario>findAll();

    void deleteById(Long id);
}
