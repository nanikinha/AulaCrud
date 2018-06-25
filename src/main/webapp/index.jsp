<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Alunos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap.min.css")%>'>
        <link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap-theme.min.css")%>'>
        <style type="text/css">
            #alunos-div{
                height: 250px;
                overflow-y: auto;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Lista de Alunos</h1>
            <form action="ListaAlunoServlet" method="POST">
                <div class="form-group">
                    <label class="control-label" for="nome">Nome: </label>
                    <input value="${nomePesquisado}" class="form-control" name="nome" id="nome" type="text" />
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit" id="btnAtualizar">Atualizar</button>
                    <button onclick="parent.location.href='ManterAlunoServlet?acao=novo'" class="btn btn-primary" type="button" id="btnNovo">Novo</button>
                </div>
            </form>
            <div id="alunos-div">
                <table class="table-striped" id="aluno-table">
                    <tr>
                        <th class="col-xs-2"></th>
                        <th class="col-xs-4">Nome</th>
                        <th class="col-xs-6">Idade</th>
                    </tr>
                    <c:forEach var="aluno" items="${alunos}">
                        <tr>
                            <td class="col-xs-2">
                                <a href="ManterAlunoServlet?acao=editar&id=${aluno.id}"><img src="imagens/editar.jpeg" /></a>
                                <a href="ManterAlunoServlet?acao=excluir&id=${aluno.id}"><img src="imagens/excluir.jpeg" /></a>
                            </td>
                            <td class="col-xs-4">${aluno.nome}</td>
                            <td class="col-xs-6">${aluno.idade}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>