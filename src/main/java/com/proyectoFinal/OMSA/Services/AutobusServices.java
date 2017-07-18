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
public class AutobusServices {
    @Autowired
    AutobusRepository autobusRepository;


    public List<Autobus> buscarTodoLosAutobus(){
        return autobusRepository.findAll();
    }

    public List<Autobus> buscarTodosLosAutobusporRuta(Long id){
        return autobusRepository.findAutobusesByRutaId(id);
    }

    @Transactional
    public Autobus guardarAutobus(Autobus autobus){
        return autobusRepository.save(autobus);
    }

    public Autobus buscarAutobusPorId(Long id){
        return autobusRepository.findAutobusById(id);
    }

    public Boolean eliminarAutobusporId(Long id){
        return autobusRepository.deleteAutobusById(id);
    }

    @Transactional
    public Boolean modificarEstadoAutobus(Autobus autobus){
       return autobusRepository.modifyEstadoAutobusById(autobus.getActivo(), autobus.getUltimaFechaModificada(), autobus.getId());
    }

    @Transactional
    public Boolean modifcarCoordenadaAutobus(Autobus autobus){
        return  autobusRepository.modifyCoordenadaAutobusById(autobus.getCoordenada(), autobus.getUltimaFechaModificada(), autobus.getId());
    }

    @Transactional
    public Boolean modificarCantidadPasajeros(Autobus autobus){
        return autobusRepository.modifyCantidadPasajerosActualDelAutobusById(autobus.getCantidadDePasajerosActual(), autobus.getUltimaParada()
        ,autobus.getUltimaFechaModificada(), autobus.getId());
    }

    public Autobus buscarUnAutobus(Long id){
        return autobusRepository.findAutobusById(id);
    }

    public Autobus buscarAutobusPorRaspberryNumeroSerial(String numeroSerial){
        return autobusRepository.findAutobusByRaspberryPiNumeroSerial(numeroSerial);
    }

    public List<Autobus> buscarAutobusActivosYPorRuta(Boolean activo, Ruta ruta){
        return  autobusRepository.findAllByActivoAndRuta(activo, ruta);
    }

    public Boolean modificarRutaAutobus(Autobus autobus){
        return autobusRepository.modifyRutaActualAutobus(autobus.getRuta(),autobus.getId());
    }
}
