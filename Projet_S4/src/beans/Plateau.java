package beans;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

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

		if (s.length() == verification) {
			for (int i = 0; i < s.length(); i++) {
				if (i % 19 == 0) {
					char c = s.charAt(i);
					if (c != '\n') {
						verif = false;

					}
				}
			}

			if (verif == false) {
				System.out.println("Le contenu de la chaine n'est pas correct."
						+ "\n manquant ? mal place ?");
				return false;
			}

		} else {
			System.out.println("La chaine ne contient pas " + TAILLE * TAILLE
					+ " caracteres");
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
	

}