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
                                    <a href="${linkTo[CorController].addForm}" class="btn-floating"><i class="fa fa-lg fa-plus"></i></a>
                                    <c:choose>
                                    <c:when test="${not empty corList}">
                                    <table class="table table-hover table-bordered table-condensed">
                                        <thead>
                                            <tr>
                                                <th style="width: 5%;">#</th>
                                                <th>Cor</th>
                                                <th style="width: 5%;"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${corList}" var="cor" varStatus="row">
                                            <tr>
                                                <td>${row.count}</td>
                                                <td>${cor.nome}</td>
                                                <td>
                                                    <a href="${linkTo[CorController].editForm(cor.id)}" role="button" title="${editTitle}"><i class="fa fa-fw fa-pencil"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    </c:when>
                                    <c:otherwise>
                                    <p>Nenhuma cor cadastrada até o momento. Clique <a href="${linkTo[CorController].addForm}">aqui</a> para cadastrar.</p>
                                    </c:otherwise>
                                    </c:choose>
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