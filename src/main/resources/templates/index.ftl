<!DOCTYPE html>
<html lang="en">
<#include "header.ftl">
<body>
<div id="wrapper">
<#include "nav.ftl">
    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Autobus Actividades
                    </h1>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-dashboard"></i> Dashboard
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div id="map" style="width: 100%; height: 400px;"></div>

                    </div>
                </div>
            </div>


        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>


<script>

    function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 12,
            center: {
                lat: 19.48863754,
                lng: -70.71721584
            },
            mapTypeId: 'terrain'
        });


        $.getJSON("/api/rutas/buscar", function (data) {
            var color= ["#ffff00",
                "#00ff00",
                "#0000ff",
                "#00ffff",
                "#ff0000",
                "#ff00ff",
                "#ff700f"];
            var coor = [];
            $.each(data, function (key, value) {
                setParadas(map, value["id"]);
                $.each(value["coordenadas"], function (k, v) {

                    var obj = {lat: v.latitude, lng: v.longitud};
                    coor.push(obj)
                });
                var x = Math.floor((Math.random() * 7));
                var flightPath = new google.maps.Polyline({
                    path: coor,
                    geodesic: true,
                    strokeColor: color[x],
                    strokeOpacity: 1.0,
                    strokeWeight: 5
                });
                coor = [];
                flightPath.setMap(map);
            });
        });
        setAutobus(map);
        setInterval(function () {
            console.log("here");
            setAutobus(map);
        }, 30000); // 30 seconds
    }

    function setParadas(map, id_ruta) {

        var image = {
            url: '/images/paradas.png',
            // This marker is 20 pixels wide by 32 pixels high.
            size: new google.maps.Size(20, 20),
            // The origin for this image is (0, 0).
            origin: new google.maps.Point(0, 0),

            anchor: new google.maps.Point(0, 20)
        };

        var shape = {
            coords: [1, 1, 1, 20, 18, 20, 18, 1],
            type: 'poly'
        };

        $.getJSON("/api/paradas/ruta/" + id_ruta, function (data) {

            $.each(data, function (key, parada) {

                var marker = new google.maps.Marker({
                    position: {
                        lat: parada.coordenada.latitude,
                        lng: parada.coordenada.longitud
                    },
                    map: map,
                    icon: image,
                    shape: shape,
                    title: parada["nombre"],
                    zIndex: 1
                });
            });
        });
    }
    var markers = [];
    function setAutobus(map) {

        var active = {
            url: '/images/busActive.png',
            // This marker is 20 pixels wide by 32 pixels high.
            size: new google.maps.Size(80, 29),
            // The origin for this image is (0, 0).
            origin: new google.maps.Point(0, 0),
            // The anchor for this image is the base of the flagpole at (0, 32).
            anchor: new google.maps.Point(0, 29)
        };
        var inactive = {
            url: '/images/busInactive.png',
            // This marker is 20 pixels wide by 32 pixels high.
            size: new google.maps.Size(80, 26),
            // The origin for this image is (0, 0).
            origin: new google.maps.Point(0, 0),
            // The anchor for this image is the base of the flagpole at (0, 32).
            anchor: new google.maps.Point(0, 26)
        };

        var shape = {
            coords: [1, 1, 1, 20, 18, 20, 18, 1],
            type: 'poly'
        };

        $.getJSON("/api/autobuses/buscar", function (data) {
            var marker = [];
            setMapOnAll(null);
            markers = [];
            $.each(data, function (key, autobus) {
                var conductor=autobus["conductor"];
                var matricula = autobus["matricula"];
                var porcentajePas=(autobus["cantidadDePasajerosActual"]/autobus["cantidadDeAsientos"])*100;
                var tieneAire=autobus["tieneAireAcondicionado"];
                var precio=autobus["precio"];
                var activo=autobus["activo"];
                var estado=function (p) {
                    if(p<60){
                        return "success";
                    }else if (p >=60 && p<=100){
                        return "warning"
                    }else{
                        return "danger"
                    }
                };

                if (activo) {
                    marker = new google.maps.Marker({
                        position: {
                            lat: autobus.coordenada.latitude,
                            lng: autobus.coordenada.longitud
                        },
                        map: map,
                        icon: active,
                        shape: shape,
                        title: autobus["modelo"] + autobus["id"],
                        zIndex: 1
                    });
                    markers.push(marker);
                } else {
                    marker = new google.maps.Marker({
                        position: {
                            lat: autobus.coordenada.latitude,
                            lng: autobus.coordenada.longitud
                        },
                        map: map,
                        icon: inactive,
                        shape: shape,
                        title: autobus["nombre"],
                        zIndex: 1
                    });
                    markers.push(marker);
                }
                //'<span class="sr-only">40% Complete (success)</span> </div> '
                var contentString =
                        '<div style="border:1px solid gray; padding:10px;">'+
                            '<h1>Autobus: '+matricula+'</h1>'+
                            '<div class="progress">'+
                                '<div class="progress-bar progress-bar-'+estado(porcentajePas)+' progress-bar-striped"' +
                                ' role="progressbar" aria-valuenow="'+porcentajePas+'" aria-valuemin="0" aria-valuemax="100" style="width:'+porcentajePas+'%;">' +
                                '</div>' +
                            '</div>'+
                            '<ul class="list-group">' +
                                '<li class="list-group-item">Conductor : <span class="badge">'+conductor+'</span></li>' +
                                '<li class="list-group-item">Precio :<span class="badge">'+precio+'</span></li> ' +
                                '<li class="list-group-item">Tiene Aire :<span class="badge">'+tieneAire+'</span></li> ' +
                                '<li class="list-group-item">Activo :<span class="badge">'+activo+'</span></li> ' +
                            '</ul> '+
                        '</div>';
                var infowindow = new google.maps.InfoWindow({
                    content: contentString
                });

                marker.addListener('click', function () {
                    infowindow.open(map, marker);
                });
            });
        });

    }
    function setMapOnAll(map) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(map);
        }
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyABtO1OiHaJnWDo29kaUUOm06HBU6GjAUA&callback=initMap">
</script>

</body>

</html>
