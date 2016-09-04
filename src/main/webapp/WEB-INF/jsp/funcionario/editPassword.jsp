<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="row">
                        <div class="col-xs-12">
                            <form action="${linkTo[FuncionarioController].editPassword}" class="form-horizontal" method="post" role="form">
                                <div class="box box-danger">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">
                                            Altere a senha da sua conta
                                        </h3>
                                    </div>
                                    <!-- ./box-header -->

                                    <div class="box-body">
                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label for="currentPassword">Senha atual</label>
                                                <input type="password" name="currentPassword" value="${not empty errors ? currentPassword : ''}" class="form-control input-sm" id="currentPassword" maxlength="30">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label for="newPassword">Nova senha</label>
                                                <input type="password" name="funcionario.senha" value="${not empty errors ? funcionario.senha : ''}" class="form-control input-sm" id="newPassword" maxlength="30">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <label for="checkPassword">Confirmar a nova senha</label>
                                                <input type="password" name="funcionario.checkPassword" value="${not empty errors ? funcionario.checkPassword : ''}" class="form-control input-sm" id="checkPassword" maxlength="30">
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
                            <c:if test="${not empty warningMessage && (empty successMessage && empty errors)}">
                            <div class="alert alert-warning alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <p><strong>${warningMessage.message}</strong></p>
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