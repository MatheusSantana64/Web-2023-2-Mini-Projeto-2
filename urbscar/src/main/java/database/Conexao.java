package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private String connection = "jdbc:mysql://sql.freedb.tech:3306/freedb_urbscardb";
    private String user = "freedb_urbscar", pass = "vcQ&tUWJ?e57xVr";

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

}
