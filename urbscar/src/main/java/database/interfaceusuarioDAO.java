package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ponto;
import model.interfaceUsuario;

public class interfaceusuarioDAO {

    private String SELECT_ALL_Pontos = "select * from ponto;";
    private String Select_ALL_linhas_pontos ="SELECT o.numero AS NumeroLinha, o.nome AS NomeLinha, MIN(p.horario) AS Horario" + 
                " FROM onibus o" +
                " JOIN parada p ON o.id = p.onibus" +
                " JOIN ponto q ON q.id = p.ponto" + 
                " where q.nome = ? AND Horario >= ?" + 
                " group by o.id  order by horario" ;
                                      


    String connection = "jdbc:mysql://sql.freedb.tech:3306/freedb_urbscardb";

    String user =  "freedb_urbscar", senha = "vcQ&tUWJ?e57xVr";

    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(connection, user, senha);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn; 
        }

    public List < Ponto > selectAllPontos() throws SQLException{
            List < Ponto > pontos = new ArrayList < > ();
             try{ 
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Pontos); 
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Ponto p = new Ponto();
                pontos.add(p);
                p.setNome(rs.getString("nome"));
                    }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return pontos;
    }

    public List < interfaceUsuario > selectAllLinhas(String nomePontoString,String hora) throws SQLException{
        List < interfaceUsuario > linhas = new ArrayList < > ();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_linhas_pontos);
            preparedStatement.setString(1, nomePontoString);
            preparedStatement.setString(2, hora);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                interfaceUsuario i = new interfaceUsuario();
                linhas.add(i);
                i.setNumeroLinha(rs.getInt("NumeroLinha"));
                i.setNomeLinha(rs.getString("NomeLinha"));
                i.setHorario(rs.getString("Horario"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return linhas;
    }


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
