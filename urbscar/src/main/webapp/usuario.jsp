<%@ page import="model.Ponto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Onibus por Parada</title>
    

    <script src="usuario.js"></script>
</head>
<body>
    <form>

        <label for="pontos">Escolha um ponto de parada:</label>
        <select id="pontos" name="pontos">

        </select>
        <input type="button" id="botaoSelecionar" value="Pesquisar" onclick="carregarDados()">
    </form>
    <table id="tabelaResultado" border=1>
        <thead>
            <tr>
                <th width = "300px">Número da Linha</th>
                <th width = "300px">Nome da Linha</th>
                <th width = "150px">Horário</th>
            </tr>
        </thead>
        <tbody>
            
        </tbody>
    </table>
</body>
</html>
