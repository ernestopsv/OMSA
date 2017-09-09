package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by anyderre on 30/06/17.
 */
@Repository
public interface ChequeoRepository extends CrudRepository<Chequeo, BigInteger> {

    Chequeo save (Chequeo chequeo);

    //List<Chequeo> findAll();

    List<Chequeo> findAllByEsEntradaAndFechaRegistrada(Boolean esEntrada, Long fechaRegistrada);

    List<Chequeo> findAllByAutobusId(Long id);

    List<Chequeo> findAllByFechaRegistradaBetween(Long fecha1, Long fecha2);

    List<Chequeo> findAllByFechaRegistradaGreaterThan(Long fechaRegistrada);

    List<Chequeo> findAllByFechaRegistradaLessThan(Long fechaRegistrada);

    //Actividad en una parada
    List<Chequeo> findChequeoByParadaIdOrderByFechaRegistrada(Long id);


    List<Chequeo> findChequeoByParadaIdAndEsEntradaAndFechaRegistrada(Long id, Boolean esEntrada, Long fechaRegistrada);


    void deleteChequeoByFechaRegistrada(Long fechaRegistrada);

    void deleteChequeoByFechaRegistradaGreaterThan(Long fechaRegistrada);

    void deleteChequeoByFechaRegistradaBetween(Long fecha1, Long fecha2);
}
