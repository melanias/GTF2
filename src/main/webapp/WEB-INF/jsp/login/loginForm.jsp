<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, shrink-to-fit=no">
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="Phelipe Melanias">

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Login -->
        <link href="${pageContext.request.contextPath}/css/fonts.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">

        <title>${title}</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <c:if test="${not empty errors}">
                <div class="page-alert">
                    <div class="alert alert-danger alert-animated fadeInUp">
                    <c:forEach items="${errors}" var="error">
                        <p><strong>${error.message}</strong></p>
                    </c:forEach>
                    </div>
                </div>
                </c:if>

                <div class="form-login">
                    <form action="${linkTo[LoginController].login}" method="post" role="form">
                        <fieldset>
                            <legend>
                                <strong>${title}</strong>&nbsp;<small>${subTitle}</small>
                            </legend>

                            <div class="form-group has-feedback">
                                <input type="text" name="cpf" value="${cpf}" class="form-control" placeholder="Digite seu CPF" aria-label="CPF">
                                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                            </div>

                            <div class="form-group has-feedback">
                                <input type="password" name="senha" value="" class="form-control" placeholder="Digite sua senha" aria-label="Senha">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>

                            <button class="btn btn-primary btn-block pull-right" type="submit">Entrar</button>
                        </fieldset>
                    </form>
                </div>
                <!-- ./form-login -->
            </div>
            <!-- ./row -->
        </div>
        <!-- ./container -->

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>

        <!-- jQuery Mask Plugin -->
        <script src="${pageContext.request.contextPath}/js/jquery.mask.min.js"></script>

        <!-- Login stuff -->
        <script>
            $("input[value='']:first").focus();

            //CPF mask
            $("input[name='cpf']").mask("000.000.000-00");
        </script>
    </body>
</html>