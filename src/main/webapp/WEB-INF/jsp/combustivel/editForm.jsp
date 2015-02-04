<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
            <div class="panel panel-default">
                <div class="panel-heading clearfix">
                    <a href="${linkTo[CombustivelController].list}" class="btn btn-sm btn-primary pull-right" role="button" title="Voltar"><span class="glyphicon glyphicon-chevron-left"></span></a>
                </div>

                <div class="panel-body">
                    <form action="${linkTo[CombustivelController].editForm(combustivel.id)}" class="form-horizontal" method="post" role="form">
                        <div class="form-group">
                            <div class="col-md-4<c:if test="${not empty errors.from('combustivel.nome')}"> has-error</c:if>">
                                <label for="nome">Nome do combustível</label>
                                <input type="text" name="combustivel.nome" value="${combustivel.nome}" class="form-control input-sm" id="nome" maxlength="100">
                                <c:if test="${not empty errors.from('combustivel.nome')}">
                                <span class="help-block">${errors.from('combustivel.nome')}</span>
                                </c:if>
                            </div>
                        </div>
                        <a href="${linkTo[CombustivelController].list}" class="btn btn-sm btn-default" role="button">Cancelar</a>
                        <button class="btn btn-sm btn-primary" type="submit">Salvar</button>
                    </form>
                </div>
            </div>
    </jsp:body>
</t:template>