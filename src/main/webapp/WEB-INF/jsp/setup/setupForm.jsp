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

        <!-- Setup -->
        <link href="${pageContext.request.contextPath}/css/fonts.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/setup.css" rel="stylesheet">

        <title>${title}</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="page-header">
                    <h1>${title}</h1>
                </div>
                <div class="form-setup">
                    <form action="${linkTo[SetupController].setup}" class="form-horizontal" method="post" role="form">
                        <fieldset>
                            <legend>Usuário administrador</legend>

                            <div class="form-group">
                                <div class="col-md-5${empty errors.from("funcionario.cpf") ? "" : " has-error"}">
                                    <label for="cpf">CPF</label>
                                    <input type="text" name="funcionario.cpf" value="${funcionario.cpf}" class="form-control input-sm" id="cpf" placeholder="CPF" alt="cpf">
                                    ${(empty errors.from('funcionario.cpf')) ? '' : '<span class="help-block">'.concat(errors.from('funcionario.cpf')).concat('</span>')}
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-10${empty errors.from("funcionario.nome") ? "" : " has-error"}">
                                    <label for="nome">Nome</label>
                                    <input type="text" name="funcionario.nome" value="${funcionario.nome}" class="form-control input-sm" id="nome" placeholder="Nome completo" maxlength="200">
                                    ${(empty errors.from("funcionario.nome")) ? "" : '<span class="help-block">'.concat(errors.from("funcionario.nome")).concat("</span>")}
                                </div>
                            </div>

                            <button class="btn btn-primary" type="submit">Salvar</button>
                        </fieldset>
                    </form>
                </div><!-- ./form-setup -->
            </div><!-- ./row -->
        </div><!-- ./container -->

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>

        <!-- jQuery Mask Plugin -->
        <script src="${pageContext.request.contextPath}/js/jquery.mask.min.js"></script>

        <!-- Setup stuff -->
        <script>
            $("input[value='']:first").focus();

            //CPF mask
            $("input[alt='cpf']").mask("000.000.000-00");
        </script>
    </body>
</html>