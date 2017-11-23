<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<#include "header.ftl">
<style>
    .ganancia {
        color: #CCCDCB;
    }
    .actual{
        color:  #009933;
        font-weight: bold;
        font-size: 25px
    }
    .gananciaYmovimiento {
        background-color: #F3FBF1;
        padding: 2px 2px;
        text-align: center;
        font-family: "Segoe UI";

    }
</style>
<body>
<div id="wrapper">
<#include "nav.ftl">

    <div id="page-wrapper">

        <div class="container-fluid">

            <div class="row">
                <#--<div class="col-lg-12">-->
                    <div class="col-lg-3">
                        <div class="panel-body">
                            <div class="gananciaYmovimiento">
                                <h3>Total pasajeros</h3>
                                <div class="actual" id="totalAyer"></div>
                                <span class="ganancia">Ayer</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="panel-body">
                        <div class="gananciaYmovimiento">
                            <h3>Ganancia</h3>
                            <div class="actual" id="gananciaAyer"></div>
                            <span class="ganancia">Ayer</span>
                        </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="panel-body">
                        <div class="gananciaYmovimiento">
                            <h3>Total pasejeros</h3>
                            <div class="actual" id="totalSemana"></div>
                            <span class="ganancia">Utlima semana</span>
                        </div>
                        </div>

                    </div>
                    <div class="col-lg-3">
                        <div class="panel-body">
                        <div class="gananciaYmovimiento">
                            <h3>Ganancia</h3>
                            <div class="actual" id="gananciaSemana"></div>
                            <span class="ganancia">Utlima semana</span>
                        </div>
                        </div>

                    </div>
                <#--</div>-->
            </div>
            <div class="row">
                <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Flujo Mensual de Pasajeros por Ruta</h3>
                        </div>
                        <div class="panel-body">
                            <div id="flujoPorRutaPorMes"><ul id ="list" class="list-group">

                            </ul></div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-8">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Flujo de pasajeros mensual
                                por ruta</h3>
                        </div>
                        <div class="panel-body">
                            <div id="flujoPorRuta "></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->


            <div class="row">
                <div class="col-lg-4">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i>Ganancia Ultimo Mes</h3>
                        </div>
                        <div class="panel-body">
                            <canvas id="ganaciaUltimoMes" width="100" height="100"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Movimiento mensual</h3>
                        </div>
                        <div class="panel-body">
                            <canvas id="movimientoMensual" width="100" height="100"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Ganancia Mensual</h3>
                        </div>
                        <div class="panel-body">
                            <canvas id="gananciaMensual" width="100" height="100"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<script src="/js/Chart.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctxGananciaMensual = document.getElementById("gananciaMensual");
    var ctxMovimientoMensual = document.getElementById("movimientoMensual");
    var ctxGanaciaUltimoMes = document.getElementById("ganaciaUltimoMes");
    var ctxFlujoPorRuta = document.getElementById("flujoPorRuta");
    var ctxCircle = document.getElementById("ChartCircle");
    var ctxCircle = document.getElementById("ChartCircle");

// -----------------------------------------Hecho  Ganancia de Ayer---------------------------------------------------------
    $.get("/api/estadistica/gananciaAyer", function (data, status) {
        data.forEach(function (doc) {
                document.getElementById("totalAyer").innerHTML = doc[0];
            if(doc[1]===null)
                document.getElementById("gananciaAyer").innerHTML = '$'+0+" RD"
            else
                document.getElementById("gananciaAyer").innerHTML = '$'+doc[1]+" RD"
        });
    });
//-----------------------------------------Hecho Ganancia Utlima Semana---------------------------------------------------------
    $.get("/api/estadistica/gananciaUltimaSemana", function (data, status) {
        data.forEach(function (doc) {
            document.getElementById("totalSemana").innerHTML = doc[0]

            if(doc[1]===null){
                document.getElementById("gananciaSemana").innerHTML = '$'+0+" RD"
            }else
                document.getElementById("gananciaSemana").innerHTML = '$'+doc[1]+" RD"
        });
    });

//-----------------------------------------Hecho Movimiento Mensual---------------------------------------------------------
        $.get("/api/estadistica/movimientoMensual", function (data, status) {
            var x = [];
            var y =[];
            data.forEach(function (doc) {
                x.push(doc[0]);
                y.push(doc[1]);
            });

            new Chart(ctxMovimientoMensual,{
                type: 'pie',
                data: {
                    datasets: [{
                        data: y,
                        label:'Movimiento de paseajeros mensual',
                        backgroundColor: [
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(54, 162, 235, 0.2)',
                                        'rgba(255, 206, 86, 0.2)',
                                        'rgba(75, 192, 192, 0.2)',
                                        'rgba(153, 102, 255, 0.2)',
                                        'rgba(255, 159, 64, 0.2)'
                                    ],
                                    borderColor: [
                                        'rgba(255,99,132,1)',
                                        'rgba(54, 162, 235, 1)',
                                        'rgba(255, 206, 86, 1)',
                                        'rgba(75, 192, 192, 1)',
                                        'rgba(153, 102, 255, 1)',
                                        'rgba(255, 159, 64, 1)'
                                    ]
                    }],

                    labels: x
                },
                options:{
                    segmentShowStroke: false,
                    animateScale: true
                }
            });
        });

//-----------------------------------------Hecho Ganancia Mensual---------------------------------------------------------
        $.get("/api/estadistica/GananciaMensual", function (data, status) {
            var x = [];
            var y =[];
            data.forEach(function (doc) {
                x.push(doc[0]);
                y.push(doc[1]);
            });
            new Chart(ctxGananciaMensual, {
                type: 'line',
                data: {
                    datasets: [{
                        label: 'Ganancia Mensual',
                        data: y
                    }],
                    labels: x
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero:true
                            }
                        }]
                    }, animateScale: true
                }


            })

        });
// -----------------------------------------Hecho Ganancia Ultimo Mes---------------------------------------------------------
        $.get("/api/estadistica/GananciaUltimoMes", function (data, status) {
            var x = [];
            var y =[];
            data.forEach(function (doc) {
               x.push(doc[0]);
                y.push(doc[1]);
            });

            new Chart(ctxGanaciaUltimoMes, {
                type: 'bar',
                data: {
                    labels: x,
                    datasets: [{
                        label: 'Ganancia Ultimo Mes',
                        data: y,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255,99,132,1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero:true
                            }
                        }]
                    }, animateScale: true
                }
            });

        });


//        $.get("/api/estadistica/movimientoAnual", function (data, status) {
//            console.log("Movimiento Anual "+ data);
//        });

        $.get("/api/estadistica/movimientoPorRuta", function (data, status) {
            console.log("Movimiento Por Ruta "+ data);
        });
//-----------------------------------------------Hecho Flujo mensual Autobus Por Ruta--------------------------------------------------------------
        $.get("/api/estadistica/movimientoPorRutaAnual", function (data, status) {
            data.forEach(function (doc) {
                $("#list").append(
                    $('<li>').css("background-color", "#e6faff" ).addClass('list-group-item').append(doc[1]).append(
                            $('<span>').addClass('badge').append(doc[0]).css("background-color", " #ff9999" )

                )
                )
            });

         });


</script>


</body>

</html>
