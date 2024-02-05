package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ponto;
import model.Usuario;

public class UsuarioDAO {

    // Define a consulta SQL para selecionar todos os pontos do banco de dados
    private String SELECT_ALL_Pontos = "select * from ponto;";
    // Define a consulta SQL para selecionar todas as linhas de ônibus que passam por um ponto específico após uma determinada hora
    private String Select_ALL_linhas_pontos = "SELECT o.numero AS NumeroLinha, o.nome AS NomeLinha, MIN(p.horario) AS Horario" + 
        " FROM onibus o" +
        " JOIN parada p ON o.id = p.onibus" +
        " JOIN ponto q ON q.id = p.ponto" + 
        " where q.nome = ? AND Horario >= ?" + 
        " group by o.id order by horario";

    // Método para selecionar todos os pontos do banco de dados
    public List<Ponto> selectAllPontos(Connection conn) throws SQLException {
        // Cria uma lista vazia para armazenar os objetos Ponto
        List<Ponto> pontos = new ArrayList<>();
        // Prepara a consulta SQL para buscar todos os pontos
        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_Pontos)) {
            // Executa a consulta e obtém o conjunto de resultados
            ResultSet rs = preparedStatement.executeQuery();
            // Itera sobre o conjunto de resultados
            while (rs.next()) {
                // Cria um novo objeto Ponto para cada linha retornada
                Ponto p = new Ponto();
                // Adiciona o objeto Ponto à lista
                pontos.add(p);
                // Define o nome do ponto com base nos dados da coluna "nome"
                p.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            // Trata qualquer exceção SQL que possa ocorrer durante a execução da consulta
            printSQLException(e);
            throw e;
        }
        // Retorna a lista de objetos Ponto
        return pontos;
    }

    // Método para selecionar todas as linhas de ônibus que passam por um ponto específico após uma determinada hora
    public List< Usuario > selectAllLinhas(Connection conn, String nomePontoString, String hora) throws SQLException {
        // Cria uma lista vazia para armazenar os objetos interfaceUsuario
        List< Usuario > linhas = new ArrayList<>();
        // Prepara a consulta SQL para buscar as linhas de ônibus
        try (PreparedStatement preparedStatement = conn.prepareStatement(Select_ALL_linhas_pontos)) {
            // Define os parâmetros da consulta SQL
            preparedStatement.setString(1, nomePontoString);
            preparedStatement.setString(2, hora);
            // Executa a consulta e obtém o conjunto de resultados
            ResultSet rs = preparedStatement.executeQuery();
            // Itera sobre o conjunto de resultados
            while (rs.next()) {
                // Cria um novo objeto Usuario para cada linha retornada
                Usuario i = new Usuario();
                // Adiciona o objeto interfaceUsuario à lista
                linhas.add(i);
                // Define os atributos do objeto interfaceUsuario com base nos dados das colunas correspondentes
                i.setNumeroLinha(rs.getInt("NumeroLinha"));
                i.setNomeLinha(rs.getString("NomeLinha"));
                i.setHorario(rs.getString("Horario"));
            }
        } catch (SQLException e) {
            // Trata qualquer exceção SQL que possa ocorrer durante a execução da consulta
            printSQLException(e);
            throw e;
        }
        // Retorna a lista de objetos interfaceUsuario
        return linhas;
    }

    // Método privado para imprimir informações detalhadas sobre exceções SQL
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }   
    }
}
