<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form method="post" action="/admin/tipos-de-cozinha/novo">
    <label for="nome">Nome</label>
    <input id="nome" type="text" name="nome">
    <form:errors path="tipoDeCozinhaForm.nome"/>
    <input type="submit" value="Salvar">
    <a href="/admin/tipos-de-cozinha">Cancelar</a>
</form>

</body>
</html>