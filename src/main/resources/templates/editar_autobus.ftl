<!DOCTYPE html>
<html lang="en"  xmlns:th="http://www.thymeleaf.org">
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
                    Autobus
                </h1>
                <ol class="breadcrumb">
                    <li>
                        <i class="fa fa-dashboard"></i> <a href="/">Home</a>
                    </li>

                    <li class="active">
                        <i class="fa fa-edit"></i> Editar Autobus
                    </li>
                </ol>
            </div>
        </div>

        <form role="form" action="#" name="myForm" th:action="@{/autobus/crear}" th:object="${autobus}" method="post">
        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <label for="modelo">Modelo</label>
                        <input type="text" class="form-control" placeholder="Modelo del autobus" min="2" max="35" name="modelo" id="modelo" <#if autobus.modelo??>value="${autobus.modelo}"</#if>>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <label for="cantidadDeAsientos">Cantidad de Asientos</label>
                        <input type="number" class="form-control" placeholder="Cantidad de asientos" name="cantidadDeAsientos" min="0" max="100" <#if autobus.cantidadDeAsientos??>value="${autobus.cantidadDeAsientos}"</#if> id="cantidadDeAsientos" required>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <label for="peso">Peso</label>
                        <div class="input-group">
                            <input type="number" id="peso" step="0.01" name="peso" min="0" placeholder="Entre el peso" max="35000"  <#if autobus.peso??>value="${autobus.peso}"</#if> class="form-control">
                            <span class="input-group-addon">kg</span>
                        </div>

                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <label for="ruta">Ruta</label>
                        <select class="form-control" name="ruta" id="ruta" required>
                            <option selected disabled>Elija una ruta</option>
                        <#if rutas??>
                            <#list rutas as ruta>
                                <option value="${ruta.id}">${ruta.nombreCorredor}/ <#if ruta.esDireccionSubida>Subida --> <#else> Bajada <-- </#if></option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <label for="anoFabricacion">A&ntilde;o Fabricaci&oacute;n</label>
                        <select class="form-control" name="anoFabricacion" id="anoFabricacion">
                            <option selected disabled>Elija el a&ntilde;o de fabricaci&oacute;n</option>
                        </select>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <label for="conductor">Conductor</label>
                        <input type="text" class="form-control" pattern="[a-zA-Z]+[ ][a-zA-Z]+" placeholder="Nombre del conductor"  <#if autobus.conductor??>value="${autobus.conductor}"</#if> name="conductor" min="2" max="100" id="conductor" required>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <div class="form-group">
                        <label for="precio">Precio</label>
                        <input type="number" min="10" placeholder="Entre el precio" class="form-control" name="precio"  <#if autobus.precio??>value="${autobus.precio}"</#if> id="precio" required>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <label for="tieneAire">Tiene aire acondicionado</label>
                        <br>
                        <label class="radio-inline">
                            <input type="radio" name="tieneAireAcondicionado" id="noTieneAireAcondicionado" value=true checked>Si
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="tieneAireAcondicionado" id="noTieneAireAcondicionado" value=false >No
                        </label>
                    </div>
                </div>
            </div>

            <div class="row">


                <h4 style="padding-left: 15px">Raspberry Pi</h4>

                <div class="col-lg-6">
                    <hr>
                    <div class="form-group">
                        <label for="numeroDeSerie">Numero de Serie</label>
                        <input type="text" pattern="^[A-Za-z0-9-]+$" class="form-control" name="numeroDeSerie" min="0" max="100"  <#if autobus.raspberryPiNumeroSerial??>value="${autobus.raspberryPiNumeroSerial}"</#if> id="precio" id="numeroDeSerie" required>
                    </div>
                </div>

                <div class="col-lg-6" style="padding-top: 65px">

                    <div class="col-lg-6">
                        <div class="form-group">
                            <button type="reset" class="btn btn-success form-control">Limpiar</button>
                        </div>

                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <button type="submit" class="btn btn-success form-control">Guardar</button>
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

<script type="text/javascript">
    // get the OPTION we want selected
    var $option = $('#ruta').children('option[value="'+ ${autobus.ruta.id} +'"]');
    // and now set the option we want selected
    $option.attr('selected', true);​​

    $(function() {
        $("#anoFabricacion").val(${autobus.anoFabricacion});
    });

</script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>

</html>