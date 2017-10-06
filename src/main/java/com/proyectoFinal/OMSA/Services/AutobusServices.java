package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Autobus> buscarAutobusPorRutaId(Long id, int page, int itemsPerPage){
        Pageable pageable = new PageRequest(page, itemsPerPage);
        return autobusRepository.findAutobusesByRutaId(id,pageable);
    }
    public List<Autobus> buscarAutobusPorRutaNull(int page, int itemsPerPage){
        Pageable pageable = new PageRequest(page, itemsPerPage);
        return autobusRepository.findAutobusesByRutaIsNull(pageable);
    }
    public int buscarAutobusesNull(){
        return autobusRepository.findAutobusesByRutaIsNull().size();
    }
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

    @Transactional
    public void eliminarAutobusporId(Long id){
        autobusRepository.deleteAutobusById(id);
    }

    @Transactional
    public void modificarEstadoAutobus(Autobus autobus){
         autobusRepository.modifyEstadoAutobusById(autobus.getActivo(), autobus.getUltimaFechaModificada(), autobus.getId());
    }

    @Transactional
    public void modifcarCoordenadaAutobus(Autobus autobus){
         autobusRepository.modifyCoordenadaAutobusById(autobus.getCoordenada(), autobus.getUltimaFechaModificada(), autobus.getId());
    }

    @Transactional
    public void modificarCantidadPasajeros(Autobus autobus){
        autobusRepository.modifyCantidadPasajerosActualDelAutobusById(autobus.getCantidadDePasajerosActual(), autobus.getUltimaParada()
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

    public List<Autobus> buscarAutobusPorUltimaParadaID(Long id){
        return buscarAutobusPorUltimaParadaID(id);
    }

    @Transactional
    public void modificarRutaAutobus(Autobus autobus){
         autobusRepository.modifyRutaActualAutobus(autobus.getRuta(),autobus.getId());
    }
}
