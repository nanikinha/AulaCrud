<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edição de Aluno</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap.min.css")%>'>
        <link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap-theme.min.css")%>'>
    </head>
    <body>
        <div class="container">
            <h1>Edição de Alunos</h1>
            <form action="ManterAlunoServlet" method="POST" data-toggle="validator" role="form">
                <input type="hidden" id="id" name="id" value="${aluno.id}"/>
                <div class="form-group">
                    <label class="control-label" for="nome">Nome: </label>
                    <input value="${aluno.nome}" class="form-control" name="nome" 
                           id="nome" type="text" required />
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label class="control-label" for="idade">Idade: </label>
                    <input value="${aluno.idade}" class="form-control" name="idade" 
                           id="idade" type="text" required />
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit" id="btnAtualizar">Salvar</button>
                </div>
            </form>
        </div>
        <script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("jquery.min.js")%>'></script>
        <script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("js/bootstrap.min.js")%>'></script>
        <script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("dist/validator.min.js")%>'></script>
    </body>
</html>
