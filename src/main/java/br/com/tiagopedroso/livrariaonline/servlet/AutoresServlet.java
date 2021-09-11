package br.com.tiagopedroso.livrariaonline.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tiagopedroso.livrariaonline.dao.AutorDao;
import br.com.tiagopedroso.livrariaonline.factory.ConnectionFactory;
import br.com.tiagopedroso.livrariaonline.model.Autor;

import static br.com.tiagopedroso.livrariaonline.config.ConfigGeral.*;

@WebServlet("/autores")
public class AutoresServlet extends HttpServlet {

	private static final long serialVersionUID = -8203776571981374141L;

	private AutorDao dao;

	public AutoresServlet() {
		dao = new AutorDao(ConnectionFactory.get());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("autores", dao.listar());
		req.getRequestDispatcher(JSP_BASE_URL + "autores.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String nome = req.getParameter("nome");
		final String email = req.getParameter("email"); 
		final LocalDate dataNascimento = LocalDate.parse(req.getParameter("dataNascimento"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Autor autor = Autor
				.builder()
				.nome(nome)
				.email(email)
				.dataNascimento(dataNascimento)
				.build()
		;
		
		dao.salvar(autor);
		
		resp.sendRedirect("autores");
	}

}
