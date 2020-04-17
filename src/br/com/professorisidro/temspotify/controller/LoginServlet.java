package br.com.professorisidro.temspotify.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.UsuarioDAO;
import br.com.professorisidro.temspotify.model.Usuario;


@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		Usuario incompleto = new Usuario();
		incompleto.setEmail(email);
		incompleto.setSenha(senha);
		
		String pagina = "/erro.jsp";
		
		try {
		
			DataSource ds = new DataSource();
			UsuarioDAO userDAO = new UsuarioDAO(ds);
			List<Object> res = userDAO.read(incompleto);
				if(res != null && res.size() > 0){
					pagina = "/myaccount.jsp";
					request.getSession().setAttribute("Usuario", res.get(0));
					}
				else {
					request.setAttribute("erroSTR", "Usuario / Senha invalidos");
				}
				ds.getConnection().close();
			}
		catch(Exception ex) {
			request.setAttribute("erroSTR", "ERRO ao Recuperar");
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	
	}
}
