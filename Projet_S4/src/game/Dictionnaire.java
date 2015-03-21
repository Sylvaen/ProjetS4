package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import beans.Joueur;

public class Dictionnaire {

	
	private static final String CHEMIN_DICTIONNAIRE = "resources/dictionnaire.txt";

	/**
	 * Parcoure la liste des mots du dictionnaire
	 * Methode inutile
	 */
	public void afficheDictionnaire () {


		try {

			BufferedReader buff = new BufferedReader(new FileReader(CHEMIN_DICTIONNAIRE));

			try {
				String line;

				while ((line = buff.readLine()) != null) {
					System.out.println(line);

				}
			} finally {

				buff.close();
			}
		} catch (IOException ioe) {
			System.out.println("Erreur --" + ioe.toString());
		}
	}
	

	/**
	 * Verifie qu'un certain mot existe
	 * Retourne true s'il existe false sinon
	 * @param mot
	 * @return
	 */
	public static boolean chercherMots (String mot) {
		

		boolean trouve = false;
		try {

			BufferedReader buff = new BufferedReader(new FileReader(CHEMIN_DICTIONNAIRE));
			try {
				String line;

				while ((line = buff.readLine()) != null && !trouve) {
					if (line.equals(mot)) {
						trouve = true;
						System.out.println("On a trouve le mot : " + mot);
					}
				}
				
				if (!trouve) {
					System.out.println("Le mot :" + mot + " n'a pas ete trouve.");
					return false;
				}
			} finally {

				buff.close();
			}
		} catch (IOException ioe) {
			System.out.println("Erreur --" + ioe.toString());
		}
		
		
		return true;
	}



}