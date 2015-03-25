package beans;

import game.Alphabet;

import java.io.Serializable;
import java.util.Random;

import dao.DAOParties;
import dao.DAOSaccoche;

/**
 * Bean : represente une partie simple
 * 
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
	private char[] lettresj1;
	private char[] lettresj2;
	private Alphabet alphabet;
	private Saccoche saccoche;
	private String str_saccoche;

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
	 * Creation d'une partie chaque User possede 7 lettres et 0 point au debut
	 */
	public Parties(User u) {
		this.user1 = u;
		this.idj1 = u.getId();
		this.lettresj1 = new char[7];
		this.lettresj2 = new char[7];
		this.alphabet = new Alphabet();
		this.saccoche = new Saccoche();
		this.str_saccoche = this.saccoche.toString();
	}

	public String getStr_saccoche() {
		return str_saccoche;
	}

	public void setStr_saccoche(String str_saccoche) {
		this.str_saccoche = str_saccoche;
	}

	public Parties() {
	}

	public Plateau getP() {
		return p;
	}

	public void setP(Plateau p) {
		this.p = p;
	}

	public Parties(String j1, String nom) {
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
	public void afficheLettres(char[] lettres) {
		for (int i = 0; i < 7; i++) {
			System.out.println("Lettre [" + i + "] = " + lettres[i]);
		}
	}

	/**
	 * Se defausser d'une lettre quand on la joue et pioche ensuite
	 */

	public void defausser(char[] lettres) {

		for (int i = 1; i < 6; i++) {
			lettres[i - 1] = lettres[i];
		}

		lettres[6] = pioche(this.saccoche);

	}

	/**
	 * Pioche une lettre et la retourne
	 */
	public char pioche(Saccoche s) {
		Random r = new Random();
		int aleatoire = r.nextInt(s.getSaccoche().size()+1);
		System.out.println("aleo: "+aleatoire);
		System.out.println(s.toString());
		char c = s.getListLettre().charAt(aleatoire);
		s.getSaccoche().remove(aleatoire);
		String s2 = "";
		for(String s3: s.getSaccoche()){
			s2+=s3;
		}
		s.setListLettre(s2);
		str_saccoche = s.getSaccoche().toString();
		System.out.println("On a tire : " + c);
		return c;
	}

	public String getAlphabet() {
		return this.alphabet.getSaccoche().toString();
	}

	public void setAlphabet(Alphabet alphabet) {
		this.alphabet.setSaccoche(alphabet.getSaccoche());
	}

	public Saccoche getSaccoche() {
		return saccoche;
	}

	public void setSaccoche(Saccoche saccoche) {
		this.saccoche = saccoche;
	}

	/**
	 * Tire 7 lettres aleatoires et les mets dans le tableau 'lettres'
	 */
	public char[] initialiseLettresj2(Saccoche s) {

		lettresj2 = new char[7];
		for (int i = 0; i < 7; i++) {
			lettresj2[i] = pioche(s);

		}
		System.out.println("Lettres, fin : " + lettresj2.toString());
		lettresj2_str = lettresj2.toString();
		DAOParties.update(this);
		return lettresj2;

	}

	/**
	 * Convertir un tableau de lettres en STRING pour la BDD
	 * 
	 * @param tableau_lettres
	 * @return
	 */
	public String convertLettres(char[] tableau_lettres) {
		String res = "";
		for (int i = 0; i < 7; i++) {
			res += tableau_lettres[i];
		}
		return res;

	}

	/**
	 * Tire 7 lettres aleatoirement pour le JOUEUR 1
	 * 
	 * @return
	 */
	public char[] initialiseLettresj1(Saccoche s)
	{
		lettresj1_str = "";
		for (int i = 0; i < 7; i++) {
			lettresj1[i] = pioche(s);
			lettresj1_str += lettresj1[1];
		}
		System.out.println("Lettres, fin : " + lettresj1_str);
		return lettresj1;

	}

	public String afficheBienLettresJ1() {
		String s = "";
		s += "<tr id=" + 1 + ">";
		for (int j = 0; j < 7; j++) {
			s += ("<td id=1"
					+ "><img class=\"lettre\" src=\"style/img/alphabet/"
					+ this.getLettresj1_str().charAt(j) + ".jpg\"</td>");
		}
		s += "</tr>";
		return s;
	}

	public String afficheBienLettresJ2() {
		String s = "";
		s += "<tr id=" + 1 + ">";
		for (int j = 0; j < 7; j++) {
			s += ("<td id=2" 
					+ "><img class=\"lettre\" src=\"style/img/alphabet/"
					+ this.getLettresj2_str().charAt(j) + ".jpg\"</td>");
		}
		s += "</tr>";
		return s;
	}

}
