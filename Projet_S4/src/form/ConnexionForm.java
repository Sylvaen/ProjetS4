package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.User;
import dao.DAOUser;

public class ConnexionForm {
	private static final String CHAMP_PASS = "mdp";
	private static final String CHAMP_NOM = "pseudo";
	private static final String ALGO_CHIFFREMENT = "SHA-256";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return this.resultat;
	}

	public Map<String, String> getErreurs() {
		return this.erreurs;
	}

	public User connecterUtilisateur(HttpServletRequest request) {
		erreurs.clear();
		String nom = getValeurChamp(request, CHAMP_NOM);
		String motDePasse = getValeurChamp(request, CHAMP_PASS);
		User utili = new User();

		/* Validation du champ email. */
		try {
			validationPseudo(nom);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		utili.setName(nom);
		/* Validation du champ mot de passe. */
		try {
			validationMotDePasse(motDePasse);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
		passwordEncryptor.setPlainDigest(false);
		utili.setPwd(motDePasse);
		/* On verifie que l'utilisateur et le mdp sont exacts */
		User user = DAOUser.getUserByUsername(utili.getName());
		if (user != null) {
			String mdp1 = utili.getPwd();
			String mdp2 = user.getPwd();
			if (!passwordEncryptor.checkPassword(mdp1, mdp2)) {
				System.out.println("compte ou mdp inv: " + mdp1);
				setErreur("noLog", "Compte ou mot de passe invalide");
			} else {
				if (user.getValide() == 0) {
					setErreur("noLog", "Merci de valider l'email");
					System.out.println("novalid");

				}
			}
		} else {
			System.out.println("compte ou mdp inv2");
			setErreur("noLog", "Compte ou mot de passe invalide");
		}

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()) {
			resultat = "Succès de la connexion.";
			System.out.println("connexion ok");

			return user;
		} else {
			System.out.println("connexion nok");

			resultat = "Échec de la connexion.";
			return null;
		}
	}

	private void validationPseudo(String pseudo) throws FormValidationException {
		if (pseudo != null) {
			if (pseudo.length() < 3) {
				throw new FormValidationException(
						"Le pseudo doit contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci de saisir un pseudo valide.");
		}
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationMotDePasse(String motDePasse)
			throws FormValidationException {
		if (motDePasse != null) {
			if (motDePasse.length() < 3) {
				throw new FormValidationException(
						"Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci de saisir votre mot de passe.");
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getValeurChamp(HttpServletRequest request,
			String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}
