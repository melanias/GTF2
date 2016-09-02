<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="row">
                        <div class="col-xs-12">
                            <form action="${linkTo[CorController].editForm(cor.id)}" class="form-horizontal" method="post" role="form">
                                <div class="box box-default">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">
                                            ${subTitle}
                                        </h3>

                                        <div class="box-tools pull-right">
                                            <a href="${linkTo[CorController].list}" class="btn btn-box-tool" role="button" title="Voltar"><i class="fa fa-lg fa-chevron-left"></i></a>
                                        </div>
                                    </div>
                                    <!-- ./box-header -->

                                    <div class="box-body">
                                        <div class="form-group">
                                            <div class="col-md-4">
                                                <label for="nome">Nome da cor</label>
                                                <input type="text" name="cor.nome" value="${cor.nome}" class="form-control input-sm" id="nome" maxlength="50">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- ./box-body -->

                                    <div class="box-footer">
                                        <a href="${linkTo[CorController].list}" class="btn btn-sm btn-default" role="button">Cancelar</a>
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