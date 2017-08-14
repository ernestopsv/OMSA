package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.hibernate.loader.collection.PaddedBatchingCollectionInitializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class ParadaServices {
    @Autowired
    ParadaRepository paradaRepository;

    @Transactional
    public void eliminarParadaPor(Long id){
         paradaRepository.deleteById(id);
    }

    @Transactional
    public void eliminarParadaPorRutaId(Long id){
        paradaRepository.deleteAllByRutaId(id);
    }

    @Transactional
    public Parada guardarParada(Parada parada){
        return paradaRepository.save(parada);
    }

    public List<Parada> buscarParadaPorRutaId(Long id){
        return paradaRepository.findAllByRutaId(id);
    }

    public Parada buscarParada(Long id){
        return paradaRepository.findById(id);
    }

    @Transactional
    void modificarParadaPorId(Parada parada){
        paradaRepository.modifyParadaById(parada.getCoordenada(),parada.getNombre(),parada.getRuta(),parada.getParadaAnterior(),parada.getParadaSiguiente(),parada.getId());
    }
}
