package beans;

/**
 * Bean : reprensente un JOUEUR (un user peut etre associe a plusieurs joueurs
 * s'il effectue plusieurs parties en mm temps)
 * 
 * @author dumetza
 *
 */

public class Joueur {

	String pseudo;
	int points;
	char[] lettres;

	/**
	 * Initalise un joueur
	 */
	public void initialise() {
		this.points = 0;
		initialiseLettres();
		afficheLettres();
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public char[] getLettres() {
		return lettres;
	}

	public void setLettres(char[] lettres) {
		this.lettres = lettres;
	}

	/**
	 * Tire 7 lettres aleatoires et les mets dans le tableau 'lettres'
	 */
	public void initialiseLettres() {
		this.lettres = new char[7];
		for (int i = 0; i < 7; i++) {
			int a = (int) (Math.random() * (122 - 97)) + 97;
			char c = (char) a;
			lettres[i] = c;
		}

	}

	/**
	 * Affiche les lettres du joueur dans la console
	 */

	public void afficheLettres() {
		for (int i = 0; i < 7; i++) {
			System.out.println("Lettre [" + i + "] = " + lettres[i]);
		}
	}

	/**
	 * Convertir 'lettres' en string pour la bdd
	 */

	public String convertLettres() {
		String res = "";

		for (int i = 0; i < 7; i++) {
			res += lettres[i];
		}
		return res;

	}

	/**
	 * Pioche une lettre et la retourne
	 */
	public char pioche() {
		int a = (int) (Math.random() * (122 - 97)) + 97;
		char c = (char) a;
		return c;
	}
	
	/**
	 * Se defausser d'une lettre quand on la joue et pioche ensuite
	 */
	
	public void defausser () {
		
		for (int i = 1; i < 6; i++) {
			lettres[i-1] = lettres[i];
		}
		
		lettres[6] = this.pioche();
		
	}

}
