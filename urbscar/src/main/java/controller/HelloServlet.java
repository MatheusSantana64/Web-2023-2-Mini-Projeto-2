package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Conexao;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private String mensagem = "conexao não realizada";

    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Connection conn = (new Conexao()).getConnection();
			mensagem = "conexao realizada com sucesso";
            conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter response_writer = response.getWriter();
		response_writer.print(this.buildPage(mensagem)); // envia HTML de resposta
		response_writer.close();
	}

	private String buildPage(String str) {
		String page;

		page = "<!doctype html>"
				+ "<html lang=\"pt-br\">" + "<head>" + "<meta charset=\"UTF-8\">" + "<title>Testa Conexao</title>"
				+ "</head>" + "<body>" + "<h1>Testa conexao</h1>" + "<h2>Conexao: " + str + "!</h2>"
				+ "</body>" + "</html>";

		return page;
	}

}
