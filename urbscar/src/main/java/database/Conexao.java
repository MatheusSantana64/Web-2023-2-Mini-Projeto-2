package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private String connection = "jdbc:mysql://sql.freedb.tech:3306/freedb_urbscardb";
    private String user = "freedb_urbscar", pass = "FBuPnE?srkuS&9M";

    // realiza conexão com BD
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(connection, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
