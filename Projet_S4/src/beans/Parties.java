package beans;

/**
 * Bean : represente une partie simple
 * @author dumetza
 *
 */
public class Parties {

	private String nom;
	private Joueur joueur1;
	private Joueur joueur2;
	
	private String j1;
	
	public String getJ1() {
		return j1;
	}

	public void setJ1(String j1) {
		this.j1 = j1;
	}

	public String getJ2() {
		return j2;
	}

	public void setJ2(String j2) {
		this.j2 = j2;
	}

	private String j2;
	
	
	/**
	 * Creation d'une partie
	 * chaque joueur possede 7 lettres
	 * et 0 point au debut
	 */
	public Parties () {
		this.joueur1 = new Joueur ();
		this.joueur2 = new Joueur ();
		this.joueur1.setPoints(0);
		this.joueur2.setPoints(0);

	}
	
	public Parties (String j1, String nom) {
		this.j1 = j1;
		this.nom = nom;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}
	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}
	public Joueur getJoueur2() {
		return joueur2;
	}
	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	private int id;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
