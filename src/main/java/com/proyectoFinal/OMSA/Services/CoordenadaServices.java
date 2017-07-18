package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class CoordenadaServices {
    @Autowired
    public CoordenadaRepository coordenadaRepository;

    public Coordenada buscarUnaCoordenada(Long id){
        return coordenadaRepository.findById(id);
    }

    public Coordenada guardarCoordenada(Coordenada coordenada){
        return coordenadaRepository.save(coordenada);
    }

}
