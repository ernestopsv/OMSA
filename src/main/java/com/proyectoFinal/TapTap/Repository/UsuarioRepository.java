package com.proyectoFinal.TapTap.Repository;

import com.proyectoFinal.TapTap.Entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario save(Usuario usuario);

    Usuario findByUsernameAndHabilitadoIsTrue(String username);

    Usuario findByIdAndHabilitadoIsTrue(Long id);

    List<Usuario>findAllByHabilitadoIsTrue(Pageable pageable);

    List<Usuario> findAllByHabilitadoIsTrueAndUsername(String username);

    List<Usuario>findAllByHabilitadoIsTrue();

    void deleteById(Long id);
}
