<!DOCTYPE html>
<html ng-app="GTF">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, shrink-to-fit=no">
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="Phelipe Melanias">

        <!-- Google Font API -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">

        <!-- Google Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <!-- Angular Material v1.1.1 -->
        <link href="${pageContext.request.contextPath}/js/lib/angular-material.min.css" rel="stylesheet">

        <!-- Font Awesome v4.7.0 -->
        <link href="${pageContext.request.contextPath}/plugins/fa/css/font-awesome.min.css" rel="stylesheet">

        <!-- GTF -->
        <link href="${pageContext.request.contextPath}/css/gtf.css" rel="stylesheet">

        <title>${title.concat(" - ").concat(subTitle)}</title>
    </head>
    <body layout="row" ng-cloak>
        <!-- sidenav -->
        <md-sidenav flex="15" md-is-locked-open="true" class="md-whiteframe-1dp">
            <md-toolbar class="md-hue-2">
                <md-button layout="column" layout-align="center center" class="md-toolbar-tools" ui-sref="index">
                    <h1>${environment.get('app.home')}</h1>
                </md-button>
            </md-toolbar>

            <md-content flex="grow">
                <md-list flex>
                    <md-subheader class="md-no-sticky">Menu Principal<i class="fa fa-arrow-down"></i></md-subheader>
                    <div class="layout-spacer-10"></div>

                    <!-- P�gina inicial -->
                    <md-list-item ui-sref="index" ui-sref-active="active">
                        <i class="fa fa-home icon-22" aria-hidden="true"></i>
                        In&iacute;cio
                    </md-list-item>

                    <div class="layout-spacer-10"></div>

                    <!-- Gerenciamento de Cor -->
                    <md-list-item ui-sref="cor.list" ui-sref-active="{'active': 'cor'}">
                        <i class="fa fa-paint-brush icon-18" aria-hidden="true"></i>
                        Cor
                    </md-list-item>
                </md-list>
            </md-content>
        </md-sidenav>
        <!-- ./sidenav -->

        <!-- wrapper -->
        <div flex layout="column">
            <md-content flex ui-view></md-content>
        </div>
        <!-- ./wrapper -->

        <!-- wrapper -->
<!--        <div class="wrapper">
            <header class="main-header">
                <a ui-sref="index" class="logo">
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
                            <li><a href="${linkTo[FuncionarioController].list}">Funcion�rio</a></li>
                            </c:if>

                            <li class="dropdown user">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-user"></i>
                                    <span class="hidden-xs">${funcionarioSession.firstAndLastName}</span>
                                    <i class="fa fa-fw fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${linkTo[FuncionarioController].editAccount}"><i class="fa fa-pencil"></i>&nbsp;Alterar conta</a></li>
                                    <li><a href="${linkTo[FuncionarioController].editPassword}"><i class="fa fa-key"></i>&nbsp;Alterar senha</a></li>
                                    <li class="divider"></li>
                                    <li><a href="logout"><span class="fa fa-sign-out"></span>&nbsp;Sair</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
             ./main-header
            -->


        <!-- AngularJS -->
        <script src="${pageContext.request.contextPath}/js/lib/angular.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/angular-animate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/angular-aria.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/angular-resource.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/angular-ui-router.min.js"></script>

        <!-- Angular Material v1.1.1 -->
        <script src="${pageContext.request.contextPath}/js/lib/angular-material.min.js"></script>

        <!-- GTF -->
        <script src="${pageContext.request.contextPath}/js/app/app.js"></script>
        <script src="${pageContext.request.contextPath}/js/app/service.js"></script>
        <script src="${pageContext.request.contextPath}/js/app/controller.js"></script>
    </body>
</html>