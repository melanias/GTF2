<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="row">
                        <div class="col-xs-3">
                            <div class="info-box">
                                <span class="info-box-icon bg-green"><i class="fa fa-paint-brush"></i></span>

                                <div class="info-box-content">
                                    <span class="info-box-text">Cores</span>
                                    <span class="info-box-number">${totalColors}</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <div class="info-box">
                                <span class="info-box-icon bg-yellow"><i class="fa fa-car"></i></span>

                                <div class="info-box-content">
                                    <span class="info-box-text">Veículos</span>
                                    <span class="info-box-number">${totalVehicles}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ./row -->
    </jsp:body>
</t:template>