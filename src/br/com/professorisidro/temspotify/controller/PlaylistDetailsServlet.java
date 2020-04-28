package br.com.professorisidro.temspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.PlayListDAO;
import br.com.professorisidro.temspotify.model.PlayList;

/**
 * Servlet implementation class PlaylistDetailsServlet
 */
@WebServlet("/playlistdetails")
public class PlaylistDetailsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String paginaDestino = "/error.jsp";
		
		if(request.getSession().getAttribute("Usuario") != null) {
			try {
			
				DataSource dataSource = new DataSource();
				PlayListDAO plDAO = new PlayListDAO(dataSource);
				System.out.println("Recebido id = "+ request.getParameter("id"));
				int id = Integer.parseInt(request.getParameter("id"));
				PlayList playlist = plDAO.readPlaylistDetailsById(id);
				
				if(playlist != null) {
					
					request.getSession().setAttribute("PlayList", playlist);
					paginaDestino = "/playlistdetails.jsp";
				}
				else {
					request.setAttribute("erroSTR", "Erro ao recuperar Playlist");
				}
			
			} catch (Exception ex) {
				request.setAttribute("erroSTR", "Erro Inesperado!");
				ex.printStackTrace();
			}
		}
		else {
			request.setAttribute("erroSTR", "Erro, voce nao esta conectado ");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		dispatcher.forward(request, response);
	}
}
