package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConnexionMYSQL;
import beans.Joueur;
import beans.Parties;

/**
 * Identifie et autorise / refuse la connexion
 * 
 * @author dumetza
 *
 */

@WebServlet("/connexion")
public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW_NOT_GOOD = "/WEB-INF/index.jsp";
	private ConnexionMYSQL con_mysql = new ConnexionMYSQL();

	public Connexion() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("logErr", "T'as essayé de me brain là ?");
		this.getServletContext().getRequestDispatcher(VIEW_NOT_GOOD)
		.forward(req, resp);
	}

	/**
	 * Connexion
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession s = request.getSession();

		// requete

		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		String requete = "Select * from users where pseudo = '" + pseudo
				+ "' and mdp = '" + mdp + "';";
		ResultSet resultats = null;

		try {
			Connection c = con_mysql.getConnection();
			if (c != null) {
				Statement stmt = c.createStatement();
				resultats = stmt.executeQuery(requete);

				// La connexion a fonctionne
				if (resultats.next()) {
					HttpSession session = request.getSession();
					session.setAttribute("user", pseudo);
					rechercheParties(request, response);
					response.sendRedirect("index");

				}

				else {
					request.setAttribute("logErr",
							"Pseudo ou mot de passe incorrect");
					response.sendRedirect("index");

				}

			} else {
				System.out.println("La connexion n'a pas pu etre etablie1.");

				request.setAttribute("logErr", "Un problème est survenu");
				this.getServletContext().getRequestDispatcher(VIEW_NOT_GOOD)
						.forward(request, response);
			}
		} catch (SQLException e) {
			System.out.println("La connexion n'a pas pu etre etablie2.");
			request.setAttribute("logErr", "Un problème est survenu");
			this.getServletContext().getRequestDispatcher(VIEW_NOT_GOOD)
					.forward(request, response);
		}

	}

	/**
	 * Affiche toutes les parties en cours que l'utilisateur peut rejoindre
	 * 
	 * @param request
	 * @param response
	 */
	public void rechercheParties(HttpServletRequest request,
			HttpServletResponse response) {

		ArrayList<Parties> parties = new ArrayList<Parties>();

		String requete = "select nom, joueur1 from parties;";

		try {

			Statement stmt = con_mysql.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(requete);

			while (rs.next()) {

				Parties part = new Parties();
				Joueur joueur1 = new Joueur();
				part.setJoueur1(joueur1);
				String nom = rs.getString("nom");
				String j = rs.getString("joueur1");
				part.getJoueur1().setPseudo(j);
				part.setNom(nom);
				parties.add(part);

			}

			System.out.println("Il y a : " + parties.size() + " parties");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		HttpSession h = request.getSession();
		h.setAttribute("parties", parties);

	}
}
