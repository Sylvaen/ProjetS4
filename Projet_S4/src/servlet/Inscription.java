package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jj.play.ns.nl.captcha.Captcha;
import beans.User;
import form.InscriptionForm;

/**
 * Servlet servant a inscrire un user dans la BDD (table users) Redirections : -
 * index.jsp si echec - Accueil.jsp si succes
 * 
 * @author dumetza
 *
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {

	private static final String VIEW = "/WEB-INF/Goodinsc.jsp";
	private static final String ATT_FORM = "errForm";
	private static final String ATT_USER = "user";
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("WTF");
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}

	/**
	 * Effectue l'inscription
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupération de la valeur renseignée dans le formulaire
		String captchaValue = request.getParameter("captchaValue");

		// Récupération d'un l'objet session
		HttpSession session = request.getSession();

		/*
		 * Récupération de l'objet captcha stocker dans la session par
		 * SimpleCaptchaServlet
		 */
		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);

		// Paramètrage de l'encodage en UTF-8
		request.setCharacterEncoding("UTF-8");
		InscriptionForm form = new InscriptionForm();
		User utilisateur = null;
		// Vérification proprement dite du captcha
		utilisateur = form.inscrireUtilisateur(request);
		System.out.println("WTF2");

		/*
		if (captcha.isCorrect(captchaValue)) {
		} else {
			form.getErreurs().put("capcha", "Capcha incorrect.");
			// ici indiquer sur la page que l'utilisateur a mal renseigné le
			// captcha
		}*/
		/*
		 * Stockage du formulaire et du bean dans l'objet request
		 */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);
		this.getServletContext().getRequestDispatcher(VIEW)
		.forward(request, response);

	}
}
