<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="panel panel-default">
                        <div class="panel-heading clearfix">
                            <a href="${linkTo[CombustivelController].addForm}" class="btn btn-sm btn-primary pull-right" role="button" title="${addTitle}"><span class="glyphicon glyphicon-plus"></span></a>
                        </div>
        <c:choose>
            <c:when test="${not empty combustivelList}">
                        <table class="table table-hover table-striped table-bordered table-condensed">
                            <thead>
                                <tr>
                                    <th style="width: 5%;">#</th>
                                    <th>Combustível</th>
                                    <th style="width: 5%;"></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${combustivelList}" var="combustivel" varStatus="row">
                                <tr>
                                    <td>${row.count}</td>
                                    <td>${combustivel.nome}</td>
                                    <td>
                                        <a href="${linkTo[CombustivelController].editForm(combustivel.id)}" role="button" title="${editTitle}"><span class="glyphicon glyphicon-pencil"></span></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
            </c:when>
            <c:otherwise>
                        <div class="panel-body">
                            <p>Nenhum combustível cadastrado até o momento.</p>
                        </div>
            </c:otherwise>
        </c:choose>
                    </div>

                    <!-- Modal Window -->
                    <div class="modal fade" id="modalWindow" tabindex="-1" role="dialog" aria-labelledby="modalWindowLabel" aria-hidden="true">
                        <div class="modal-dialog" style="width: 400px;">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="modalWindowLabel"></h4>
                                </div>
                                <div class="modal-body"></div>
                                <div class="modal-footer">
                                    <button class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                    <button class="btn btn-primary" id="saveThis">Salvar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal Window -->
    </jsp:body>
</t:template>