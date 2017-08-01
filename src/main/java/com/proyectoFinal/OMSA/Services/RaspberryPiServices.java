package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class RaspberryPiServices {
    @Autowired
    ReaspberryPiRepository reaspberryPiRepository;

    public RaspberryPiAPI buscarRaspberryPiPorid(Long id){
        return reaspberryPiRepository.findRaspberryPiAPIById(id);
    }
    public RaspberryPiAPI buscarRaspberryPiPorNumeroSerial(String numeroSerial){
        return reaspberryPiRepository.findRaspberryPiAPIByNumeroSerial(numeroSerial);
    }
    public Boolean eliminarRaspberryPiPorId(Long id){
        return reaspberryPiRepository.deleteRaspberryPiAPIById(id);
    }
    public Boolean eliminarRapsberryPorNumeroSerial(String numeroSerial){
        return reaspberryPiRepository.deleteRaspberryPiAPIByNumeroSerial(numeroSerial);
    }
    public RaspberryPiAPI guardarRaspberryPi(RaspberryPiAPI raspberryPiAPI){
        return reaspberryPiRepository.save(raspberryPiAPI);
    }
    public RaspberryPiAPI buscarRaspberryPiPorIdAutobus(Long id){
        return reaspberryPiRepository.findRaspberryPiAPIByAutobusId(id);
    }
}
