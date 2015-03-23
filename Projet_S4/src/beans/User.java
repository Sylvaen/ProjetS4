package beans;

import java.io.Serializable;

import game.Alphabet;

/**
 * Definit un utilisateur avec les caracteristiques principales (pseudo, id,
 * mdp, ...)
 * 
 * @author amaury
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int id;
	private String pwd;
	private int points;
	private int valide = 1;
	private String email;
	private String key;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValide() {
		return valide;
	}

	public void setValide(int valide) {
		this.valide = valide;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Ajoute les points selon les valeurs des lettres du mot S
	 * 
	 * @param s
	 */
	public void ajouterPoints(String s, Alphabet al) {

		int points_rapportes = 0;

		for (int i = 0; i < s.length(); i++) {
			char caractere = s.charAt(i);
			String cast_caractere = caractere + "";
			int valeur_caractere = al.getAlphabet().get(cast_caractere);
			points_rapportes += valeur_caractere;
		}

		int points_actuels = this.getPoints();
		this.setPoints(points_actuels + points_rapportes);
	}

	public void setPseudo(String nom_joueur1) {
		this.name = nom_joueur1;

	}

}
