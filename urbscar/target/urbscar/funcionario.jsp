<%@page import="database.FuncionarioDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; pageEncoding=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);
                    
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Status', 'Quantidade'],
                    ['Em Manutenção', <%= request.getAttribute("busInMaintenanceCount") %>],
                    ['Disponíveis', <%= request.getAttribute("availableBusesCount") %>]
                ]);
                
                var options = {
                    title: 'Distribuição de Ônibus em Manutenção e Disponíveis'
                };
                
                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                
                chart.draw(data, options);
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interface de Funcionário</title>
    </head>
    <body>
        <h1> Gráfico de onibus em manutencao </h1>
        <div id="piechart" style="width: 900px; height: 500px;"></div>
    </body>
</html>