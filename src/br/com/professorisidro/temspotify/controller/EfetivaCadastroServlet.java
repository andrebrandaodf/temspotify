package br.com.professorisidro.temspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Dispatch;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.UsuarioDAO;
import br.com.professorisidro.temspotify.model.Usuario;


@WebServlet("/efetivacadastro")
public class EfetivaCadastroServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//receber dados do formulario
		//criar um objeto usuario com estes dados (mas que ainda nao tem ID)
		//dependendo do resultado, vamos trabalhar em qual pagina retornar
		
		String pagina = "/myaccount.jsp";
		String nome = request.getParameter("txtNome");
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		//instanciar o datasource e o DAO 
		//gravar
		DataSource dataSource = new DataSource();
		UsuarioDAO usuarioDAO = new UsuarioDAO(dataSource);
		usuarioDAO.create(usuario);
		System.out.println(usuario);
		
		try {
			dataSource.getConnection().close();
		}
		catch(Exception ex) {
			System.out.println("Erro ao fechar conexao "+ ex.getMessage());
			request.setAttribute("erroMSG", "Erro ao  criar nova conta de usuario");
			pagina = "/error.jsp";
		}
		
		if(usuario.getId() != 0) request.getSession().setAttribute("Usuario", usuario);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}
