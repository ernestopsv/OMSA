package com.taptap.taptap.Repository;

import com.taptap.taptap.Entity.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository  extends CrudRepository<Rol, Long> {

    List<Rol> findAllByHabilitadoIsTrue();

    List<Rol> findAllByUsernameAndHabilitadoIsTrue(String Username);

    void deleteRolById(Long id);

    void deleteRolByUsername(String username);
}
