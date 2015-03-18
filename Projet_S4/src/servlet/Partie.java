package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Joueur;
import beans.Plateau;

/**
 * Servlet implementation class Partie
 */
@WebServlet("/Partie")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom_partie = request.getParameter("nom");
		String nom_joueur1 = request.getParameter("nom_joueur1");
		Plateau p = new Plateau ();
		p.setNom(nom_partie);
		String s = p.toString();
		savePlateau(request, nom_partie, nom_joueur1, s);		
		PrintWriter out = response.getWriter();
		out.println(nom_partie);
		HttpSession session = request.getSession();
		String plat = p.afficherPlateau();
		session.setAttribute("plateau",plat);
		session.setAttribute("nom", nom_partie);;
		this.getServletContext().getRequestDispatcher(VIEW)
		.forward(request, response);
	}


	/**
	 * On sauvegarde la partie dans la BDD
	 * On insere dans la table partie le NOM de la partie, le NOM du joueur 1, le PLATEAU
	 * @param request
	 * @param nom_partie
	 * @param nom_joueur1
	 * @param s
	 */
	public void savePlateau (HttpServletRequest request, String nom_partie, String nom_joueur1, String s) {
		
		// on initialise le joueur 1
		Joueur joueur1 = new Joueur();
		joueur1.setPseudo(nom_joueur1);
		
		// on range ses lettres dans la session
		joueur1.initialiseLettres();
		String lettres_j1 = joueur1.convertLettres();
		HttpSession session = request.getSession();
		session.setAttribute("lettresj1", lettres_j1);
		
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


		String requete = "insert into parties (nom, joueur1,  plateau)"
				+ "values('" + nom_partie + "','" + nom_joueur1 + "','" + s + "');";
		System.out.println("Ajout plateau = " + requete);
		
	
		// connection + requete
		try {
			Connection con = DriverManager.getConnection(url,user,password);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(requete);
			
			String ajout_lettresj1 = "UPDATE parties SET lettres_j1 = '" +  lettres_j1  + "' WHERE nom='" + nom_partie +
					"' and joueur1='" + nom_joueur1 + "';";
			System.out.println("Rajout J2 = " + ajout_lettresj1);
			stmt.executeUpdate(ajout_lettresj1);
			
			
		} catch (SQLException e1) {
	
			System.out.println("Ajout plateau PB, ou ajout LettreJ1 PB");
			e1.printStackTrace();
		}
		
	

	}	
}
