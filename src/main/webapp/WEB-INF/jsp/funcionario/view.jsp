<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${not empty funcionario}">
                                    <table class="table table-striped table-condensed">
                                        <tbody>
                                            <tr>
                                                <th>CPF:</th>
                                                <td>${funcionario.cpf}</td>

                                                <th>Data de cadastro:</th>
                                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.data}" /></td>
                                            </tr>
                                            <tr>
                                                <th>Nome:</th>
                                                <td colspan="3">${funcionario.nome}</td>
                                            </tr>
                                            <tr>
                                                <th>E-mail:</th>
                                                <td colspan="3">${not empty funcionario.email ? funcionario.email : "-"}</td>
                                            </tr>
                                            <tr>
                                                <th>Perfil:</th>
                                                <td>${funcionario.perfil.role}</td>

                                                <th>Situa��o:</th>
                                                <td>${funcionario.status.status}</td>
                                            </tr>
                                        </tbody>
                                    </table>
    </c:when>
    <c:otherwise>
                                    <p>Este funcion�rio n�o existe ou foi exclu�do.</p>
    </c:otherwise>
</c:choose>