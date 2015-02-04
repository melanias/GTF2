<%@ tag description="Template Principal da Aplicação" pageEncoding="UTF-8" %>

<%@ attribute name="title" required="true" %>
<%@ attribute name="subTitle" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Phelipe Melanias">

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/bootstrap/css/jasny-bootstrap.min.css" rel="stylesheet">

        <!-- DataTables -->
        <link href="${pageContext.request.contextPath}/datatables/dataTables.bootstrap.css" rel="stylesheet">

        <!-- App -->
        <link href="${pageContext.request.contextPath}/css/gtfall.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <title>${title}</title>
    </head>
    <body>
        <c:if test="${not empty successMessage}">
        <!-- Success message -->
        <div class="page-alert">
            <div class="alert alert-info alert-fixed-bottom alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <p><strong>${successMessage}</strong></p>
            </div>
        </div>
        <!-- ./Success message -->
        </c:if>

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

        <!-- Container -->
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        ${title}
                        <small>
                            <span class="glyphicon glyphicon-chevron-right"></span>
                            ${subTitle}
                        </small>
                    </h1>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <jsp:doBody/>
                </div>
            </div>
        </div>
        <!-- ./Container -->

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-migrate.js"></script>

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/bootstrap/js/jasny-bootstrap.min.js"></script>

        <!-- DataTables -->
        <script src="${pageContext.request.contextPath}/datatables/dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/datatables/dataTables.bootstrap.js"></script>

        <!-- meioMask -->
        <script src="${pageContext.request.contextPath}/js/meiomask.js"></script>

        <!-- Root URL -->
        <script type="text/javascript">
            const ROOTURL  = "${pageContext.request.contextPath}";
        </script>

        <!-- App JS -->
        <script src="${pageContext.request.contextPath}/js/gtfall.js"></script>
    </body>
</html>