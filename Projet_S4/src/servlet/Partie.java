package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Parties;
import beans.Saccoche;
import beans.User;
import dao.DAOParties;
import dao.DAOSaccoche;
import dao.DAOUser;

/**
 * Servlet implementation class Partie
 */
@WebServlet("/createPartie")
public class Partie extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/Partie.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Partie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creer une partie : on recoit une requete POST de la part de mainpage.hsp
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// On met toutes les infos de la partie dans la session
		HttpSession session = request.getSession();
		String name = request.getParameter("nom");
		User user = (User) session.getAttribute("user");
		Parties p = DAOParties.createPartie(user, name);
		Saccoche sac = p.getSaccoche();
		DAOSaccoche.update(sac);
		
		// Parties p = DAOParties.getPartiesByName(name);
		p.setUser1(DAOUser.getUserById(p.getIdj1()));
		p.setUser2(DAOUser.getUserById(p.getIdj2()));
		String plateau = p.getPlateauString();
		System.out.println("Partie :  plateau = " + plateau);
		session.setAttribute("nom", name);
		session.setAttribute("lettresj1_str", p.getLettresj1_str());
		session.setAttribute("user", user);
		session.setAttribute("plateau", plateau);
		request.setAttribute("partie", p);
		request.setAttribute("lettresJ1BIEN", p.afficheBienLettresJ1());

		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

	/**
	 * On sauvegarde la partie dans la BDD On insere dans la table partie le NOM
	 * de la partie, le NOM du joueur 1, le PLATEAU
	 * 
	 * @param request
	 * @param nom_partie
	 * @param nom_joueur1
	 * @param s
	 */
	public void savePlateau(HttpServletRequest request, String nom_partie,
			String nom_joueur1, String s, User u) {

		Parties p = new Parties(u);

		DAOParties.update(p);

		// on range ses lettres dans la session

		String lettres_j1 = p.convertLettres(p.getLettresj1());
		HttpSession session = request.getSession();
		session.setAttribute("lettresj1", lettres_j1);

		// connection + requete

	}
}
