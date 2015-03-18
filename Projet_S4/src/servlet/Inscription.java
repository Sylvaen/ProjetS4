package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet servant a inscrire un user dans la BDD (table users) Redirections : -
 * index.jsp si echec - Accueil.jsp si succes
 * 
 * @author dumetza
 *
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {

	private static final String VIEW = "/WEB-INF/index.jsp";
	private static final String VIEW_GOOD = "/WEB-INF/Accueil.jsp";

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

	/**
	 * Effectue l'inscription
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		String url = "jdbc:postgresql://psqlserv/n3p1";
		String user = "dumetza";
		String password = "moi";

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.out.println("driver KO");
			e.printStackTrace();
			System.out.println("connection KO");
			e.printStackTrace();
		}

		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		String requete = "Select * from users where pseudo = '" + pseudo + "';";
		ResultSet resultats = null;

		try {
			Statement stmt = con.createStatement();
			resultats = stmt.executeQuery(requete);

			if (!resultats.next()) {
				String ajout = "Insert into users (pseudo,mdp)" + "values ('"
						+ pseudo + "','" + mdp + "');";
				System.out.println("Ajout = " + ajout);
				stmt.executeUpdate(ajout);
				System.out.println("Ajout OK");

				HttpSession session = request.getSession();
				session.setAttribute("pseudo", pseudo);

				this.getServletContext().getRequestDispatcher(VIEW_GOOD)
						.forward(request, response);

			}
		} catch (SQLException e) {
			System.out.println("L'inscription a lamentablement echoue.");
		}

	}
}
