package br.com.professorisidro.temspotify.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.PlayListDAO;
import br.com.professorisidro.temspotify.model.PlayList;
import br.com.professorisidro.temspotify.model.Usuario;

/**
 * Servlet implementation class EfetivaPlaylistsServlet
 */
@WebServlet("/efetivaplaylist")
public class EfetivaPlaylistServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String paginaDestino = "/index.html";
		
		try {
			Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
			if(usuario != null) {
				String titulo = request.getParameter("txtNomePlaylist");
				PlayList  p = new PlayList();
				p.setTitulo(titulo);
				p.setUsuario(usuario);
				
				//Vou gravar no banco
				DataSource dataSource = new DataSource();
				PlayListDAO plDao = new PlayListDAO(dataSource);
				plDao.create(p);
				dataSource.getConnection().close();
				if(usuario.getPlaylists() == null) {
					usuario.setPlaylists(new ArrayList<PlayList>());
				}
				usuario.getPlaylists().add(p);
				request.getSession().setAttribute("Usuario", usuario);
				paginaDestino = "/myplaylists.jsp";
			}
		} 
		catch (Exception ex) {
			System.out.println("Erro ao cadastrar Playlist "+ex.getMessage());
			request.setAttribute("erroSTR", "Erro grave ao criar PlayList");
			paginaDestino = "/error.jsp";
		}
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		dispatcher.forward(request, response);
	}

}
