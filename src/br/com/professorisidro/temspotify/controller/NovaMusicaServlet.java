package br.com.professorisidro.temspotify.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NovaMusicaServlet
 */
@WebServlet("/novamusica")
public class NovaMusicaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String paginaDestino = "/erro.jsp";
		
		if(request.getSession().getAttribute("Usuario") != null) {
			paginaDestino = "/novamusica.jsp";
		}
		else {
			request.setAttribute("erroSTR", "Erro: Usuario nao conectado");
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		dispatcher.forward(request, response);
	}

}
