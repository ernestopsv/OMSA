<!DOCTYPE html>
<html lang="en"  >
<#include "header.ftl">

<body ng-app="omsaTracker"  ng-controller="paradaController">
<div id="wrapper">
<#include "nav.ftl">

    <div id="page-wrapper" ng-init="getParadas(${id_ruta})">

            <div class="container-fluid" >

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Lista de Parada
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> <a href="/">Home</a>
                            </li>
                             <li>
                                <i class="fa fa-table"></i> <a href="/">Tabla de Corredores</a>
                            </li>                            
                            <li class="active">
                                <i class="fa fa-table"></i> Tabla de Paradas
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
                <#if id_ruta??><a href="/parada/ver/${id_ruta}">Ver en mapa</a></#if>

                <form action="">
                    <div class="row">
                        <div class=" col-lg-offset-8 col-lg-4">

                            <div class="form-group">
                                <label for="ruta">Ruta</label>
                                <select class="form-control" name="ruta" id="ruta">
                                    <option selected disabled>Elija una opcion</option>
                                </select>
                            </div>

                        </div>
                    </div>

                </form>
                <div class="row">
                    <div class="col-lg-12">
                        <h2>Paradas de la ruta {Tal} {Subida/Bajada}</h2>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Nombre</th>
                                        <th>Ruta</th>
                                        <th>Longitude</th>
                                        <th>Latitude</th>
                                        <th>Parada Anterior</th>
                                        <th>Parada Siguiente</th>
                                        <th>&nbsp</th>

                                    </tr>
                                </thead>
                                <tbody>
                                <#if paradas??>
                                <#list paradas as parada>
                                    <tr>
                                        <th scope="row">${parada?index+1}</th>
                                        <td><#if parada.nombre??>${parada.nombre}</#if></td>
                                        <td><#if parada.ruta??> ${parada.ruta.nombreCorredor}</#if></td>
                                        <td><#if parada.coordenada??>${parada.coordenada.longitud}</#if></td>
                                        <td><#if parada.coordenada??>${parada.coordenada.latitude}</#if></td>
                                        <#if parada.paradaAnterior??>
                                            <td ng-bind="(paradas | filter : {'paradaAnterior':${parada.paradaAnterior}})[0].nombre"></td>
                                        <#else>
                                                <td></td>
                                        </#if>

                                        <#if parada.paradaSiguiente??>
                                            <td ng-bind="(paradas | filter : {'paradaSiguiente':${parada.paradaSiguiente}})[0].nombre"></td>
                                        <#else>
                                            <td></td>
                                        </#if>
                                        <td>
                                            <a href="/parada/editar/${parada.id}">
                                                <p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Editar" data-toggle="modal" data-target="#edit"><span class="glyphicon glyphicon-pencil"></span></button></p>
                                            </a>
                                        </td>

                                    </tr>
                                </#list>
                                </#if>
                                </tbody>
                            </table>
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
    <script src="/js/jquery.js">
    </script>

    <#--angularjs app -->
     <script type="text/javascript" src="/js/app.js"></script>
    <script type="text/javascript" src="/js/controllers/paradaController.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="/js/bootstrap.min.js"></script>

</body>

</html>
