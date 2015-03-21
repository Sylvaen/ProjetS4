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
	 * Creer une partie : on recoit une requete GET de la part de Accueil.jsp
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("nom");
		User user = (User) session.getAttribute("user");
		DAOParties.createPartie(user, name);
		List<Parties> available = new ArrayList<Parties>();
		List<Parties> partieEnCours = new ArrayList<Parties>();
		List<Parties> parties = DAOParties.getParties();
		for(Parties p: parties){
			if(p.getIdj1() == user.getId() || p.getIdj2() == user.getId()){
				partieEnCours.add(p);
			}
			else{
				available.add(p);
			}
		}
		
		session.setAttribute("listPartieEnCours", partieEnCours);
		session.setAttribute("listPartiesDispo", available);
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
			String nom_joueur1, String s) {

		Parties p = new Parties();
		p.initialiseLettres(p.getLettresj1());

		// on range ses lettres dans la session

		String lettres_j1 = p.convertLettres(p.getLettresj1());
		HttpSession session = request.getSession();
		session.setAttribute("lettresj1", lettres_j1);

		// connection + requete

	}
}
