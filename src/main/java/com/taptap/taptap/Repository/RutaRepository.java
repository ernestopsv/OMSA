package com.taptap.taptap.Repository;

import com.taptap.taptap.Entity.Ruta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends CrudRepository<Ruta, Long> {
    //Encontrar ruta por nombre Corredor
    List<Ruta> findAllByHabilitadoIsTrueAndNombreCorredor(String nombre);

    //Encontrar todas las rutas
    List<Ruta> findAllByHabilitadoIsTrue();

    List<Ruta> findAllByCiudadAndHabilitadoIsTrue(String ciudad);

    List<Ruta> findAllByHabilitadoIsTrue(Pageable pageable);


    //encontrar ruta con id
    Ruta findRutaByIdAndHabilitadoIsTrue(Long id);

    //Guardar Ruta
    Ruta save(Ruta ruta);

    //eliminar una ruta por su id
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Ruta r SET r.distanciaTotal = :distanciaTotal, r.esDireccionSubida=:esDireccionSubida, r.fechaUltimaModificacion=:fechaUltimaModificacion, r.nombreCorredor=:nombreCorredor WHERE r.id = :id")
    void modifyRutaById(@Param("distanciaTotal")Float distanciaTotal, @Param("esDireccionSubida")Boolean esDireccionSubida, @Param("fechaUltimaModificacion")Long fechaUltimaModificacion , @Param("nombreCorredor") String nombreCorredor, @Param("id") Long id);




}
