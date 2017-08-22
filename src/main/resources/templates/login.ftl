<!DOCTYPE html>
<html lang="en"  xmlns:th="http://www.thymeleaf.org">

<#--xmlns="http://www.w3.org/1999/xhtml"-->
<#--xmlns:th="http://www.thymeleaf.org"-->
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link  type="text/css" href="/css/bootstrap.min.css"  rel="stylesheet">

    <#--<link href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css"-->
          <#--th:href="@{/webjars/bootstrap/3.3.4/css/bootstrap.min.css}"-->
          <#--rel="stylesheet" media="screen" /> &lt;#&ndash;new one&ndash;&gt;-->

    <!-- Custom CSS -->
    <link href="/css/sb-admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="/js/angular.js"></script>

</head>

<body ng-app="OmsaTracker">

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.ftl">OMSA</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu message-dropdown">
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>John Smith</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>John Smith</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>John Smith</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-footer">
                            <a href="#">Read All New Messages</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu alert-dropdown">
                        <li>
                            <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">View All</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- /.navbar-collapse -->
        </nav>

        <div class="row" style="margin-top: 140px" ng-controller="usuarioController">

            <div class="col-lg-offset-2 col-lg-7">
                <div class="panel panel-green">
                    <div class="panel-heading" style="text-align: center">
                        <h2 class="panel-title">Login</h2>
                    </div>
                    <div class="panel-body">

                        <form role="form" name="myForm">
                            <div class="row">
                                <div class="col-lg-offset-3 col-lg-6">
                                    <div class="form-group">
                                        <label for="username">Usuario</label>
                                        <input type="text" class="form-control" ng-minlength="4" ng-maxlength=30 ng-model="username" name="username" placeholder="Entre su nombre de usuario" id="username" required>
                                        <span style="color:red" ng-show="myForm.username.$dirty && myForm.username.$invalid">
                                              <small ng-show="myForm.username.$error.required">Nombre de usuario requerido.</small>
                                        </span>
                                        <small style="color:red" ng-show="myForm.username.$dirty && myForm.username.$error.minlength">Nombre de usuario corto.</small>
                                        <small style="color:red" ng-show="myForm.username.$dirty && myForm.username.$error.maxlength">Nombre de usuario demasiado largo.</small>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-offset-3 col-lg-6">
                                    <div class="form-group">
                                        <label for="cantidadDeAsientos">Contrase&ntilde;a</label>
                                        <input type="password" class="form-control" ng-model="password" name="password" placeholder="Entre su contrasena" id="password" ng-minlength="6" ng-maxlength=30 required>
                                        <span style="color:red" ng-show="myForm.password.$dirty && myForm.password.$invalid">
                                              <small ng-show="myForm.password.$error.required">Contrase&ntilde;a requerida.</small>
                                        </span>
                                        <small style="color:red" ng-show="myForm.password.$dirty && mysForm.password.$error.minlength">contrase&ntilde;a corto</small>
                                        <small style="color:red" ng-show="myForm.password.$dirty && mysForm.password.$error.maxlength">contrase&ntilde;a demasiado largo</small>
                                    </div>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-offset-3 col-lg-3">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-success form-control" ng-disabled="myForm.username.$dirty && myForm.username.$invalid || myForm.password.$dirty && myForm.password.$invalid">Guardar</button>
                                    </div>

                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- /.container-fluid -->



    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="/js/jquery.js">


    </script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/js/bootstrap.min.js"></script>

    <script src="/js/app.js"></script>
    <script src=/js/controllers/usarioController.js"></script>

</body>

</html>
