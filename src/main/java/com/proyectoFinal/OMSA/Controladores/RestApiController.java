package com.proyectoFinal.OMSA.Controladores; /**
 * Created by anyderre on 04/07/17.
 */
import com.proyectoFinal.OMSA.Entities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.proyectoFinal.OMSA.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    UsuarioServices usuarioServices;

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
        autobus.getRuta().setParadas(null);
        return autobus;
    }

    @RequestMapping(value = "/autobuses/buscar", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public List<Autobus> buscarTodosLoasAutobuses(){
        List<Autobus> autobuses = autobusServices.buscarTodoLosAutobus();

        if (autobuses==null){
            return new ArrayList<>();
        }
        for(int i=0; i<autobuses.size(); i++){
            autobuses.get(i).setRuta(null);
            autobuses.get(i).setUltimaParada(null);
        }
        return autobuses;
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
    @RequestMapping(value ="/autobuses/buscar/ruta/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Autobus> obetenerAutobusPorRuta(@PathVariable("id")Long id){
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
        if(currentAutobus.getCoordenada()==null){
            Coordenada coordenada = new Coordenada();
            coordenada.setLongitud(autobus.getCoordenada().getLongitud());
            coordenada.setLatitude(autobus.getCoordenada().getLatitude());
            currentAutobus.setCoordenada(coordenada);
            currentAutobus.setUltimaFechaModificada(autobus.getUltimaFechaModificada());
            autobusServices.guardarAutobus(currentAutobus);
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
        if(autobus.getCantidadDePasajerosActual()>=currentAutobus.getCantidadDeAsientos()){
            currentAutobus.setCantidadDePasajerosActual(currentAutobus.getCantidadDeAsientos());

        }else {
            currentAutobus.setCantidadDePasajerosActual(autobus.getCantidadDePasajerosActual());
        }
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
            Ruta ruta1 = new Ruta();
            ruta1.setParadas(null);
            return ruta1;
        }
        ruta.setParadas(null);
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

    /** Buscar Todas las rutas
     * @return
     */
    @RequestMapping(value = "/rutas/buscar", method = RequestMethod.GET, produces = ACCECPT_TYPE )
    public List<Ruta> buscarTodasLasRutas(){
        List<Ruta> rutas = rutaServices.buscarTodasLasRutas();
        if(rutas==null){
            return new ArrayList<>();
        }
        for(Ruta r:rutas){
            r.setParadas(null);
        }
        return rutas;
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




 //-------------------------------------------------------------Usuario-------------------------------------------------------------------
 @RequestMapping(value = "/usuario/buscar/{page}/item/{items}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
 public ArrayList<Usuario> buscarUsuarioPorPaginas(@PathVariable("items")int items, @PathVariable("page")int page){
        List <Usuario> usuarios = usuarioServices.buscarUsuarios(page, items);
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
//______________________________________________________Mobile App Distance and Time________________________________________________
    @RequestMapping(value = "/distancia/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public DistanceAndTime buscarDistanciaYTiempo (@PathVariable("id")Long id){
            Parada parada = paradaServices.buscarParada(id);
            Autobus autobus = autobosMasCercanoPorParada(parada);
           // totalTiempoApiGoogle();
            if(autobus!=null){
                Parada iterador = getParadaMasCerca(autobus);
                double totalTiempo = 0;
                double totalTraffic= 0;
                double totalDistance=0;
                totalTiempo+= totalTiempoApiGoogle(autobus.getCoordenada(), iterador.getCoordenada()).getDuration();
                totalTraffic+= totalTiempoApiGoogle(autobus.getCoordenada(), iterador.getCoordenada()).getDuration_Traffic();
                totalDistance+= totalTiempoApiGoogle(autobus.getCoordenada(), iterador.getCoordenada()).getDistance();

                while (!iterador.getId().equals(parada.getId())){
                    totalTiempo+= totalTiempoApiGoogle(autobus.getCoordenada(), iterador.getCoordenada()).getDuration();
                    totalTraffic+= totalTiempoApiGoogle(autobus.getCoordenada(), iterador.getCoordenada()).getDuration_Traffic();
                    totalDistance+= totalTiempoApiGoogle(autobus.getCoordenada(), iterador.getCoordenada()).getDistance();
                    iterador=paradaServices.buscarParada(iterador.getParadaSiguiente());
                }

                return new DistanceAndTime(totalDistance,totalTiempo,totalTraffic);

            }

            return new DistanceAndTime();
    }
private DistanceAndTime totalTiempoApiGoogle(Coordenada coordenadaAutobus, Coordenada coordenadaParada){
        DistanceAndTime distanceAndTime = new DistanceAndTime();

    String cadena = "https://maps.googleapis.com/maps/api/directions/json?origin=";

    cadena = cadena + coordenadaAutobus.getLatitude();
    cadena = cadena + ",";
    cadena = cadena + coordenadaAutobus.getLongitud();
    cadena = cadena + "&destination=";
    cadena = cadena + coordenadaParada.getLatitude();
    cadena = cadena + ",";
    cadena = cadena + coordenadaAutobus.getLongitud();
    cadena = cadena + "&departure_time=1541202457&traffic_model=best_guess&key=AIzaSyCIvewpnbMTDZbCR3NGc4VwKRYb2BB3Qrs";

    String cadena2 = "https://maps.googleapis.com/maps/api/directions/json?origin=19.488278,-70.7167&destination=19.4710,-70.6913&departure_time=1541202457&traffic_model=best_guess&key=AIzaSyCIvewpnbMTDZbCR3NGc4VwKRYb2BB3Qrs";

    String devuelve = "";
    System.out.println("cadena: "+cadena);
    URL url = null;
    try {
        url = new URL(cadena);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
        connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
        //connection.setHeader("content-type", "application/json");

        int respuesta = connection.getResponseCode();
        StringBuilder result = new StringBuilder();

        if (respuesta == HttpURLConnection.HTTP_OK) {


            InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

            // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
            // que tranformar el BufferedReader a String. Esto lo hago a traves de un
            // StringBuilder.

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);        // Paso toda la entrada al StringBuilder
            }

            //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
            JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
            //Accedemos al vector de resultados
            JSONArray resultJSON = respuestaJSON.getJSONArray("routes");   // results es el nombre del campo en el JSON
            JSONObject jsonObject = resultJSON.getJSONObject(0);
            JSONArray jsonArray = jsonObject.getJSONArray("legs");
            //---------------------------distance-----------
            JSONObject distance = jsonArray.getJSONObject(0).getJSONObject("distance");
            int dis= (int) distance.get("value");
            System.out.println(distance+"==================================");
            System.out.println(dis+"==================================");
            System.out.println("valor: "+jsonArray.toString());
            //----------------------------duracion--------------------------
            JSONObject duracion = jsonArray.getJSONObject(0).getJSONObject("duration");
            int durac= (int) duracion.get("value");
            System.out.println(duracion+"==================================");
            System.out.println(durac+"==================================");
            System.out.println("valor: "+jsonArray.toString());
            //----------------------------duracion In traffic--------------------------
            JSONObject duracionTraffic = jsonArray.getJSONObject(0).getJSONObject("duration_in_traffic ");
            int duracTraf= (int) duracionTraffic.get("value");
            System.out.println(duracionTraffic+"==================================");
            System.out.println(duracTraf+"==================================");
            System.out.println("valor: "+jsonArray.toString());
            double newDurac = durac/60;
            double newDuracTraf = duracTraf/60;
            double newDis = dis/1000;
            distanceAndTime.setDistance(newDis);
            distanceAndTime.setDuration(newDurac);
            distanceAndTime.setDuration_Traffic(newDuracTraf);
            //Vamos obteniendo todos los campos que nos interesen.
            //En este caso obtenemos la primera dirección de los resultados.
                   /* String direccion="SIN DATOS PARA ESA LONGITUD Y LATITUD";
                    if (resultJSON.length()>0){
                        direccion = resultJSON.getJSONObject(0).getString("formatted_address");    // dentro del results pasamos a Objeto la seccion formated_address
                    }
                    devuelve = "Dirección: " + direccion;   // variable de salida que mandaré al onPostExecute para que actualice la UI
*/
        }
;
    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }

    return distanceAndTime;

}

    private Parada getParadaMasCerca(Autobus autobus){
        Ruta ruta = autobus.getRuta();
        ArrayList<Parada> paradas = (ArrayList<Parada>) paradaServices.buscarParadaPorRutaId(ruta.getId());

        double max=1000000000;
        double distanciaActual=0;
        int indexes=0;
        for(int i = 0; i<paradas.size(); i++){
            distanciaActual =Math.sqrt(Math.pow((paradas.get(i).getCoordenada().getLatitude()-autobus.getCoordenada().getLatitude()), 2)
                    +Math.pow((paradas.get(i).getCoordenada().getLongitud()-autobus.getCoordenada().getLongitud()),2));
            if (distanciaActual<max){
                max =distanciaActual;
                indexes = i;
            }
        }

        return paradas.get(indexes);
    }
    private Autobus autobosMasCercanoPorParada(Parada parada){
        Ruta ruta = parada.getRuta();
        ArrayList<Autobus>  autobuses = (ArrayList<Autobus>) autobusServices.buscarAutobusActivosYPorRuta(true, ruta);//Busca una lista de autobuses dado una ruta
        if(autobuses.size() == 0){
            return new Autobus();
        }

        Parada iterador = paradaServices.buscarParada(parada.getParadaAnterior());
        while(!iterador.getId().equals(parada.getId())){
            for(Autobus autobus: autobuses){
                if(iterador.getId().equals(autobus.getUltimaParada().getId()))
                    return autobus;
            }
            iterador = paradaServices.buscarParada(iterador.getParadaAnterior());

        }
        return  new Autobus();
    }

}
