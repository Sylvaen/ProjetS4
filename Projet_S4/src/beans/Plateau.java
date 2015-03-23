package beans; 

import game.Case;

public class Plateau {

	private final int TAILLE = 19;
	private Case[][] plateau;
	private String nom;
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Plateau() {
		this.plateau = new Case[TAILLE][TAILLE];
		
		initialisePlateau();

	}

	public void initialisePlateau() {
		Case c = new Case();
		for (int i = 0; i < TAILLE; i++) {
			for (int j = 0; j < TAILLE; j++) {
				this.plateau[i][j] = c;
				this.plateau[i][j].setLettre('-');
			}
		}
	}

	public String toString() {
		String resultat = "";
		for (int i = 0; i < TAILLE; i++) {
			for (int j = 0; j < TAILLE; j++) {
				resultat += this.plateau[i][j].getLettre();
			}
		}
		return resultat;
	}

	public void toPlateau(String s) {
		if (verifChaine(s)) {
			for (int i = 0; i < TAILLE; i++) {
				for (int j = 0; j < TAILLE; j++) {
					this.plateau[i][j].setLettre(s.charAt(i * TAILLE + j));

				}
			}
		}
	}

	public boolean verifChaine(String s) {
		int verification = TAILLE * TAILLE;
		boolean verif = true;

		if (s.length() != verification) {
				verif = false;
		}
		if (verif == false) {
			System.out.println("Taille chaine = "+ s.length());
 			System.out.println("Le contenu de la chaine n'est pas correct."
					+ "\n manquant ? mal place ?");
			return false;
		}

		return true;
	}
	
	public String afficherPlateau() {
		String s = "<table>";
		for (int i = 0; i < TAILLE; i++) {
			s+= "<tr>";
			for (int j = 0; j < TAILLE; j++) {
				s+=("<td>"+plateau[i][j].getLettre()+"</td>");
			}
			s+="</tr>";
		}
		s+="</table>";
		return s;
		
	}
	
	public String afficherPlateau2(String plateau_string) {
		String s = "<table>";
		s+= plateau_string;
		for (int i = 0; i < TAILLE; i++) {
			s+= "<tr id="+i+">";
			for (int j = 0; j < TAILLE; j++) {
				s+=("<td id="+j+"><img class=\"lettre\" src=\"style/img/alphabet/"+plateau[i][j].getLettre()+".jpg\"</td>");
			}
			s+="</tr>";
		}
		s+="</table>";
		return s;
		
	}
}