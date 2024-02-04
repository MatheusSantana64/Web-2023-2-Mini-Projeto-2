package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Conexao;
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

        String saida = "";

        // recupera atributo do ajax
        String op = request.getParameter("op");

        if (op.equals("emManutencao")) {
            saida = consultaEmManutencao();
        }
        else if (op.equals("atrasados")) {
            saida = consultaAtrasados();
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        out.println(saida);

    }

    protected String consultaEmManutencao() {
        int onibusManutencaoCount = 0;
        int onibusFuncionandoCount = 0;

        try {
            Connection conn = (new Conexao()).getConnection();
            onibusManutencaoCount = funcionarioDAO.countOnibusManutencao(conn);
            onibusFuncionandoCount = funcionarioDAO.countOnibusFuncionando(conn);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Integer.toString(onibusManutencaoCount)+"-"+Integer.toString(onibusFuncionandoCount);
    }

    protected String consultaAtrasados() {
        int onibusAtrasadosCount = 0;
        int onibusPontuaisCount = 0;

        try {
            Connection conn = (new Conexao()).getConnection();
            onibusAtrasadosCount = funcionarioDAO.onibusAtrasadosCount(conn);
            onibusPontuaisCount = funcionarioDAO.onibusPontuaisCount(conn);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Integer.toString(onibusAtrasadosCount)+"-"+Integer.toString(onibusPontuaisCount);
    }

} // fim servlet