package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Parties;
import beans.User;
import dao.DAOParties;
import dao.DAOPlateau;
import dao.DAOUser;

/**
 * Servlet implementation class Partie
 */
@WebServlet("/jouerPartie")
public class JouerPartie extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/Partie.jsp";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String nom_partie = request.getParameter("jouer");
		User u = (User) session.getAttribute("user");
		Parties parties = DAOParties.getPartiesByName(nom_partie);
		String plateau = parties.getPlateauString();
		char[] lettres = new char[7];
		if (u.getId() == parties.getIdj1()) {
			lettres = parties.getLettresj1();
			session.setAttribute("lettresj1", lettres);
		}
		if (u.getId() == parties.getIdj2()) {
			lettres = parties.getLettresj2();
			session.setAttribute("lettresj2", lettres);
		}

		session.setAttribute("user", u);
		session.setAttribute("partie", parties);
		session.setAttribute("plateau", plateau);

		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

}