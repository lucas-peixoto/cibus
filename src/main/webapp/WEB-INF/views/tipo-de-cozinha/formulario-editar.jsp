<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="/admin/tipos-de-cozinha/editar/${tipoDeCozinha.id}">
    <input type="hidden" name="id" value="${tipoDeCozinha.id}">
    <label for="nome">Nome</label>
    <input id="nome" type="text" name="nome" value="${tipoDeCozinha.nome}" maxlength="50" required>
    <form:errors path="tipoDeCozinhaParaEdicaoForm.nome"/>
    <input type="submit" value="Salvar">
    <a href="/admin/tipos-de-cozinha">Cancelar</a>
</form>

</body>
</html>