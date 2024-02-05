
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ponto;
import model.interfaceUsuario;
import database.interfaceusuarioDAO;

@WebServlet("/interfaceusuario")
public class interfaceusuarioServlet extends HttpServlet {
    private interfaceusuarioDAO listaDePontos;

    public void init() {
        listaDePontos = new interfaceusuarioDAO();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Ponto> pontos = listaDePontos.selectAllPontos();
            enviarRespostaJSON(response, pontos);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao obter pontos de parada.");
        }
    }

    private void enviarRespostaJSON(HttpServletResponse response, List<Ponto> pontos) throws IOException {
        // Configura a resposta para ser JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Constrói manualmente a representação JSON da lista de pontos
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < pontos.size(); i++) {
            Ponto ponto = pontos.get(i);
            jsonBuilder.append("{\"id\":").append(ponto.getId()).append(", \"nome\":\"").append(ponto.getNome())
                    .append("\"}");
            if (i < pontos.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");

        // Envia a resposta JSON de volta ao cliente
        PrintWriter out = response.getWriter();
        out.print(jsonBuilder.toString());
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        try {
            List<interfaceUsuario> interfaceUsu = listaDePontos.selectAllLinhas(request.getParameter("nomePonto"), request.getParameter("hora"));

            // Constrói manualmente a representação JSON da lista de resultados
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("[");
            for (int i = 0; i < interfaceUsu.size(); i++) {
                interfaceUsuario usu = interfaceUsu.get(i);
                jsonBuilder.append("{\"numeroLinha\":").append(usu.getNumeroLinha()).append(", \"nomeLinha\":\"").append(usu.getNomeLinha()).append("\", \"horario\":\"").append(usu.getHorario()).append("\"}");
                if (i < interfaceUsu.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("]");

            // Envia a resposta JSON de volta ao cliente
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonBuilder.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao obter pontos de parada.");
        }
        }
}
