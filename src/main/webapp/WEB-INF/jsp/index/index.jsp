<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<t:template title="${title}" subTitle="${subTitle}">
    <jsp:body>
                    <div class="jumbotron">
                        <p>
                            Este sistema foi desenvolvido para facilitar o controle e a
                            administração dos veículos de empresas públicas e/ou privadas.
                        </p>
                        <br>
                        <p>
                            Versão atual: ${environment.get('appVersion')}
                        </p>
                    </div>
    </jsp:body>
</t:template>