package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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



@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	

	private static final String VIEW = "/WEB-INF/index.jsp";
	private static final String VIEW_GOOD = "/WEB-INF/Accueil.jsp";
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		// Transmission de la paire d'objets request/response à notre JSP
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

	/**
	 * Effectue l'inscription
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Connection con = null;
    
        String url = "jdbc:postgresql://psqlserv/n3p1";
        String user = "dumetza";
        String password = "moi";
		
	    
	    // DRIVER
		try {
			   Class.forName("org.postgresql.Driver");
			   System.out.println("Driver O.K.");
		} catch (ClassNotFoundException e2) {
			System.out.println("driver KO");
			e2.printStackTrace();
		}
		
		// connection
		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e1) {
	
			System.out.println("connection KO");
			e1.printStackTrace();
		}
		
		//requete
		
		boolean valide = true;
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter(
				"mdp");
		String requete = "Select * from users where pseudo = '" + pseudo + "';";
		ResultSet resultats = null;
		
		try {
			Statement stmt = con.createStatement();
			resultats = stmt.executeQuery(requete);
			
			
			if (resultats.next()) {
				valide = false;
			}
			
			else {
				String ajout = "Insert into users (pseudo,mdp)"
						+ "values ('" + pseudo + "','" + mdp + "');";
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