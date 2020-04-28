package br.com.professorisidro.temspotify.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.professorisidro.temspotify.dao.DataSource;
import br.com.professorisidro.temspotify.dao.PlayListDAO;

/**
 * Servlet implementation class IncluirNaPlaylistServlet
 */
@WebServlet("/incluirnaplaylist")
public class IncluirNaPlaylistServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String paginaResultado = "/result.jsp";
		DataSource dataSource = null;
		
		try {
			
			int idPlaylist = Integer.parseInt(request.getParameter("idplaylist"));
			int idMusica   = Integer.parseInt(request.getParameter("idMusica"));
			dataSource = new DataSource();
			PlayListDAO plDAO = new PlayListDAO(dataSource);
			
			if(plDAO.createMusicaPlaylist(idPlaylist, idMusica)) {
				request.setAttribute("strRESULT", "OK");
			}
			dataSource.getConnection().close();

		} 
		catch (Exception ex) {
			if(dataSource != null) {
				
				try {
					dataSource.getConnection().close();
				} 
				catch (SQLException ex2) {
					System.out.println("Nao fechei a conexao");
				}
			
			}
			System.out.println("Erro ao inserir "+ex.getMessage());
			request.setAttribute("strRESULT", "Erro ao inserir Musica na Playlist");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaResultado);
		dispatcher.forward(request, response);
	}

}
