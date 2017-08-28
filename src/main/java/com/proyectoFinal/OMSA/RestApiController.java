package com.proyectoFinal.OMSA; /**
 * Created by anyderre on 04/07/17.
 */
import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.proyectoFinal.OMSA.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
    private static final String ACCECPT_TYPE= "application/json";
    private static final String CONTENT_TYPE= "application/json";
    @Autowired
    private AutobusServices autobusServices;
    @Autowired
    private ParadaServices paradaServices;
    @Autowired
    private ChequeoServices chequeoServices;
    @Autowired
    private CoordenadaServices coordenadaServices;
    @Autowired
    private RutaServices rutaServices;
    @Autowired
    UsuarioService usuarioService;

//**********************************************************************Autobus*****************************************************
    /**
     * Buscar un autobus por su id
     * @param id
     * @return
     */
    @RequestMapping(value = "/autobus/buscar/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public Autobus autobus(@PathVariable Long id){
        Autobus autobus = autobusServices.buscarUnAutobus(id);
        if (autobus==null){
            return new Autobus();
        }
        return autobus;
    }

    @RequestMapping(value = "/autobus/buscar/ruta/size/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public int getCantdeAutobusPorRuta(@PathVariable("id")Long id){
        return autobusServices.buscarTodosLosAutobusporRuta(id).size();
    }


    @RequestMapping(value = "/autobus/buscar/{page}/{items}/corredor/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Autobus> buscarAutobusPorRutaId(@PathVariable ("page") int page, @PathVariable ("items") int itemsPerPage, @PathVariable ("id")Long corredor){
        List<Autobus> autobuses = autobusServices.buscarAutobusPorRutaId(corredor, page, itemsPerPage);
        if (autobuses==null){
            return new ArrayList<>();
        }
        return (ArrayList<Autobus>) autobuses;
    }
    /**
     *
     * Obtener Listado de autobus de una Ruta
     * @param id
     * @return
     */
    @RequestMapping(value ="/autobuses/buscar/rutas", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Autobus> obetenerAutobusPorRuta(@RequestParam("id_ruta")Long id){
        Ruta ruta =rutaServices.buscarRutaPorId(id);
        if(ruta==null){
//            logger.error("No se encuentra el autobus buscado.", id);
            return new ArrayList<Autobus>();
        }
        List <Autobus> autobuses = autobusServices.buscarTodosLosAutobusporRuta(ruta.getId());
        return (ArrayList<Autobus>) autobuses;
    }

    /**
     *
     * guardar un autobus
     * @param id
     * @return
     */
    @RequestMapping(value ="/autobus/guardar/{id_ruta}", method = RequestMethod.POST, produces =ACCECPT_TYPE, consumes = ACCECPT_TYPE)
    public String guardarAutobus(@RequestBody Autobus autobus, @PathVariable("id_ruta")Long id){

        Ruta ruta = rutaServices.buscarRutaPorId(id);
        autobus.setRuta(ruta);
        Autobus autobus1 = autobusServices.guardarAutobus(autobus);
        if(ruta==null){
          return "La ruta no existe";
        }
        if(autobus1!=null){

            return "autobus guardada";
        }
        return "Error al guardar el autobus";
    }

    /**
     *
     * modificar coordenada de un autobus
     * @param autobus
     * @return
     */
    @RequestMapping(value = "/autobus/modificar/posicion/", method =RequestMethod.PUT, produces = ACCECPT_TYPE, consumes = ACCECPT_TYPE)
    public String modificarCoordenadaAutobus(@RequestBody Autobus autobus) {
        Autobus currentAutobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(autobus.getRaspberryPiNumeroSerial());

        if(currentAutobus == null){
            return new Gson().toJson("Este autobus no existe");
        }
        currentAutobus.getCoordenada().setLatitude(autobus.getCoordenada().getLatitude());
        currentAutobus.getCoordenada().setLongitud(autobus.getCoordenada().getLongitud());
        currentAutobus.setUltimaFechaModificada(autobus.getUltimaFechaModificada());
        autobusServices.guardarAutobus(currentAutobus);

        return new Gson().toJson("Posicion autobus modificado exitosamente");
    }
    /**
     *
     * modificar posicion de un autobus
     * @param autobus
     * @return
     */
    @RequestMapping(value = "/autobus/modificar/estado", method =RequestMethod.PUT, produces = ACCECPT_TYPE, consumes =ACCECPT_TYPE)
    public String modificarEstadoAutobus(@RequestBody Autobus autobus){
        Autobus currentAutobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(autobus.getRaspberryPiNumeroSerial());
        if(currentAutobus == null){
            return new Gson().toJson("El autobus que quieres modificar no existe");
        }
        currentAutobus.setActivo(autobus.getActivo());
        currentAutobus.setUltimaFechaModificada(autobus.getUltimaFechaModificada());

        autobusServices.guardarAutobus(currentAutobus);
        return new Gson().toJson( "Estado Autobus modificado exitosamente");

    }
    /**
     * modificar posicion de un autobus
     * @param autobus
     * @return
     */
    @RequestMapping(value = "/autobus/modificar/cantidadPasajeros", method =RequestMethod.PUT, produces = ACCECPT_TYPE, consumes = ACCECPT_TYPE)
    public String modificarCantidadPasajerosAutobus( @RequestBody Autobus autobus){
        Autobus currentAutobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(autobus.getRaspberryPiNumeroSerial());
        if(currentAutobus == null){
            return new Gson().toJson("El autobus que quieres modificar no existe");
        }
        currentAutobus.setCantidadDePasajerosActual(autobus.getCantidadDePasajerosActual());
        currentAutobus.setUltimaFechaModificada(autobus.getUltimaFechaModificada());
        autobusServices.guardarAutobus(currentAutobus);
        return new Gson().toJson("Autobus modificado exitosamente");

    }

    @RequestMapping(value = "/autobus/eliminar/{id}", method = RequestMethod.POST)
    public Boolean eliminarAutobus(@PathVariable("id")Long id){
        Autobus autobus = autobusServices.buscarUnAutobus(id);
        if(autobus==null){
            return false;
        }
        autobusServices.eliminarAutobusporId(id);
        return true;
    }
//----------------------------------------Parada---------------------------------------
    @RequestMapping(value = "/paradas/ruta/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Parada> buscarParadasPorRuta(@PathVariable Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
        if(ruta==null){
            return new ArrayList<>();
        }
        List<Parada> paradas = paradaServices.buscarParadaPorRutaId(id);
        if(paradas.isEmpty()){
            return new ArrayList<>();
        }
        List<Parada> paradasTemp= new ArrayList<>();
        for(Parada parada: paradas){
            parada.setRuta(null);
            paradasTemp.add(parada);
        }
        return new ArrayList<>(paradasTemp);
    }
    /** buscar una parada
     * @param id
     * @return
     */
    @RequestMapping(value = "/parada/buscar/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public Parada buscarParada(@PathVariable Long id){
        Parada parada =paradaServices.buscarParada(id);

        if(parada==null){
            return new Parada();
        }
       return parada;
    }
    /** Guardar una parada
     * @param id
     * @param parada
     * @return
     */
    @RequestMapping(value = "/paradas/guardar/{ruta_id}", method = RequestMethod.POST, produces = ACCECPT_TYPE)
    public String guardarParada(@RequestBody Parada parada, @PathVariable("ruta_id") Long id ){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
        if(ruta!=null){
            parada.setRuta(ruta);
            paradaServices.guardarParada(parada);
            return new Gson().toJson("Parada guardada exitosamente");
        }
        return new Gson().toJson("no se pudo guardar la parada");
    }

    /** Buscar rango de paradas
     * @param id_ruta
     * @param page
     * @param numberOfItems
     * @return
     */
  @RequestMapping(value = "/paradas/buscar/{page}/{items}/ruta/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
  public ArrayList<Parada> buscarTopParadas(@PathVariable("page")int page, @PathVariable("items")int numberOfItems, @PathVariable("id")Long id_ruta){
      List<Parada> paradas = paradaServices.getTopParadas(id_ruta, page, numberOfItems);
      if(paradas==null){
          return new ArrayList<>();
      }
      return (ArrayList<Parada>) paradas;
  }

//---------------------------------------Ruta-------------------------------------------//--------------------------------------Ruta----------------------------------------------------------
@RequestMapping(value="/guardar/ruta/", method =RequestMethod.POST, consumes = ACCECPT_TYPE)
public String guardarRuta(@RequestBody Ruta ruta){
    Ruta ruta1  = rutaServices.guardarRuta(ruta);
    if(ruta1!=null){
        return  new Gson().toJson("Ruta guardada exitosamente");
    }
    return new Gson().toJson("no se pudo guardar la ruta especificada");
}
    @RequestMapping(value = "/ruta/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public Ruta buscarRuta(@PathVariable Long id){
        Ruta ruta =rutaServices.buscarRutaPorId(id);
        if(ruta==null){
            return new Ruta();
        }
        return  ruta;
    }
    @RequestMapping(value = "/ruta/eliminar/{id}", method = RequestMethod.POST, produces = CONTENT_TYPE)
    public Boolean borrarParada(@PathVariable("id")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
        if(ruta!=null){
            paradaServices.eliminarParadaPorRutaId(id);
            rutaServices.eliminarRutaPorId(id);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/ruta/buscar/pagina/{numero}/item/{items}", method = RequestMethod.GET, produces = ACCECPT_TYPE )
    public ArrayList<Ruta> buscarRutasPorPagina(@PathVariable("numero")int page, @PathVariable("items")int numberOfItems ){
        List<Ruta> rutas = rutaServices.buscarRutasPorPagina(page, numberOfItems);
        if(rutas==null){
            return new ArrayList<>();
        }
        return (ArrayList<Ruta>) rutas;
    }
//-------------------------------------Chequeo-----------------------------------------------


    @RequestMapping(value="/chequeo/guardar", method = RequestMethod.POST, consumes = ACCECPT_TYPE)
    public String guardarChequeo(@RequestBody Chequeo chequeo){
        //obteniendo la parada mas cerca a ese punto
        Autobus autobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(chequeo.getAutobus().getRaspberryPiNumeroSerial());

        chequeo.setAutobus(autobus);
        Parada parada = getParadaReal(chequeo);
        parada.setRuta(null);
        chequeo.setParada(parada);
        if(parada==null){
            new Gson().toJson("No se pudo guardar el chequeo");
        }
        autobus.setUltimaParada(parada);
        chequeo.setAutobus(autobus);
        if(chequeoServices.guardarChequeo(chequeo)==null){
            return new Gson().toJson("No se pudo guardar el chequeo");
        }
        return new Gson().toJson("Chequeo guardado");
    }

    @RequestMapping(value="/chequeo/buscar/{id_autobus}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public List<Chequeo> buscarChequeos(@PathVariable("id_autobus")Long id){
       List<Chequeo> chequeos = chequeoServices.buscarChequeoPorAutobusId(id);
        if(chequeos== null){
            return new ArrayList<>();
        }
        return chequeos;
    }
//    import sys
//import math
//
//    dict =[(19.4416166,-70.68727), (19.43919,-70.68845), (19.43493,-70.69110), (19.42904,-70.69471), (19.42482,-70.69729), (19.42227,-70.69899)]
//
//    def calculate(list,tuple):
//    d = 0
//    max =100000000
//    index=0
//    count =1
//            for k in list:
//    d =math.sqrt(pow((tuple[0]-k[0]), 2)+ pow((tuple[1]-k[1]),2))
//    print k, d
//        if(d<max):
//    max=d
//            index=count
//    count=count+1
//            return index
//    print calculate(dict,(19.4406,-70.6873))

    private Parada getParadaReal(Chequeo chequeo){
        Autobus autobus = chequeo.getAutobus();
        Ruta ruta = autobus.getRuta();
        ArrayList<Parada> paradas = (ArrayList<Parada>) paradaServices.buscarParadaPorRutaId(ruta.getId());

        double max=1000000000;
        double distanciaActual=0;
        int indexes=0;
        for(int i = 0; i<paradas.size(); i++){
            distanciaActual =Math.sqrt(Math.pow((paradas.get(i).getCoordenada().getLatitude()-chequeo.getParada().getCoordenada().getLatitude()), 2)+Math.pow((paradas.get(i).getCoordenada().getLongitud()-chequeo.getParada().getCoordenada().getLongitud()),2));
            if (distanciaActual<max){
                max =distanciaActual;
                indexes = i;
            }
        }

        return paradas.get(indexes);
    }

    private Ruta rutaReal(Coordenada coordenada, Autobus autobus){
        return new Ruta();
    }


 //-------------------------------------------------------------Usuario-------------------------------------------------------------------
 @RequestMapping(value = "/usuario/buscar/{page}/item/{items}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
 public ArrayList<Usuario> buscarUsuarioPorPaginas(@PathVariable("items")int items, @PathVariable("page")int page){
        List <Usuario> usuarios = usuarioService.buscarUsuarios(page, items);
        if (usuarios==null){
            return new ArrayList<>();
        }
     return (ArrayList<Usuario>) usuarios;
 }
//-----------------------------------------Coordenada----------------------------------------------------
    @RequestMapping(value = "/ruta/{id}/buscar/coordenada/{start}/{end}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public List<Coordenada> buscarCoordenadas (@PathVariable("id")Long id, @PathVariable("start")int start, @PathVariable("end")int end){

       List<Coordenada> coordenadas = rutaServices.buscarRutaPorId(id).getCoordenadas();

       if(coordenadas.size()==0){
            return new ArrayList<>();
       }else{
           if(end>=coordenadas.size()){
               end = coordenadas.size();
           }
       }

     return coordenadas.subList(start, end);
    }
}
