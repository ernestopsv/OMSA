package com.proyectoFinal.TapTap.Repository;

import com.proyectoFinal.TapTap.Entities.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anyderre on 30/06/17.
 */
@Repository
public interface CoordenadaRepository extends CrudRepository<Coordenada, Long> {

    List<Coordenada> findAllByHabilitadoIsTrue();

    Coordenada findByIdAndHabilitadoIsTrue(Long id);

    Coordenada save(Coordenada coordenada);

    Coordenada findCoordenadaByLatitudeAndLongitudAndHabilitadoIsTrue(double latitud, double longitud);
    void deleteCoordenadaById(Long id);



}
