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
                            Crear una nueva parada
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> <a href="/">Home</a>
                            </li>

                            <li class="active">
                                <i class="fa fa-edit"></i> Crear Parada
                            </li>
                        </ol>
                    </div>
                </div>

                <form role="form">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="nombre">Nombre</label>
                                <input type="text" class="form-control" min="2" max="100" name="nombre" id="nombre" required>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="ruta">Ruta</label>
                                <select class="form-control" name="ruta" id="ruta" required>
                                    <option selected disabled>Elija una opcion</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="latitud">Latitud</label>
                                <input type="number" id="latitud" step="0.000001" name="latitud" class="form-control">
                            </div>

                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="longitud">Longitud</label>
                                <input type="number" class="form-control" step="0.000001" name="longitud" id="longitud">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="paradaAnterior">Parada anterior</label>
                                <select class="form-control" name="paradaAnterior" id="paradaAnterior">
                                     <option selected disabled>Elija una opcion</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="paradaSiguiente">Parada Siguiente</label>
                                <select class="form-control" name="paradaSiguiente" id="paradaSiguiente">
                                     <option selected disabled>Elija una opcion</option>
                                </select>
                            </div>
                        </div>
                    </div>


            </div>
            <div class="row">
                <hr>
                <div class="col-lg-offset-6 col-lg-6">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <button type="submit" class="btn btn-success form-control">Guardar</button>
                        </div>

                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <button type="reset" class="btn btn-success form-control">Limpiar</button>
                        </div>

                    </div>

                </div>
            </div>

            </form>

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="/js/jquery.js">


    </script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/js/bootstrap.min.js"></script>

</body>

</html>
