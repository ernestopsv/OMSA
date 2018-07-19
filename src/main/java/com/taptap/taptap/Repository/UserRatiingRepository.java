package com.taptap.taptap.Repository;

import com.taptap.taptap.Entity.UserRating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserRatiingRepository extends CrudRepository<UserRating, Long> {
    List<UserRating> findAll();
    List<UserRating> findAllByFechaPublicadaAfter(Long fecha);
    List<UserRating> findAllByFechaPublicadaBefore(Long fecha);
    List<UserRating> findAllByFechaPublicadaBetween(Long fecha, Long fecha2);
    List<UserRating> findUserRatingsByFechaPublicadaIsGreaterThanEqualAndFechaPublicadaIsLessThanEqual(long fecha, long  fecha2);

    UserRating save (UserRating userRating);

    ArrayList <UserRating> findAll(Pageable pageable);

    ArrayList<UserRating> findAllByIdGreaterThanEqualOrderByNumeroDePuntuacionDesc(Long id, Pageable pageable);
    void deleteById(Long id);

    void deleteAllByComentario(String comentario);
    void deleteAllByFechaPublicadaBetween(Long fecha1, Long fecha2);
    void deleteAllByFechaPublicadaAfter(Long fecha);
    void deleteAllByFechaPublicadaBefore(Long fecha);

}
