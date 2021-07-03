<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <title>Editar um Restaurante</title>
</head>
<body>

<div class="container pt-3 px-md-5">
    <div class="row justify-content-center">
        <div class="col-md-8">

            <h3 class="titulo">Editar um Restaurante</h3>
            <hr class="mb-3">
            <form class="form-editar-tipo-de-cozinha" method="post" action="/restaurantes/editar/${restaurante.slug}">
                <input type="hidden" name="id" value="${restaurante.id}">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome:</label>
                    <input id="nome" type="text" name="nome" class="form-control" value="${restaurante.nome}"
                           maxlength="50" required>
                    <form:errors path="restauranteParaEdicaoForm.nome" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="cnpj" class="form-label">CNPJ:</label>
                    <input id="cnpj" type="text" name="cnpj" class="form-control" value="${restaurante.cnpj}"
                           maxlength="14" required>
                    <form:errors path="restauranteParaEdicaoForm.cnpj" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="cep" class="form-label">CEP:</label>
                    <input id="cep" type="text" name="cep" class="form-control" value="${restaurante.cep}"
                           maxlength="14" required>
                    <form:errors path="restauranteParaEdicaoForm.cep" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="endereco" class="form-label">Endereço:</label>
                    <textarea id="endereco" name="endereco" class="form-control" rows="2" required>${restaurante.endereco}</textarea>
                    <form:errors path="restauranteParaEdicaoForm.endereco" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="tipoDeCozinhaId" class="form-label">Tipo de cozinha:</label>
                    <select id="tipoDeCozinhaId" name="tipoDeCozinhaId" class="form-control" required>
                        <c:forEach items="${tiposDeCozinha}" var="tipoDeCozinha">
                            <option value="${tipoDeCozinha.id}" <c:if test="${tipoDeCozinha.id == restaurante.tipoDeCozinha.id}">selected</c:if>>
                                    ${tipoDeCozinha.nome}
                            </option>
                        </c:forEach>
                    </select>
                    <form:errors path="restauranteParaEdicaoForm.tipoDeCozinhaId" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="taxaDeEntrega" class="form-label">Taxa de entrega (em R$):</label>
                    <input id="taxaDeEntrega" type="number" name="taxaDeEntrega" class="form-control" value="${restaurante.taxaDeEntrega}"
                           step="0.01" required>
                    <form:errors path="restauranteParaEdicaoForm.taxaDeEntrega" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="tempoMinimoEntrega" class="form-label">Tempo de entrega mínimo (em minutos):</label>
                    <input id="tempoMinimoEntrega" type="number" name="tempoMinimoEntrega" class="form-control" value="${restaurante.tempoMinimoEntrega}"
                           step="1" required>
                    <form:errors path="restauranteParaEdicaoForm.tempoMinimoEntrega" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="tempoMaximoEntrega" class="form-label">Tempo de entrega máximo (em minutos):</label>
                    <input id="tempoMaximoEntrega" type="number" name="tempoMaximoEntrega" class="form-control" value="${restaurante.tempoMaximoEntrega}"
                           step="1" required>
                    <form:errors path="restauranteParaEdicaoForm.tempoMaximoEntrega" cssClass="text-danger"/>
                </div>
                <div class="mb-3">
                    <label for="descricao" class="form-label">Descrição:</label>
                    <textarea id="descricao" name="descricao" class="form-control" rows="5" required>${restaurante.descricao}</textarea>
                    <form:errors path="restauranteParaEdicaoForm.descricao" cssClass="text-danger"/>
                </div>

                <div class="d-flex justify-content-end">
                    <input type="submit" class="btn btn-primary me-2" value="Salvar">
                    <a href="/restaurantes" class="btn btn-danger">Cancelar</a>
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