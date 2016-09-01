<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="row">
                        <div class="col-xs-12">
                            <form action="${linkTo[FuncionarioController].add}" class="form-horizontal" method="post">
                                <div class="box box-default">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">
                                            ${subTitle}
                                        </h3>

                                        <div class="box-tools pull-right">
                                            <a href="${linkTo[FuncionarioController].list}" class="btn btn-box-tool" role="button" title="Voltar"><i class="fa fa-lg fa-chevron-left"></i></a>
                                        </div>
                                    </div>
                                    <!-- ./box-header -->

                                    <div class="box-body">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <div class="col-md-6">
                                                        <label for="cpf">CPF</label>
                                                        <input type="text" name="funcionario.cpf" value="${funcionario.cpf}" class="form-control input-sm" id="cpf" alt="cpf">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <label for="nome">Nome</label>
                                                        <input type="text" name="funcionario.nome" value="${funcionario.nome}" class="form-control input-sm" id="nome" maxlength="200">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <label for="email">E-mail</label>
                                                        <input type="email" name="funcionario.email" value="${funcionario.email}" class="form-control input-sm" id="email" maxlength="200">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-md-6">
                                                        <label for="perfil">Perfil</label>
                                                        <select name="funcionario.perfil" class="form-control input-sm select2" id="perfil">
                                                        <c:forEach items="${roles}" var="r">
                                                            <option value="${r}"${(r == funcionario.perfil) ? " selected" : ""}>${r.role}</option>
                                                        </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="col-md-6">
                                                        <label for="status">Situação</label>
                                                        <select name="funcionario.status" class="form-control input-sm select2" id="status">
                                                        <c:forEach items="${status}" var="s">
                                                            <option value="${s}"${(s == funcionario.status) ? " selected" : ""}>${s.status}</option>
                                                        </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- ./col-md-4 -->
                                        </div>
                                        <!-- ./row -->
                                    </div>
                                    <!-- ./box-body -->

                                    <div class="box-footer">
                                        <a href="${linkTo[FuncionarioController].list}" class="btn btn-sm btn-default" role="button">Cancelar</a>
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
                            <c:if test="${not empty exception}">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <p>Ocorreu um erro:&nbsp;<strong>${exception.message}</strong></p>
                            </div>
                            </c:if>
                        </div>
                        <!-- ./col-xs-12 -->
                    </div>
                    <!-- ./row -->
    </jsp:body>
</t:template>