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

import beans.Parties;



@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW_GOOD = "/WEB-INF/Accueil.jsp";
	private static final String VIEW_NOT_GOOD = "/WEB-INF/index.jsp";

    public Connexion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	/**
	 * Connexion
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession s = request.getSession();
		//if(s )
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
		
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter(
				"mdp");
		String requete = "Select * from users where pseudo = '" + pseudo + "' and mdp = '" + mdp + "';";
		ResultSet resultats = null;
		
		try {
			Statement stmt = con.createStatement();
			resultats = stmt.executeQuery(requete);
			
			
			// La connexion a fonctionne
			if (resultats.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("pseudo", pseudo);
				rechercheParties(request, response);
				this.getServletContext().getRequestDispatcher(VIEW_GOOD)
				.forward(request, response);
				
			}
			
			else {
				out.println("Pseudo et / ou password incorrect(s) ");
				this.getServletContext().getRequestDispatcher(VIEW_NOT_GOOD)
				.forward(request, response);
				
			}
			
		} catch (SQLException e) {
			System.out.println("La connexion n'a pas pu etre etablie.");
		}
	
	}

	public void rechercheParties (HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Parties> parties = new ArrayList<Parties>();
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
		
		String requete = "select nom, joueur1 from parties;";
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(requete);
			while (rs.next()) {
				Parties part = new Parties ();
				String nom = rs.getString("nom");
				String j = rs.getString("joueur1");	
				part.setJoueur1(j);
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