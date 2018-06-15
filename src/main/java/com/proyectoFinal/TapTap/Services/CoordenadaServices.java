package com.proyectoFinal.TapTap.Services;

import com.proyectoFinal.TapTap.Entities.*;
import com.proyectoFinal.TapTap.Repository.*;
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
        return coordenadaRepository.findByIdAndHabilitadoIsTrue(id);
    }

    public Coordenada buscarCoordenadaPorLatitudLongitud(Double latitud, Double longitud){
        return coordenadaRepository.findCoordenadaByLatitudeAndLongitudAndHabilitadoIsTrue(latitud,longitud);
    }

    public Coordenada guardarCoordenada(Coordenada coordenada){
        return coordenadaRepository.save(coordenada);
    }

    public void eliminarCoordenada(Long id ){
        coordenadaRepository.deleteCoordenadaById(id);
    }

}
