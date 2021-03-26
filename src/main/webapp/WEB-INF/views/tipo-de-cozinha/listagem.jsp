<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Tipos de Cozinha</title>
</head>
<body>

<div class="container pt-3 px-md-5">
    <div class="row justify-content-center">
        <div class="col-md-8">

            <h1 class="mb-3">
                <span class="me-3">Tipos de cozinha</span>
                <a href="/admin/tipos-de-cozinha/novo" class="btn btn-success">Adicionar novo</a>
            </h1>

            <table class="table">
                <thead>
                <tr>
                    <th>Nome</th>
                    <th></th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <c:forEach items="${tiposDeCozinha}" var="tipoDeCozinha">
                    <tr>
                        <td>${tipoDeCozinha.nome}</td>
                        <td class="d-flex justify-content-end">
                            <a href="/admin/tipos-de-cozinha/editar/${tipoDeCozinha.id}" class="btn btn-primary me-2">Editar</a>
                            <form method="post" action="/admin/tipos-de-cozinha/remover/${tipoDeCozinha.id}">
                                <button class="btn btn-danger" type="submit">Remover</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>
</body>
</html>