package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.UserRating;
import com.proyectoFinal.OMSA.Repository.UserRatiingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dany on 06/10/2017.
 */
@Service
public class UserRatingServices {
    @Autowired
    UserRatiingRepository userRatiingRepository;

    public UserRating guardarComentario(UserRating userRating){
        return userRatiingRepository.save(userRating);
    }

    public List<UserRating> buscarComentario(){
        return userRatiingRepository.findAll();
    }

    public List<UserRating> buscarTodoPorFechaPosterior(Long fecha){
        return userRatiingRepository.findAllByFechaPublicadaAfter(fecha);
    }
    public List<UserRating> buscarTodoPorFechaAnterior(Long fecha){
        return userRatiingRepository.findAllByFechaPublicadaAfter(fecha);
    }

    @Transactional
    public  void  elimarRutaPorId(Long id){
        userRatiingRepository.deleteById(id);
    }
    @Transactional
    public  void  eliminarRutaPorComentario(String comentario){
        userRatiingRepository.deleteAllByComentario(comentario);
    }

    @Transactional
    public  void  eliminarRutaPorFechaEntre(Long fecha1, Long fecha2){
        userRatiingRepository.deleteAllByFechaPublicadaBetween(fecha1, fecha2);
    }

    @Transactional
    public void eliminarTodoPorFechaPosterior(Long fecha){
        userRatiingRepository.deleteAllByFechaPublicadaBefore(fecha);
    }
    public void eliminarTodoPorFechaAnterior(Long fecha){
        userRatiingRepository.findAllByFechaPublicadaAfter(fecha);
    }
}
