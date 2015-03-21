package beans;

import game.Alphabet;

public class User {

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
	 * @param s
	 */
	public void ajouterPoints (String s) {

		int points = 0;
		System.out.println("la");
		for (int i=0; i < s.length(); i ++) {
			char c = s.charAt(i);
			String cs = c + "";
			int iz =  (int) Alphabet.getAlphabet().get(cs);
			points += iz;
		}
		
		int points_actuels = this.getPoints();
		this.setPoints(points_actuels + points);
	}
	

	public void setPseudo(String nom_joueur1) {
		this.name = nom_joueur1;
		
	}
	

	

	
}
