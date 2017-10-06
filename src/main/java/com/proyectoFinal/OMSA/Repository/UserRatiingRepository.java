package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.UserRating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Dany on 06/10/2017.
 */
public interface UserRatiingRepository extends CrudRepository<UserRating, Long> {
    List<UserRating> findAll();
    List<UserRating> findAllByFechaPublicadaAfter(Long fecha);
    List<UserRating> findAllByFechaPublicadaBefore(Long fecha);

    UserRating save (UserRating userRating);

    void deleteById(Long id);

    void deleteAllByComentario(String comentario);
    void deleteAllByFechaPublicadaBetween(Long fecha1, Long fecha2);
    void deleteAllByFechaPublicadaAfter(Long fecha);
    void deleteAllByFechaPublicadaBefore(Long fecha);

}
