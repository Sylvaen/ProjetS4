package servlet;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.ConnexionMYSQL;
import form.ConnexionForm;

/**
 * Identifie et autorise / refuse la connexion
 * 
 * @author dumetza
 *
 */

@WebServlet("/connexion")
public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	private static final String ATT_SESSION_USER = "user";
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ConnexionForm form = new ConnexionForm();

		/*
		 * Appel au traitement et à la validation de la requête, et récupération
		 * du bean en résultant
		 */
		HttpSession session = request.getSession();
		User utilisateur = form.connecterUtilisateur(request);
		/* Si il n'y a pas eu d'erreur */
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, utilisateur);
			// envoyerEmail();
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
		}
		/*
		 * Stockage du formulaire et du bean dans l'objet request
		 */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);
		if (utilisateur != null) {
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/index.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("errLog", form.getErreurs());
			request.setAttribute("result", form.getResultat());
			session.setAttribute(ATT_SESSION_USER, null);
			request.setAttribute(ATT_USER, null);
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp")
					.forward(request, response);
		}
	}
}
