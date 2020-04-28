package br.com.professorisidro.temspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.professorisidro.temspotify.model.PlayList;
import br.com.professorisidro.temspotify.model.Usuario;

/**
 * Servlet implementation class PlayerServlet
 */
@WebServlet("/player")
public class PlayerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String paginaDestino = "/error.jsp";
		Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
		
		if(usuario != null) {
			PlayList playlist = (PlayList)request.getSession().getAttribute("PlayList");
			if(playlist != null) {
				paginaDestino = "/player.jsp";
			}
			else {
				request.setAttribute("erroSTR", "Playlist nao encontrada!");
			}
		}
		else {
			request.setAttribute("erroSTR", "Erro! Usuario nao conectado!");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		dispatcher.forward(request, response);
	}

}
