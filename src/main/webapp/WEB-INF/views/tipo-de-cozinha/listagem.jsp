<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Tipos de cozinha</title>
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
            <td><a href="/admin/tipos-de-cozinha/formulario-editar/${tipoDeCozinha.id}">Editar</a></td>
            <td><a href="/admin/tipos-de-cozinha/remover/${tipoDeCozinha.id}">Remover</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>