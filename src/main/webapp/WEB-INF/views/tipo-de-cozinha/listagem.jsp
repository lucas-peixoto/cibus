<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <title>Tipos de Cozinha</title>
</head>
<body>

<h1>Tipos de cozinha</h1>
<a href="/admin/tipos-de-cozinha/novo">Adicionar novo</a>
<table>
    <tr>
        <th>Nome</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${tiposDeCozinha}" var="tipoDeCozinha">
        <tr>
            <td>${tipoDeCozinha.nome}</td>
            <td><a href="/admin/tipos-de-cozinha/editar/${tipoDeCozinha.id}">Editar</a></td>
            <td>
                <form method="post" action="/admin/tipos-de-cozinha/remover/${tipoDeCozinha.id}">
                    <button type="submit">Remover</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>