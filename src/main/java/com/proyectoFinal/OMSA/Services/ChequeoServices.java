package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class ChequeoServices {
    @Autowired
    ChequeoRepository chequeoRepository;

    @Transactional
    public Chequeo guardarChequeo(Chequeo chequeo){
        return chequeoRepository.save(chequeo);
    }

    public List<Chequeo> buscarChequeoPorFechaRegistradaYPorTipo(Chequeo chequeo){
        return chequeoRepository.findAllByEsEntradaAndFechaRegistrada(chequeo.getEsEntrada(), chequeo.getFechaRegistrada());
    }

    public List<Chequeo> buscarChequeoEntreRangoFecha(Long fecha1, Long fecha2){
        return chequeoRepository.findAllByFechaRegistradaBetween(fecha1, fecha2);
    }

    public List<Chequeo> buscarChequeoDespuesDeUnaFecha(Long fecha){
        return chequeoRepository.findAllByFechaRegistradaGreaterThan(fecha);
    }

    public List<Chequeo> buscarChequeAntesDeUnaFecha(Long fecha){
        return  chequeoRepository.findAllByFechaRegistradaLessThan(fecha);
    }

    public List<Chequeo> buscarChequoPorParadaId(Long id){
        return chequeoRepository.findChequeoByParadaIdOrderByFechaRegistrada(id);
    }

    public List<Chequeo> buscarChequeoPorParadaIdAndCaracteristicas(Chequeo chequeo){
        return chequeoRepository.findChequeoByParadaIdAndEsEntradaAndFechaRegistrada(chequeo.getParada().getId(), chequeo.getEsEntrada(), chequeo.getFechaRegistrada());
    }
    public Boolean eliminarChequeoPorFecha(Long fecha){
        return chequeoRepository.deleteChequeoByFechaRegistrada(fecha);
    }

    public Boolean eliminarChequeoPorRangoDeFecha(Long fecha1, Long fecha2){
        return chequeoRepository.deleteChequeoByFechaRegistradaBetween(fecha1, fecha2);
    }

    public Boolean eliminarChequeoDespuesDeUnaFecha(Long fecha){
        return chequeoRepository.deleteChequeoByFechaRegistradaGreaterThan(fecha);
    }
}

