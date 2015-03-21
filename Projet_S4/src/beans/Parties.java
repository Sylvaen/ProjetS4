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
	private char [] lettresj1;
	private char [] lettresj2;
	
	
	public char[] getLettresj1() {
		return lettresj1;
	}

	public void setLettresj1(char[] lettresj1) {
		this.lettresj1 = lettresj1;
	}

	public char[] getLettresj2() {
		return lettresj2;
	}

	public void setLettresj2(char[] lettresj2) {
		this.lettresj2 = lettresj2;
	}

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
	
	
	/**
	 * Affiche les lettres du joueur dans la console
	 */
	public static  void afficheLettres(char [] lettres) {
		for (int i = 0; i < 7; i++) {
			System.out.println("Lettre [" + i + "] = " + lettres[i]);
		}
	}
	

	
	/**
	 * Se defausser d'une lettre quand on la joue et pioche ensuite
	 */
	
	public void defausser (char  [] lettres) {
		
		for (int i = 1; i < 6; i++) {
			lettres[i-1] = lettres[i];
		}
		
		lettres[6] = pioche();
		
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
	 * Tire 7 lettres aleatoires et les mets dans le tableau 'lettres'
	 */
	public void initialiseLettres(char [] lettres) {
		lettres = new char[7];
		for (int i = 0; i < 7; i++) {
			int a = (int) (Math.random() * (122 - 97)) + 97;
			char c = (char) a;
			lettres[i] = c;
		}

	}
	/**
	 * Convertir un tableau de lettres en STRING pour la BDD
	 * @param tableau_lettres
	 * @return
	 */
	public String convertLettres(char [] tableau_lettres) {
		String res = "";

		for (int i = 0; i < 7; i++) {
			res += tableau_lettres[i];
		}
		return res;

	}

}
