package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by anyderre on 30/06/17.
 */
public interface RutaRepository extends CrudRepository<Ruta, Long> {
    //Encontrar ruta por nombre Corredor
    List<Ruta> findAllByNombreCorredor(String nombre);

    //Encontrar todas las rutas
    List<Ruta> findAll();

    List<Ruta> findAllByCiudad(String ciudad);

    List<Ruta> findAll(Pageable pageable);


    //encontrar ruta con id
    Ruta findRutaById(Long id);

    //Guardar Ruta
    Ruta save(Ruta ruta);

    //eliminar una ruta por su id
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Ruta r SET r.distanciaTotal = :distanciaTotal, r.esDireccionSubida=:esDireccionSubida, r.fechaUltimaModificacion=:fechaUltimaModificacion, r.nombreCorredor=:nombreCorredor WHERE r.id = :id")
    void modifyRutaById(@Param("distanciaTotal")Float distanciaTotal, @Param("esDireccionSubida")Boolean esDireccionSubida, @Param("fechaUltimaModificacion")Long fechaUltimaModificacion , @Param("nombreCorredor") String nombreCorredor, @Param("id") Long id);




}
