<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" ng-app="omsaTracker">
<#include "header.ftl">

<body>
<div id="wrapper" ng-controller="usuarioController">
<#include "nav.ftl">
        <div id="page-wrapper">

                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <div class="row">
                                <div class="col-lg-12">
                                    <h1 class="page-header">
                                        Usuarios
                                    </h1>
                                    <ol class="breadcrumb">
                                        <li>
                                            <i class="fa fa-dashboard"></i> <a href="/">Home</a>
                                        </li>

                                        <li class="active">
                                            <i class="fa fa-edit"></i> Crear Usuarios
                                        </li>
                                    </ol>
                                </div>
                            </div>

                            <form role="form" name="myForm" method="post" action="#" th:action="@{/zonaAdmin/registrar}" th:object="${usuario}">
                                <h2>Agregar Nuevo Usuario</h2>
                                <hr>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label for="name">Nombre</label>
                                            <input type="text" class="form-control" min=2 max=100 name="name" id="name" placeholder="Entre su nombre" required>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label for="username">Nombre de Usuario</label>
                                            <input type="text" class="form-control" min="2" max="30" name="username" id="username" placeholder="Entre su nombre de usuario">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label for="password">Contrase&ntilde;a</label>
                                            <input type="password" ng-model="password" id="password" name="password" max="30" min="6" class="form-control" placeholder="Entre su contrase&ntilde;a" required>
                                        </div>

                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label for="confirmPassword">Confirmar Contrase&ntilde;a</label>
                                            <input type="password" ng-model="confirmPassword" class="form-control" min="2" max="100" id="confirmPassword" placeholder="Confirme su contrase&ntilde;a" required>
                                        </div>
                                            <small class="error" style="color: red" ng-show="password !== confirmPassword">Contrase&ntilde;a incompatibles</small>


                                    </div>
                                </div>

                    <div class="row">
                        <hr>
                        <#if error??>
                            <small class="error" style="color: red;"> ${error}</small>
                        </#if>
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
    <script src="/js/app.js"></script>
    <script src="/js/controllers/usarioController.js"></script>

</body>

</html>
