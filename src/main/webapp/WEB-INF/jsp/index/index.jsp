<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="jumbotron">
                        <p>
                            Este sistema foi desenvolvido para facilitar o controle e a
                            administra��o dos ve�culos de empresas p�blicas e/ou privadas.
                        </p>
                        <br>
                        <p>
                            Vers�o atual: ${environment.get('appVersion')}
                        </p>
                    </div>
    </jsp:body>
</t:template>