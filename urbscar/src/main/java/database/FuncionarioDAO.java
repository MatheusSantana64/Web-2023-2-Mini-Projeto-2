package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {

    // Consulta para contar o número de ônibus em manutenção
    private String COUNT_manutencao = "SELECT COUNT(*) FROM onibus WHERE manutencao = TRUE;";
    // Consulta para contar o número de ônibus disponíveis (não em manutenção)
    private String COUNT_funcional = "SELECT COUNT(*) FROM onibus WHERE manutencao = FALSE;";

    // Consulta para contar o número de ônibus atrasados
    private String COUNT_atrasado = "SELECT COUNT(*) FROM parada WHERE atrasado = TRUE;";
    // Consulta para contar o número de ônibus que estão no horário certo
    private String COUNT_pontual = "SELECT COUNT(*) FROM parada WHERE atrasado = FALSE;";

    public int countOnibusManutencao(Connection conn) throws SQLException {
        int count = 0;
        try (PreparedStatement ps = conn.prepareStatement(COUNT_manutencao)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int countOnibusFuncionando(Connection conn) throws SQLException {
        int count = 0;
        try (PreparedStatement ps = conn.prepareStatement(COUNT_funcional)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int onibusAtrasadosCount(Connection conn) throws SQLException {
        int count = 0;
        try (PreparedStatement ps = conn.prepareStatement(COUNT_atrasado)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

    public int onibusPontuaisCount(Connection conn) throws SQLException {
        int count = 0;
        try (PreparedStatement ps = conn.prepareStatement(COUNT_pontual)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }

} // fim da classe FuncionarioDAO