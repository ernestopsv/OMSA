<!DOCTYPE html>
<html lang="en">
<title>Editar Usuario</title>
<#include "header.ftl">
<body>
<div id="wrapper">
<#include "nav.ftl">

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h3 class="page-header">
                        Usuarios
                    </h3>
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

            <form role="form">
                <h2>Agregar Nuevo Usuario</h2>
                <hr>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="name">Nombre</label>
                            <input type="text" class="form-control" min=2 max=100 name="name" id="name"
                                   placeholder="Entre su nombre">
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="username">Nombre de Usuario</label>
                            <input type="text" class="form-control" min=2 max=3 0 name="username" id="username"
                                   placeholder="Entre su nombre de usuario">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="password">Contrase&ntilde;a</label>
                            <input type="password" id="password" name="password" max=30 min=6 class="form-control"
                                   placeholder="Entre su contrase&ntilde;a">
                        </div>

                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="confirmPassword">Confirmar Contrase&ntilde;a</label>
                            <input type="password" class="form-control" min="2" max="100" id="confirmPassword"
                                   placeholder="Confirme su contrase&ntilde;a">
                        </div>
                    </div>
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
