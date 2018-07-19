package com.taptap.taptap.Service;

import com.taptap.taptap.Entity.Coordenada;
import com.taptap.taptap.Repository.CoordenadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
