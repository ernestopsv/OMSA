<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
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
                        Crear una nueva coordenada
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="/">Home</a>
                        </li>

                        <li class="active">
                            <i class="fa fa-edit"></i> Crear Coordenada
                        </li>
                    </ol>
                </div>
            </div>
            <a href="/ruta/listar/coordenadas/${ruta.id}">Ver Ruta</a>

            <form role="form" action="#" th:action="@{/ruta/coordenada/editar}" th:object="${coordenada}"  method="POST">

                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="latitude">Latitud</label>
                            <input type="number" id="latitude" step="0.000001" name="latitude" value="${coordenada.latitude}" class="form-control" required>
                        </div>

                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="longitud">Longitud</label>
                            <input type="number" class="form-control" value="${coordenada.longitud}" step="0.000001" name="longitud" id="longitud" required>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="ruta" value="${ruta.id}">
                <input type="hidden" name="coordenada" value="${coordenada.id}">
        </div>
        <div class="row">
            <hr>
            <div class="col-lg-offset-6 col-lg-6">
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
        <#if message??>${message}</#if>
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
