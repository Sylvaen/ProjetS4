package servlet;

import game.Alphabet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

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
 * Servlet implementation class ValiderMot
 */
@WebServlet("/validerMot")
public class ValiderMot extends HttpServlet {
	private static final URL CHEMIN_DICTIONNAIRE = ValiderMot.class.getResource("dictionnaire.txt");

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/Partie.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderMot() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String mot = request.getParameter("validerWord");
		if(mot == null){
			System.out.println("wtf");
		}
		User u = (User) session.getAttribute("user");
		String nom = (String) session.getAttribute("nom");
		System.out.println("nom partie = " + nom);
		Parties p = DAOParties.getPartiesByName(nom);
	
		boolean trouve = false;
		try {
			BufferedReader buff = new BufferedReader(new FileReader(CHEMIN_DICTIONNAIRE.getFile()));
			try {
				String line;

				while ((line = buff.readLine()) != null && !trouve) {
					if (line.equals(mot)) {
						trouve = true;
						System.out.println("On a trouve le mot : " + mot);
					}
				}
				
				if (!trouve) {
					System.out.println("on a paz triyve le mot : " + mot);
				}

			} finally {
				buff.close();
			}
		} catch (IOException ioe) {
			System.out.println("Erreur" + ioe.toString());
		}
		
		if (trouve) {
			Integer points = calculerPoints(mot);
			System.out.println("Ca vaut : "+points+" points");
			if (u.getId() == p.getIdj1()) {
				points = p.getPj1() + points;
				System.out.println("Ca vaut pas: "+points+" points");
				p.setPj1(points);
				DAOParties.update(p);
				session.setAttribute("partie", p);
				System.out.println("un truc avant"+p.getPj1());
				this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
				
			}
			
			else {
				points += p.getPj2();
				session.setAttribute("pointsJ2", points);this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
				
				
			}
			
			
		
			
		}
		
	}
	
	public Integer calculerPoints (String mot) {
		Integer res = 0;
		Alphabet al = new Alphabet ();
		al.initialise();
		for (int i = 0; i < mot.length(); i++) {
			char caractere = mot.charAt(i);
			String cast_caractere = caractere + "";
			int valeur_caractere = al.getAlphabet().get(cast_caractere);
			res += valeur_caractere;
		}
		
		return res;
	}
	

}
