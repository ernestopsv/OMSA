package com.proyectoFinal.OMSA.Repository;


import com.proyectoFinal.OMSA.Entities.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by anyderre on 29/06/17.
 */
@Repository
public interface AutobusRepository extends CrudRepository<Autobus,Long> {

    //buscar un autobus por id
    Autobus findAutobusById(Long id);

    //buscando la lista de los autobus que hay en un ruta
    List<Autobus> findAutobusesByRutaId(Long id);

    //agregando un autobus en la base de datos
    Autobus save(Autobus autobus);

    //leer todos los autobuses
    List<Autobus> findAll();

    List<Autobus> findAllByUltimaParadaId(Long id);

    List<Autobus> findAutobusesByRutaId(Long id,  Pageable pagin);

    //leer los autobuses nules
    List<Autobus> findAutobusesByRutaIsNull(Pageable pagin);
    List<Autobus> findAutobusesByRutaIsNull();

    //buscar un autobus por macAddress del raspberry
    Autobus findAutobusByRaspberryPiNumeroSerial(String numeroSerial);

    //buscar los autobus que son activos
    List<Autobus> findAllByActivoAndRuta(Boolean esActivo, Ruta ruta);

    //eliminando un autobus
    void deleteAutobusById(Long id);

    //

    //Modificando el estado del autobus
    @Modifying
    @Query("UPDATE Autobus a SET a.activo = :activo, a.ultimaFechaModificada=:ultimaFechaModificada WHERE a.id = :id")
    void modifyEstadoAutobusById(@Param("activo") Boolean activo, @Param("ultimaFechaModificada") Long ultimaModificacion, @Param("id") Long id);

    //Modificando posicion actual del autobus
    @Modifying
    @Query("UPDATE Autobus a SET a.coordenada = :coordenada, a.ultimaFechaModificada=:ultimaFechaModificada WHERE a.id = :id")
    void modifyCoordenadaAutobusById(@Param("coordenada") Coordenada coordenada, @Param("ultimaFechaModificada") Long ultimaModificacion, @Param("id") Long id);

    //Modificando cantidad de pasajeros actual actual del autobus
    @Modifying
    @Query("UPDATE Autobus a SET a.cantidadDePasajerosActual = :cantidadActual,a.ultimaParada=:ultimaParada, a.ultimaFechaModificada=:ultimaFechaModificada WHERE a.id = :id")
    void modifyCantidadPasajerosActualDelAutobusById(@Param("cantidadActual") Integer cantidadActual, @Param("ultimaParada") Parada ultimaParada, @Param("ultimaFechaModificada") Long ultimaModificacion, @Param("id") Long id);

    //Modificando cantidad de pasajeros actual actual del autobus
    @Modifying
    @Query("UPDATE Autobus a SET a.ruta = :ruta WHERE a.id = :id")
    void modifyRutaActualAutobus(@Param("ruta") Ruta ruta, @Param("id") Long id);

}