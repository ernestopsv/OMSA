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
     * @param numeroSerial
     * @param latitud
     * @param longitud
     * @param fechaRegistrada
     * @return
     */
    @RequestMapping(value = "/autobus/modificar/posicion/", method =RequestMethod.POST, produces = ACCECPT_TYPE, consumes = ACCECPT_TYPE)
    public String modificarCoordenadaAutobus(@RequestParam("numeroSerial")String numeroSerial, @RequestParam("latitud") double latitud,
                                             @RequestParam("longitud")double longitud , @RequestParam("fecha") Long fechaRegistrada) {
        Autobus autobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(numeroSerial);

        if(autobus == null){
            return new Gson().toJson("Este autobus no existe");
        }

        autobus.setUltimaFechaModificada(fechaRegistrada);
        Coordenada coordenada = autobus.getCoordenada();
        coordenada.setLatitude(latitud);
        coordenada.setLongitud(longitud);
        autobus.setCoordenada(coordenada);
        autobusServices.modifcarCoordenadaAutobus(autobus);

        return new Gson().toJson("Posicion autobus modificado exitosamente");
    }
    /**
     *
     * modificar posicion de un autobus
     * @param estadoActual
     * @param fechaRegistrada
     * @param latitud
     * @param longitud
     * @return
     */
    @RequestMapping(value = "/autobus/modificar/estado", method =RequestMethod.POST, produces = ACCECPT_TYPE, consumes =ACCECPT_TYPE)
    public String modificarEstadoAutobus(@RequestParam("estadoActual") Boolean estadoActual, @RequestParam("numeroSerial")String numeroSerial, @RequestParam("fecha") Long fechaRegistrada,
                                         @RequestParam("longitud")Double longitud, @RequestParam("latitud")Double latitud){
        Autobus autobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(numeroSerial);
        if(autobus == null){
            return new Gson().toJson("El autobus que quieres modificar no existe");
        }
        autobus.setActivo(estadoActual);
        autobus.setUltimaFechaModificada(fechaRegistrada);
        Coordenada coordenada = new Coordenada();
        coordenada.setLongitud(longitud);
        coordenada.setLatitude(latitud);
        //Parada parada = getParadaReal()

        autobusServices.modificarEstadoAutobus(autobus);

        return new Gson().toJson( "Estado Autobus modificado exitosamente");

    }
    /**
     * modificar posicion de un autobus
     * @param numeroSerial
     * @param cantidadPasajeros
     * @param  fechaRegistrada
     * @return
     */
    @RequestMapping(value = "/autobus/modificar/cantidadPasajeros", method =RequestMethod.POST, produces = ACCECPT_TYPE, consumes = ACCECPT_TYPE)
    public String modificarCantidadPasajerosAutobus( @RequestParam("numeroSerial") String numeroSerial,@RequestParam("cantidadPasajeros") Integer cantidadPasajeros, @RequestParam("fecha") Long fechaRegistrada){
        Autobus autobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(numeroSerial);
        if(autobus == null){
            return new Gson().toJson("El autobus que quieres modificar no existe");
        }
        autobus.setCantidadDePasajerosActual(cantidadPasajeros);
        autobus.setUltimaFechaModificada(fechaRegistrada);
        autobusServices.modificarCantidadPasajeros(autobus);
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
    public String guardarChequeo(@RequestBody Chequeo chequeo, @RequestParam("numero_serial")String numeroSerial){
        //obteniendo la parada mas cerca a ese punto
        chequeo.setParada(getParadaReal(chequeo, numeroSerial));
        chequeo.setAutobus(autobusServices.buscarAutobusPorRaspberryNumeroSerial(numeroSerial));
        return new Gson().toJson(chequeoServices.guardarChequeo(chequeo));
    }

    @RequestMapping(value="/chequeo/buscar/{id_autobus}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public List<Chequeo> buscarChequeos(@PathVariable("id_autobus")Long id){
       List<Chequeo> chequeos = chequeoServices.buscarChequeoPorAutobusId(id);
        if(chequeos== null){
            return new ArrayList<>();
        }
        return chequeos;
    }
    private Parada getParadaReal(Chequeo chequeo,String numeroSerial){
        Autobus autobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(numeroSerial);
        Ruta ruta = autobus.getRuta();
        List<Parada> paradas = paradaServices.buscarParadaPorRutaId(ruta.getId());
        Integer cont =1;
        double distancia=1000000000;
        double distanciaActual=0;
        Integer indexes=0;
        for(Parada parada:paradas){
            distanciaActual =Math.sqrt(Math.pow((parada.getCoordenada().getLatitude()-chequeo.getParada().getCoordenada().getLatitude()), 2)+Math.pow((parada.getCoordenada().getLongitud()-chequeo.getParada().getCoordenada().getLongitud()),2));
            if (distanciaActual<distancia){
                //distancia =distanciaActual;
                indexes = cont;
            }
            cont++;
        }

        return paradas.get(indexes);
    }

    private Ruta rutaReal(Coordenada coordenada, Autobus autobus){
        return new Ruta();
    }
}
