package beans;



/**
 * Bean : represente une partie simple
 * @author dumetza
 *
 */
public class Parties {

	private String nom;
	private int idj1;
	private int idj2;
	private User User1;
	private User User2;
	private int pj1;
	private int pj2; 
	private String plateauString;
	private Plateau p;
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
	 * chaque User possede 7 lettres
	 * et 0 point au debut
	 */
	public Parties () {
		this.User1 = new User ();
		this.User2 = new User ();
		this.User1.setPoints(0);
		this.User2.setPoints(0);

	}
	
	public Plateau getP() {
		return p;
	}

	public void setP(Plateau p) {
		this.p = p;
	}

	public Parties (String j1, String nom) {
		this.j1 = j1;
		this.nom = nom;
	}

	public User getUser1() {
		return User1;
	}
	public void setUser1(User User1) {
		this.User1 = User1;
	}
	public User getUser2() {
		return User2;
	}
	public void setUser2(User User2) {
		this.User2 = User2;
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

	public int getIdj1() {
		return idj1;
	}

	public void setIdj1(int idj1) {
		this.idj1 = idj1;
	}

	public int getIdj2() {
		return idj2;
	}

	public void setIdj2(int idj2) {
		this.idj2 = idj2;
	}

	public String getPlateauString() {
		return plateauString;
	}

	public void setPlateauString(String plateauString) {
		this.plateauString = plateauString;
		this.p = new Plateau();
		p.initialisePlateau();
		p.toPlateau(this.plateauString);
	}

	public int getPj1() {
		return pj1;
	}

	public void setPj1(int pj1) {
		this.pj1 = pj1;
	}

	public int getPj2() {
		return pj2;
	}

	public void setPj2(int pj2) {
		this.pj2 = pj2;
	}
	
	
	
}
