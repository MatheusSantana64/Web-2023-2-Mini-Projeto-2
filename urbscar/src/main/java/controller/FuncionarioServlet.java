package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FuncionarioDAO;

@WebServlet("/funcionario")
public class FuncionarioServlet extends HttpServlet {
    // um unico DAO com todas as acoes relacionadas a funcionario
    private FuncionarioDAO funcionarioDAO;

    public void init() {
        // assim que o servlet é carregado, já inicializa o DAO
        funcionarioDAO = new FuncionarioDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obter a contagem de ônibus em manutenção e disponíveis
            int onibusManutencaoCount = funcionarioDAO.countOnibusManutencao();
            int onibusFuncionandoCount = funcionarioDAO.countOnibusFuncionando();
    
            // Definir os atributos para serem usados na página JSP
            request.setAttribute("onibusManutencaoCount", onibusManutencaoCount);
            request.setAttribute("onibusFuncionandoCount", onibusFuncionandoCount);
    
            // Redireciona para a página funcionario.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/funcionario.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            response.sendRedirect("/erro.jsp");
        }
    }
} // fim servlet
