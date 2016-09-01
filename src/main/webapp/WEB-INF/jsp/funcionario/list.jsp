<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-default">
                                <div class="box-header with-border">
                                    <h3 class="box-title">
                                        ${subTitle}
                                    </h3>
                                </div>
                                <!-- ./box-header -->

                                <div class="box-body">
                                    <a href="${linkTo[FuncionarioController].addForm}" class="btn-floating"><i class="fa fa-lg fa-plus"></i></a>
                                    <table class="table table-hover table-bordered table-condensed" id="employeeTable">
                                        <thead>
                                            <tr>
                                                <th style="width: 12%;">CPF</th>
                                                <th>Nome</th>
                                                <th>E-mail</th>
                                                <th style="width: 10%;">Perfil</th>
                                                <th style="width: 10%;">Situação</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${funcionarioList}" var="funcionario">
                                                <tr>
                                                    <td>${funcionario.cpf}</td>
                                                    <td>${funcionario.nome}</td>
                                                    <td>${not empty funcionario.email ? funcionario.email : "-"}</td>
                                                    <td>${funcionario.perfil.role}</td>
                                                    <td>${funcionario.status.status}</td>
                                                    <td>
                                                        <a href="${linkTo[FuncionarioController].view(funcionario.id)}" role="button" title="${viewTitle}" data-toggle="modal" data-header="${viewTitle}"><i class="fa fa-fw fa-list-alt"></i></a>
                                                        <a href="${linkTo[FuncionarioController].editForm(funcionario.id)}" role="button" title="${editTitle}"><i class="fa fa-fw fa-pencil"></i></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- ./box-body -->
                            </div>
                            <!-- ./box -->

                            <!-- modalWindow -->
                            <div class="modal fade" id="modalWindow" tabindex="-1" role="dialog" aria-labelledby="modalWindowLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" tabindex="-1" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="modalWindowLabel"></h4>
                                        </div>
                                        <div class="modal-body"></div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" tabindex="-1" data-dismiss="modal">Fechar</button>
                                            <button type="button" class="btn btn-primary" tabindex="-1" id="saveIt">Salvar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- ./modalWindow -->
                        </div>
                        <!-- ./col-xs-12 -->
                    </div>
                    <!-- ./row -->
    </jsp:body>
</t:template>