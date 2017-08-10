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
@Transactional
public class ParadaServices {
    @Autowired
    ParadaRepository paradaRepository;


    public Boolean eliminarParadaPor(Long id){
        return paradaRepository.deleteById(id);
    }
    public Boolean eliminarParadaPorRutaId(Long id){
        return paradaRepository.deleteAllByRutaId(id);
    }

    public Parada encontrarParadaPorId(Long id){
        return paradaRepository.findById(id);
    }

    public Parada guardarParada(Parada parada){
        return paradaRepository.save(parada);
    }

    public List<Parada> buscarParadaPorRutaId(Long id){
        return paradaRepository.findAllByRutaId(id);
    }

    public Parada buscarParada(Long id){
        return paradaRepository.findById(id);
    }
    Boolean modificarParadaPorId(Parada parada){
        return paradaRepository.modifyParadaById(parada.getCoordenada(),parada.getNombre(),parada.getRuta(),parada.getParadaAnterior(),parada.getParadaSiguiente(),parada.getId());
    }
}
