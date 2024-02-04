package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
            //int onibusManutencaoCount = funcionarioDAO.countOnibusManutencao();
            //int onibusFuncionandoCount = funcionarioDAO.countOnibusFuncionando();

            int onibusManutencaoCount = 5;
            int onibusFuncionandoCount = 10;
            String s = Integer.toString(onibusManutencaoCount)+"-"+Integer.toString(onibusFuncionandoCount);

            PrintWriter out = response.getWriter();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            out.println(s);

        } catch (IOException e) {
            response.sendRedirect("/erro.jsp");
        }
    }
} // fim servlet
