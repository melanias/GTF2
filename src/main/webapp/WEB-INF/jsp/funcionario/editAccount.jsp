<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="row">
                        <div class="col-xs-12">
                            <form action="${linkTo[FuncionarioController].editAccount}" class="form-horizontal" method="post" role="form">
                                <div class="box box-danger">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">
                                            Altere as informações básicas da sua conta
                                        </h3>
                                    </div>
                                    <!-- ./box-header -->

                                    <div class="box-body">
                                        <div class="form-group">
                                            <div class="col-md-2">
                                                <label for="cpf">CPF</label>
                                                <input type="text" name="funcionario.cpf" value="${funcionario.cpf}" class="form-control input-sm" id="cpf" alt="cpf" readonly>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label for="nome">Nome</label>
                                                <input type="text" name="funcionario.nome" value="${funcionario.nome}" class="form-control input-sm" id="nome" maxlength="200">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label for="email">E-mail</label>
                                                <input type="email" name="funcionario.email" value="${funcionario.email}" class="form-control input-sm" id="email" maxlength="200">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- ./box-body -->

                                    <div class="box-footer">
                                        <button class="btn btn-sm btn-primary" type="submit">Salvar</button>
                                    </div>
                                </div>
                                <!-- ./box -->
                            </form>
                            <c:if test="${not empty successMessage}">
                            <div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <p><strong>${successMessage}</strong></p>
                            </div>
                            </c:if>
                            <c:if test="${not empty errors}">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <c:forEach items="${errors}" var="error">
                                <p><strong>${error.message}</strong></p>
                                </c:forEach>
                            </div>
                            </c:if>
                        </div>
                        <!-- ./col-xs-12 -->
                    </div>
                    <!-- ./row -->
    </jsp:body>
</t:template>