<%@page import="database.FuncionarioDAO"%>
<%@page contentType="text/html; pageEncoding=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript" src="funcionario.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(invocaAjax);
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interface de Funcionário</title>
    </head>
    <body>
        <h1> Gráfico de onibus em manutencao </h1>
        <div id="piechart" style="width: 900px; height: 500px;"></div>
    </body>
</html>