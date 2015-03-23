package beans;

import java.io.Serializable;



/**
 * Bean : represente une partie simple
 * @author dumetza
 *
 */
public class Parties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private int idj1;
	private int idj2 = 0;
	private User user1;
	private User user2;
	private int pj1 = 0;
	private int pj2 = 0; 
	private String plateauString;
	private Plateau p;
	private String j1;
	private String lettresj1_str;
	private String lettresj2_str;
	private char [] lettresj1;
	private char [] lettresj2;
	
	
	public String getLettresj1_str() {
		return lettresj1_str;
	}

	public void setLettresj1_str(String lettresj1_str) {
		this.lettresj1_str = lettresj1_str;
	}

	public String getLettresj2_str() {
		return lettresj2_str;
	}

	public void setLettresj2_str(String lettresj2_str) {
		this.lettresj2_str = lettresj2_str;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
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
	public Parties (User u) {
		this.user1 = u;
		this.idj1 = u.getId();
		this.lettresj1 = new char [7];
		this.lettresj2 = new char [7];
	}
	
	public Parties() {
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
		return user1;
	}
	public void setUser1(User User1) {
		this.user1 = User1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User User2) {
		this.user2 = User2;
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
	public  void afficheLettres(char [] lettres) {
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
	public char pioche () {

		int a = (int) (Math.random() * (122 - 97)) + 97;
		char c = (char) a;
		return c;
	}
	
	/**
	 * Tire 7 lettres aleatoires et les mets dans le tableau 'lettres'
	 */
	public char [] initialiseLettresj2() {

		lettresj2 = new char [7];
		for (int i = 0; i < 7; i++) {
			int a = (int) (Math.random() * (122 - 97)) + 97;
			System.out.println(" a = "  + a);
			char c = (char) a;
			System.out.println("c = " + c);
			lettresj2[i] = c;
			
		}
		System.out.println("Lettres, fin : " + lettresj2.toString());
		lettresj2_str = lettresj2.toString();
		return lettresj2;

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

	public char [] initialiseLettresj1() {

		lettresj1 = new char [7];
		for (int i = 0; i < 7; i++) {
			int a = (int) (Math.random() * (122 - 97)) + 97;
			System.out.println(" a = "  + a);
			char c = (char) a;
			System.out.println("c = " + c);
			lettresj1[i] = c;
			
		}
		System.out.println("Lettres, fin : " + lettresj1.toString());
		lettresj1_str = lettresj1.toString();
		return lettresj1;

	}
}
