package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

/**
 * Servlet implementation class JouerPartie
 */
@WebServlet("/RejoindrePartie")
public class RejoindrePartie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/PartieJ2.jsp";
	private ConnexionMYSQL con_mysql = null;
	private Connection con = null;

	public RejoindrePartie() {
		super();
	}

	/**
	 * Rejoindre une partie : on recupere le nom de la partie, le nom du joueur
	 * qui l'a cree, le plateau
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response, Parties partie) throws ServletException, IOException {

		Plateau p = new Plateau();
		HttpSession s = request.getSession();
		String nom_partie = request.getParameter("partie");
		String nom_joueur1 = request.getParameter("joueur1");
		String request2 = "select plateau, nom, joueur1 from parties where nom='"
				+ nom_partie + "' and joueur1='" + nom_joueur1 + "';";
		System.out
				.println("Recherche partie via pseudo, requete = " + request2);

		con = con_mysql.getConnection();

		String pseudo_joueur2 = s.getAttribute("pseudo").toString();
		String nom = "";

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");
		} catch (ClassNotFoundException e2) {
			System.out.println("driver KO");
			e2.printStackTrace();

			con = con_mysql.getConnexion();

			try {

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(request2);

				// On recupere le plateau et le nom de la partie et on les mets
				// dans la session
				while (rs.next()) {
					String plateau = rs.getString("plateau");
					p.toPlateau(plateau); // on transforme la chaine en plateau
					plateau = p.afficherPlateau();
					nom = rs.getString("nom");
					s.setAttribute("plateau", plateau);
					s.setAttribute("nom", nom);
				}

				// on rajoute le joueur 2 dans la bdd

				String rajout_j2 = "UPDATE parties SET joueur2 = '"
						+ pseudo_joueur2 + "' WHERE nom='" + nom
						+ "' and joueur1='" + nom_joueur1 + "';";
				System.out.println("Rajout J2 = " + rajout_j2);
				stmt.executeUpdate(rajout_j2);

				// on range ses lettres dans la session
				User j2 = new User();
				partie.initialiseLettres(partie.getLettresj2());
				String lettres_j2 = partie.convertLettres(partie.getLettresj2());
				HttpSession session = request.getSession();
				session.setAttribute("lettresj2", lettres_j2);

				// on range ses lettres dans la bdd
				try {

					String ajout_lettresj2 = "UPDATE parties SET lettres_j2 = '"
							+ lettres_j2
							+ "' WHERE nom='"
							+ nom_partie
							+ "' and joueur1='" + nom_joueur1 + "';";
					System.out.println("Rajout J2 = " + ajout_lettresj2);
					stmt.executeUpdate(ajout_lettresj2);

				} catch (SQLException e1) {

					System.out
							.println("Ajout plateau PB, ou ajout LettreJ1 PB");
					e1.printStackTrace();
				}

				// le joueur 2 a bien rejoint la partie, on redirige vers la vue
				System.out.println("rejoindre partie OK");
				this.getServletContext().getRequestDispatcher(VIEW)
						.forward(request, response);

			}

			catch (Exception e) {
				e.printStackTrace();
				System.out.println("rejoindre partie KO");
			}
		}

	}
}
