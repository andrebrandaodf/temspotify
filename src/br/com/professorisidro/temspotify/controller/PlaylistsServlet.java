package br.com.professorisidro.temspotify.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class PlaylistsServlet
 */
@WebServlet("/playlists")
public class PlaylistsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String paginaDestino = "/myplaylists.jsp";
		
		try {
			Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
			if(usuario != null) {
				//Ta Logado
				if(usuario.getPlaylists() == null) { //Nao tem playlist?
					
					//recupero do banco
					DataSource dataSource = new DataSource();
					PlayListDAO plDAO = new PlayListDAO(dataSource);
					List<Object> lista = plDAO.read(usuario.getId());
					dataSource.getConnection().close();
					
					//Recuperei valores
					
					//Vou passar por cada elemento do que veio do BD e vou referenciar o usuario
					if(lista != null) {
						ArrayList<PlayList> myPlaylists = new ArrayList<PlayList>();
						for(Object  o: lista) {
							PlayList novaP1 = (PlayList)o;
							novaP1.setUsuario(usuario);
							myPlaylists.add(novaP1);
						}
						usuario.setPlaylists(myPlaylists);
					
					}
				}
				request.getSession().setAttribute("Usuario", usuario);
				paginaDestino = "/myplaylists.jsp";
			}
		}
		catch(Exception ex){
			System.out.println("Ero ao recuperar Playlists "+ex.getMessage());
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		dispatcher.forward(request, response);
		
	}

}
