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

import dao.ConnexionMYSQL;

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
	private ConnexionMYSQL con_mysql = null;
	private Connection con = null;

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


		


		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		String requete = "Select * from users where pseudo = '" + pseudo + "';";
		ResultSet resultats = null;

		try {
			Statement stmt = con_mysql.getConnection().createStatement();
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
