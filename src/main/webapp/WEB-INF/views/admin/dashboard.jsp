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

    <title>Restaurantes por Tipo de Cozinha</title>
</head>
<body>

<div class="container pt-3 px-md-5">
    <div class="row justify-content-center">
        <div class="col-md-8">

            <h1 class="mb-3">
                <span class="titulo me-3">Restaurantes por Tipo de Cozinha</span>
            </h1>

            <table class="table">
                <thead>
                <tr>
                    <th>Tipo de Cozinha</th>
                    <th>Número de Restaurantes</th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <c:forEach items="${restaurantesPorTipoDeCozinha}" var="tipoDeCozinha">
                    <tr>
                        <td class="nome-tipo-de-cozinha">${tipoDeCozinha.nome}</td>
                        <td class="nome-tipo-de-cozinha">${tipoDeCozinha.totalDeRestaurantes}</td>
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