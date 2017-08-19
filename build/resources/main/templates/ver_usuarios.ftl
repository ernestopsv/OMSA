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
                            Usuarios
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i> <a href="/">Home</a>
                            </li>

                            <li class="active">
                                <i class="fa fa-table"></i> Tabla de Usuarios
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->


                <div class="row">
                    <div class="col-lg-12">
                        <h2>Tabla de Usuarios</h2>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Nombre</th>
                                        <th>Nombre de Usuario</th>
                                        <th>Administrador</th>
                                        <th>&nbsp</th>
                                        <th>&nbsp</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <#if usuarios??>
                                    <#list usuarios as usuario>

                                    <tr>
                                        <th scope="row">${usuario?index+1}</th>
                                        <td><#if usuario.name??>${usuario.name}</#if></td>
                                        <td><#if usuario.username??>${usuario.username}</#if></td>
                                        <td><#if usuario.admin> Si<#else>No</#if></td>
                                        <td>
                                            <a href="/zonaAdmin/eliminar/usuario/${usuario.id}">
                                                <p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Editar" data-toggle="modal" data-target="#edit"><span class="glyphicon glyphicon-pencil"></span></button></p>
                                            </a>
                                        </td>
                                        <td>
                                            <a href="/zonaAdmin/editar/${usuario.id}">
                                                <p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Eliminar" data-toggle="modal" data-target="#delete"><span class="glyphicon glyphicon-trash"></span></button></p>
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

    <!-- Bootstrap Core JavaScript -->
    <script src="/js/bootstrap.min.js"></script>

</body>

</html>
