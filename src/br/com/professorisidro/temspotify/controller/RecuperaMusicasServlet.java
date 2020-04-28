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
import br.com.professorisidro.temspotify.dao.MusicaDAO;
import br.com.professorisidro.temspotify.model.Usuario;

/**
 * Servlet implementation class RecuperaMusicasServlet
 */
@WebServlet("/recuperamusicas")
public class RecuperaMusicasServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String  paginaDestino = "/error.jsp";
		
		try {
			
			Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
			if(usuario == null) {
				request.setAttribute("erroSTR", "Usuario nao conectado!");
			}
			else {
				DataSource dataSource = new DataSource();
				MusicaDAO mdao = new MusicaDAO(dataSource);
				List<Object> lista = mdao.read(null);
				
				if(lista == null) {
					request.setAttribute("erroSTR", "Erro ao conectar musicas do Banco de Dados");
				}
				else {
					String idPlaylist = request.getParameter("idplaylist");
					request.setAttribute("idPlaylist", idPlaylist);
					request.setAttribute("ListaMusicas", lista);
					paginaDestino = "/minhasmusicas.jsp";
				}
				dataSource.getConnection().close();
			}
			
		} 
		catch (Exception ex) {
			System.out.println("Erro o montar pagina de musicas "+ex.getMessage());
			request.setAttribute("erroSTR", "Erro ao montar pagina de musicas ");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		dispatcher.forward(request, response);
	}

}
