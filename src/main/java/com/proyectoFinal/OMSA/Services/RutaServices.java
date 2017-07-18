package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class RutaServices {
    @Autowired
    RutaRepository rutaRepository;

    public List<Ruta> buscarRutaPorNombreCorredor(String nombre){
        return rutaRepository.findAllByNombreCorredor(nombre);
    }

    public List<Ruta> buscarTodasLasRutas(){
        return rutaRepository.findAll();
    }

    public Ruta buscarRutaPorId(Long id){
        return rutaRepository.findRutaById(id);
    }

    public Ruta guardarRuta(Ruta ruta){
        return rutaRepository.save(ruta);
    }

    public Ruta eliminarRutaPorId(Long id){
        return rutaRepository.deleteById(id);
    }

    public Boolean modificarRutaPorId(Ruta ruta){
        return rutaRepository.modifyRutaById(ruta.getDistanciaTotal(), ruta.getEsDireccionSubida(),ruta.getFechaUltimaModificacion(),ruta.getNombreCorredor(),ruta.getId());
    }
}
