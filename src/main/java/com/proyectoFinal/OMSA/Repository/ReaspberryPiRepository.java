package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by anyderre on 30/06/17.
 */
public interface ReaspberryPiRepository extends CrudRepository<RaspberryPiAPI, Long> {
    //Encontrar un raspberry por su id
    RaspberryPiAPI findRaspberryPiAPIById(Long id);

    RaspberryPiAPI findRaspberryPiAPIByNumeroSerial(String numeroSerial);

    //Eliminar un raspberry por su id
    Boolean deleteRaspberryPiAPIByNumeroSerial(String numeroSerial);

    Boolean deleteRaspberryPiAPIById(Long id);

    //Guardar un raspberry
    RaspberryPiAPI save(RaspberryPiAPI raspberryPiAPI);

    //encontrar todos los raspberry
    List<RaspberryPiAPI> findAll();

    //Encontrar raspberry de un autobus
    RaspberryPiAPI findRaspberryPiAPIByAutobusId(Long id);

    @Modifying
    @Query("UPDATE RaspberryPiAPI r SET r.ipAddress = :ipAddress, r.macAddress=:macAddress, r.numeroSerial=:numeroSerial WHERE r.id = :id")
    Boolean modifyRutaById(@Param("ipAddress")String ipAddress, @Param("macAddress")String macAddress, @Param("numeroSerial")String numeroSerial, @Param("id") Long id);


}
