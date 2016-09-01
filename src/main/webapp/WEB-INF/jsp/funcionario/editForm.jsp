<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="row">
                        <div class="col-xs-12">
                            <form action="${linkTo[FuncionarioController].editForm(funcionario.id)}" class="form-horizontal" method="post">
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

                                                    <div class="col-md-6">
                                                        <label for="status">Situação</label>
                                                        <select name="funcionario.status" class="form-control input-sm select2" id="status">
                                                            <c:forEach items="${status}" var="s">
                                                            <option value="${s}"${(s == funcionario.status) ? " selected" : ""}>${s.status}</option>
                                                            </c:forEach>
                                                        </select>
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
                                                        <input type="email" name="funcionario.email" value="${funcionario.email}" class="form-control input-sm" id="email" maxlength="100">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-md-6">
                                                        <label for="celular">Celular</label>
                                                        <input type="text" name="funcionario.celular" value="${funcionario.celular}" class="form-control input-sm" id="celular" alt="fone">
                                                    </div>

                                                    <div class="col-md-6">
                                                        <label for="telefone">Telefone</label>
                                                        <input type="text" name="funcionario.telefone" value="${funcionario.telefone}" class="form-control input-sm" id="telefone" alt="fone">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <label for="setor">Setor</label>
                                                        <select name="funcionario.setor.id" class="form-control input-sm select2" id="setor" data-placeholder="Selecione um setor" data-allow-clear="true"${empty setores ? "disabled" : ""}>
                                                            <option value=""></option>
                                                            <c:forEach items="${setores}" var="s">
                                                            <option value="${s.id}"${s.id == funcionario.setor.id ? " selected" : ""}>${s.sigla}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-md-6">
                                                        <input type="checkbox" name="resetPasswd" value="true" id="resetPasswd"${resetPasswd ? " checked" : ""}>
                                                        <label for="resetPasswd">&nbsp;&nbsp;Resetar a senha</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- ./col-md-4 -->

                                            <div class="col-md-8">
                                                <c:forEach items="${applications}" var="app" varStatus="count">
                                                <div class="form-group">
                                                    <div class="col-md-5">
                                                        <label for="aplicativo${count.index}">${app.app}</label>
                                                        <input type="hidden" name="funcionario.apps[${count.index}].aplicativo" value="${app}">
                                                        <select name="funcionario.apps[${count.index}].perfil" class="form-control input-sm select2" id="aplicativo${count.index}">
                                                            <c:forEach items="${roles}" var="r">
                                                            <option value="${r}"${((funcionario.apps[count.index].aplicativo == app) && (funcionario.apps[count.index].perfil == r)) ? " selected" : ""}>${r.role}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                </c:forEach>
                                            </div>
                                            <!-- ./col-md-8 -->
                                        </div>
                                        <!-- ./row -->
                                    </div>
                                    <!-- ./box-body -->

                                    <div class="box-footer">
                                        <a href="${linkTo[FuncionarioController].list}" class="btn btn-sm btn-default" role="button">Cancelar</a>
                                        <button class="btn btn-sm btn-primary" type="submit">Salvar</button>
                                    </div>
                                </div>
                            </form>
                            <!-- ./box -->
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