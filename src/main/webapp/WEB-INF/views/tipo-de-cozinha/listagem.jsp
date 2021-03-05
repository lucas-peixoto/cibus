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
    <c:forEach items="${tiposDeCozinha}"></c:forEach>
    <tr>
        <td>Arabe</td>
        <td><a href="/admin/tipos-de-cozinha/formulario-editar/1">Editar</a></td>
        <td><a href="/admin/tipos-de-cozinha/remover/1">Remover</a></td>
    </tr>
</table>
</body>
</html>