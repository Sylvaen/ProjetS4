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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom_partie = request.getParameter("nom");
		String nom_joueur1 = request.getParameter("nom_joueur1");
		Plateau p = new Plateau ();
		p.setNom(nom_partie);
		String s = p.toString();
		savePlateau(nom_partie, nom_joueur1, s);		
		PrintWriter out = response.getWriter();
		out.println(nom_partie);
		HttpSession session = request.getSession();
		String plat = p.afficherPlateau();
		session.setAttribute("plateau",plat);
		this.getServletContext().getRequestDispatcher(VIEW)
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	public void savePlateau (String nom_partie, String nom_joueur1, String s) {
		
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
			
		} catch (SQLException e1) {
	
			System.out.println("Ajout plateau PB");
			e1.printStackTrace();
		}

	}	
}