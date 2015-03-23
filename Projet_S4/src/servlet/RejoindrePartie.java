package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Parties;
import beans.Plateau;
import beans.User;
import dao.ConnexionMYSQL;
import dao.DAOParties;

/**
 * Servlet implementation class JouerPartie
 */
@WebServlet("/RejoindrePartie")
public class RejoindrePartie extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/Partie.jsp";


	public RejoindrePartie() {
		super();
	}

	/**
	 * Rejoindre une partie : on recupere le nom de la partie, le nom du joueur
	 * qui l'a cree, le plateau
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession http = request.getSession();
		User u = (User) http.getAttribute("user");
		Plateau p = new Plateau();
		HttpSession s = request.getSession();
		String nom_partie = request.getParameter("partie");
		String nom_joueur1 = request.getParameter("joueur1");
		Parties pa = DAOParties.rejoindrePartie(u, nom_partie);
		
		http.setAttribute("user2", u);
		http.setAttribute("lettresj2_str", pa.getLettresj2_str());
		String plateau = pa.getPlateauString();
		http.setAttribute("plateau", plateau);
		String nom = pa.getNom();
		http.setAttribute("nom", nom);
		
		this.getServletContext().getRequestDispatcher(VIEW)
		.forward(request, response);
	}
}
