<!DOCTYPE html>
<html lang="en">
<#include "header.ftl">

<body>
<div id="wrapper">
<#include "nav.ftl">
    <div id="page-wrapper">

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Ruta
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="/">Home</a>
                        </li>

                        <li class="active">
                            <i class="fa fa-edit"></i> Editar Ruta
                        </li>
                    </ol>
                </div>
            </div>

            <form role="form">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="nombreCorredor">Nombre Corredor</label>
                            <input type="text" class="form-control" name="nombreCorredor" id="nombreCorredor">
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="ruta">Ruta</label>
                            <select class="form-control" name="ruta" id="ruta">
                                <option selected disabled>Elija una opcion</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="distanciaTotal">Distancia Total</label>
                            <div class="input-group">
                                <input type="number" id="distanciaTotal" step="0.01" min="0.0" name="distanciaTotal"
                                       class="form-control">
                                <span class="input-group-addon">km</span>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="ciudad">Ciudad</label>
                            <input type="text" class="form-control" name="ciudad" min="2" max="100" id="ciudad">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="anoFabricacion">Ano Fabricacion</label>
                            <select class="form-control" name="anoFabricacion" id="anoFabricacion">
                                <option selected disabled>Elija una opcion</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="conductor">Conductor</label>
                            <input type="text" class="form-control" name="conductor" min="2" max="100" id="conductor">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="precio">Precio</label>
                            <input type="number" min="10" class="form-control" name="precio" id="precio">
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="tieneAire">Tiene aire acondicionado</label>
                            <br>
                            <label class="radio-inline">
                                <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="1"
                                       checked>SI
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline2" value="0">NO
                            </label>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <h3 style="padding-left: 15px">Raspberry Pi</h3>

                    <div class="col-lg-6">

                        <div class="form-group">
                            <label for="numeroDeSerie">Numero de Serie</label>
                            <input type="text" pattern="[a-zA-Z][a-zA-Z0-9\s]*" class="form-control"
                                   name="numeroDeSerie"
                                   min="0" max="100" id="numeroDeSerie">
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