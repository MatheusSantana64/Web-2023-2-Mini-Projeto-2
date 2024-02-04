package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {
    
    private String connection = "jdbc:mysql://sql.freedb.tech:3306/freedb_urbscardb";
    private String user = "freedb_urbscar", pass = "vcQ&tUWJ?e57xVr";

    // Consulta para contar o número de ônibus em manutenção
    private String COUNT_manutencao = "SELECT COUNT(*) FROM onibus WHERE manutencao = TRUE;";
    // Consulta para contar o número de ônibus disponíveis (não em manutenção)
    private String COUNT_funcional = "SELECT COUNT(*) FROM onibus WHERE manutencao = FALSE;";

    // realiza conexão com BD
    public Connection getConnection() {
        Connection conn = null;
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(connection, user, pass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao estabelecer conexão com o banco de dados", e);
        }
        return conn;
    }

    public int countOnibusManutencao() throws SQLException {
        int count = 0;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(COUNT_manutencao)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int countOnibusFuncionando() throws SQLException {
        int count = 0;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(COUNT_funcional)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }
} // fim da classe FuncionarioDAO