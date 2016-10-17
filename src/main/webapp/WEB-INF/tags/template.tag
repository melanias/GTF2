<%@ tag description="Template Principal da Aplicação" pageEncoding="UTF-8" %>

<%@ attribute name="title" required="true" %>
<%@ attribute name="subTitle" required="true" %>

<%@ attribute name="ngApp" %>
<%@ attribute name="ngController" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html${not empty ngApp ? ' ng-app="'.concat(ngApp).concat('"') : ""}>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, shrink-to-fit=no">
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="Phelipe Melanias">

        <!-- Bootstrap v3.3.7 -->
        <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/jasny-bootstrap.min.css" rel="stylesheet">

        <!-- Font Awesome v4.6.3 -->
        <link href="${pageContext.request.contextPath}/plugins/fa/css/font-awesome.min.css" rel="stylesheet">

        <!-- Ionicons v2.0.1 -->
        <link href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css" rel="stylesheet">

        <!-- ADminLTE v2.3.6 -->
        <link href="${pageContext.request.contextPath}/plugins/lte/css/AdminLTE.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plugins/lte/css/skins/skin-black.min.css" rel="stylesheet">

        <!-- Snarl v0.3.3 -->
        <link href="${pageContext.request.contextPath}/plugins/snarl/snarl.min.css" rel="stylesheet">

        <!-- iCheck v1.0.2 -->
        <link href="${pageContext.request.contextPath}/plugins/icheck/skins/minimal/minimal.css" rel="stylesheet">

        <!-- Select2 v4.0.3 -->
        <link href="${pageContext.request.contextPath}/plugins/select2/select2.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/plugins/select2/select2-bootstrap.min.css" rel="stylesheet">

        <!-- DataTables v1.10.8 -->
        <link href="${pageContext.request.contextPath}/plugins/datatables/css/dataTables.bootstrap.min.css" rel="stylesheet">

        <!-- App Fonts -->
        <link href="${pageContext.request.contextPath}/css/fonts.css" rel="stylesheet">

        <!-- App -->
        <link href="${pageContext.request.contextPath}/css/gtf.css" rel="stylesheet">
        <script>
            var ROOTURL = "${pageContext.request.contextPath}";
        </script>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <title>${not empty subTitle ? (title).concat(" - ").concat(subTitle) : title}</title>
    </head>
    <body class="hold-transition skin-black fixed sidebar-mini"${not empty ngController ? ' ng-controller="'.concat(ngController).concat('"') : ""}>
        <!-- wrapper -->
        <div class="wrapper">
            <header class="main-header">
                <a href="${linkTo[IndexController].index}" class="logo">
                    <span class="logo-mini">${environment.get('app.home')}</span>
                    <span class="logo-lg"><strong>${environment.get('app.home')}</strong></span>
                </a>

                <nav class="navbar navbar-static-top">
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Alterar Menu</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>

                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <c:if test="${funcionarioSession.perfil == 'ADMINISTRADOR'}">
                            <li><a href="${linkTo[FuncionarioController].list}">Funcionário</a></li>
                            </c:if>

                            <li class="dropdown user">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-user"></i>
                                    <span class="hidden-xs">${funcionarioSession.firstAndLastName}</span>
                                    <i class="fa fa-fw fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${linkTo[FuncionarioController].editAccount}"><span class="fa fa-pencil"></span>&nbsp;Alterar conta</a></li>
                                    <li><a href="${linkTo[FuncionarioController].editPassword}"><span class="fa fa-key"></span>&nbsp;Alterar senha</a></li>
                                    <li class="divider"></li>
                                    <li><a href="${linkTo[LoginController].logout}"><span class="fa fa-sign-out"></span>&nbsp;Sair</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- ./main-header -->

            <aside class="main-sidebar">
                <section class="sidebar">
                    <ul class="sidebar-menu">
                        <li class="header text-uppercase">Menu principal</li>

                        <li>
                            <a href="${linkTo[IndexController].index}">
                                <i class="fa fa-home"></i>
                                <span>In&iacute;cio</span>
                            </a>
                        </li>

                        <li>
                            <a href="${linkTo[CorController].list}">
                                <i class="fa fa-paint-brush"></i>
                                <span>Cor</span>
                            </a>
                        </li>

                        <li>
                            <a href="${linkTo[CombustivelController].list}">
                                <i class="fa fa-filter"></i>
                                <span>Combustível</span>
                            </a>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>
            <!-- ./main-sidebar -->

            <!-- content-wrapper -->
            <div class="content-wrapper">
                <div class="content-header">
                    <h1>
                        ${title}
                    </h1>
                </div>

                <!-- content -->
                <div class="content">
                    <jsp:doBody/>
                </div>
                <!-- ./content -->
            </div>
            <!-- ./content-wrapper -->

            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <strong>Versão:</strong>&nbsp;${environment.get('app.version')}
                </div>
                <strong>Copyright&nbsp;&copy;&nbsp;2016&nbsp;-&nbsp;<a href="${linkTo[IndexController].index}">Locadora São Sebastião</a>.</strong>
                Todos os direitos reservados.
            </footer>
        </div>
        <!-- ./wrapper -->


        <!--
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${linkTo[IndexController].index}">GTF</a>
                </div>

                <div class="collapse navbar-collapse navbar-responsive-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastros&nbsp;&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <c:if test="${workerSession.perfil == 'ADMINISTRADOR'}">
                                <li><a href="${linkTo[WorkerController].list}">Usuário</a></li>
                                <li class="divider"></li>
                                </c:if>
                                <li><a href="${linkTo[ClienteController].list}">Cliente</a></li>
                                <li><a href="${linkTo[ContratoController].list}">Contrato</a></li>
                                <li class="divider"></li>
                                <li><a href="${linkTo[CorController].list}">Cor</a></li>
                                <li><a href="${linkTo[MarcaController].list}">Marca</a></li>
                                <li><a href="${linkTo[PadraoController].list}">Padrão</a></li>
                                <li><a href="${linkTo[ModeloController].list}">Modelo</a></li>
                                <li><a href="${linkTo[CombustivelController].list}">Combustível</a></li>
                                <li><a href="${linkTo[VeiculoController].list}">Veículo</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Relatórios&nbsp;&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="">Marcas</a></li>
                                <li><a href="">Final de Placa</a></li>
                                <li><a href="">Ano de Fabricação / Modelo</a></li>
                            </ul>
                        </li>
                    </ul>

                    <c:if test="${not empty workerSession.nome}">
                    <ul class="nav navbar-nav navbar-right">
                        <%--
                        <c:choose>
                            <c:when test="${not empty employeeSession.arquivo}">
                                <li class="signed-in-with-image"><img src="${employeeSession.arquivo.encodeBase64}" alt="${employeeSession.arquivo.fileName}"></li>
                            </c:when>
                            <c:otherwise>
                                <li class="signed-in-without-image"><span class="glyphicon glyphicon-user"></span></li>
                            </c:otherwise>
                        </c:choose>
                        --%>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;${workerSession.firstAndLastName}&nbsp;&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="${linkTo[WorkerController].editAccount}"><span class="glyphicon glyphicon-edit"></span>&nbsp;Editar conta</a></li>
                                <li><a href="${linkTo[WorkerController].editPasswd}"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Alterar senha</a></li>
                                <li class="divider"></li>
                                <li><a href="${linkTo[LoginController].logout}"><span class="glyphicon glyphicon-off"></span>&nbsp;Sair</a></li>
                            </ul>
                        </li>
                    </ul>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>

        <!-- Bootstrap v3.3.7 -->
        <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/jasny-bootstrap.min.js"></script>

        <!-- ADminLTE v2.3.6 -->
        <script src="${pageContext.request.contextPath}/plugins/lte/js/app.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/lte/js/jquery.slimscroll.min.js"></script>

        <!-- jQuery Mask Plugin v1.13.4 -->
        <script src="${pageContext.request.contextPath}/js/jquery.mask.min.js"></script>

        <!-- Snarl v0.3.3 -->
        <script src="${pageContext.request.contextPath}/plugins/snarl/snarl.min.js"></script>

        <!-- iCheck v1.0.2 -->
        <script src="${pageContext.request.contextPath}/plugins/icheck/icheck.min.js"></script>

        <!-- Select2 v4.0.3 -->
        <script src="${pageContext.request.contextPath}/plugins/select2/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/select2/i18n/pt-BR.min.js"></script>

        <!-- DataTables v1.10.8 -->
        <script src="${pageContext.request.contextPath}/plugins/datatables/js/dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/datatables/js/dataTables.bootstrap.min.js"></script>

        <!-- App -->
        <script src="${pageContext.request.contextPath}/js/gtf.js"></script>

        <c:if test="${not empty ngApp && not empty ngController}">
        <!-- AngularJS -->
        <script src="${pageContext.request.contextPath}/js/angular.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app/module/${ngApp}.module.js"></script>
        <script src="${pageContext.request.contextPath}/js/app/service/${ngApp}.service.js"></script>
        <script src="${pageContext.request.contextPath}/js/app/controller/${ngApp}.controller.js"></script>
        </c:if>
    </body>
</html>