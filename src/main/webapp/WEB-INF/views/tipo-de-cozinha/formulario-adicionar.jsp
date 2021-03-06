<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <title>Adicionar um Tipo de Cozinha</title>
</head>
<body>

<div class="container pt-3 px-md-5">
    <div class="row justify-content-center">
        <div class="col-md-8">

            <h3 class="titulo">Adicionar um Tipo de Cozinha</h3>
            <hr class="mb-3">
            <form class="form-adicionar-tipo-de-cozinha" method="post" action="/admin/tipos-de-cozinha/novo">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome:</label>
                    <input id="nome" type="text" name="nome" class="form-control" maxlength="50" required>
                    <form:errors path="tipoDeCozinhaForm.nome" cssClass="text-danger"/>
                </div>

                <div class="d-flex justify-content-end">
                    <input type="submit" class="btn btn-primary me-2" value="Salvar">
                    <a href="/admin/tipos-de-cozinha" class="btn btn-danger">Cancelar</a>
                </div>
            </form>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>
</body>
</html>