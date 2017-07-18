package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.*;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by anyderre on 30/06/17.
 */
public interface ChequeoRepository extends CrudRepository<Chequeo, BigInteger> {

    Chequeo save (Chequeo chequeo);

    //List<Chequeo> findAll();

    List<Chequeo> findAllByEsEntradaAndFechaRegistrada(Boolean esEntrada, Long fechaRegistrada);

    List<Chequeo> findAllByFechaRegistradaBetween(Long fecha1, Long fecha2);

    List<Chequeo> findAllByFechaRegistradaGreaterThan(Long fechaRegistrada);

    List<Chequeo> findAllByFechaRegistradaLessThan(Long fechaRegistrada);

    //Actividad en una parada
    List<Chequeo> findChequeoByParadaIdOrderByFechaRegistrada(Long id);


    List<Chequeo> findChequeoByParadaIdAndEsEntradaAndFechaRegistrada(Long id, Boolean esEntrada, Long fechaRegistrada);


    Boolean deleteChequeoByFechaRegistrada(Long fechaRegistrada);

    Boolean deleteChequeoByFechaRegistradaGreaterThan(Long fechaRegistrada);

    Boolean deleteChequeoByFechaRegistradaBetween(Long fecha1, Long fecha2);
}
