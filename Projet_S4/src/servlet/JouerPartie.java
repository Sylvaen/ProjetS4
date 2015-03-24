package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Parties;
import beans.User;
import dao.DAOParties;


/**
 * Servlet implementation class Partie
 */
@WebServlet("/jouerPartie")
public class JouerPartie extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/Partie.jsp";

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession http = request.getSession();
		String nom_partie = request.getParameter("jouer");
		User u = (User) http.getAttribute("user");
		Parties parties = DAOParties.getPartiesByName(nom_partie);
		String plateau = parties.getPlateauString();
		
		// Si c'est le joueur 1 de la partie qui la rejoint
		
		if (u.getId() == parties.getIdj1()) {
			
			System.out.println("JouerPartie. Lettres J1 = " + parties.getLettresj1_str());

			
			http.setAttribute("lettresj1_str", parties.getLettresj1_str());
			http.setAttribute("nom", nom_partie);
			parties.setUser1(u);
			http.setAttribute("lettresJ1BIEN", parties.afficheBienLettresJ1());
			http.setAttribute("partie", parties);
			http.setAttribute("plateau", plateau);
			http.setAttribute("user", u);
			this.getServletContext().getRequestDispatcher(VIEW)
			.forward(request, response);
		}
		
		// Si c'est le joueur 2 de la partie qui la rejoint
		
		if (u.getId() == parties.getIdj2()) {
			parties.setUser2(u);
			http.setAttribute("lettresj2_str", parties.getLettresj2_str());
			http.setAttribute("nom", nom_partie);
			http.setAttribute("lettresJ2BIEN", parties.afficheBienLettresJ2());
			http.setAttribute("partie", parties);
			http.setAttribute("plateau", plateau);
			http.setAttribute("user", u);
			this.getServletContext().getRequestDispatcher(VIEW)
			.forward(request, response);
		}

	}

}