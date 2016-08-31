<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <section>
                        <h3>
                            <small>Informações do Sistema</small>
                        </h3>
                        <div class="row">
                            <div class="col-xs-3">
                                <div class="info-box">
                                    <span class="info-box-icon bg-blue"><i class="fa fa-file-text"></i></span>

                                    <div class="info-box-content">
                                        <span class="info-box-text">Cores</span>
                                        <span class="info-box-number">${totalColors}</span>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-3">
                                <div class="info-box">
                                    <span class="info-box-icon bg-green"><i class="fa fa-bars"></i></span>

                                    <div class="info-box-content">
                                        <span class="info-box-text">Veículos</span>
                                        <span class="info-box-number">${totalVehicles}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
    </jsp:body>
</t:template>