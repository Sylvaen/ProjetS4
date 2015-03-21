package game;

import static org.junit.Assert.*;

import org.junit.Test;

import beans.Joueur;

public class DictionnaireTest {


	
	/**
	 * Verifie qu'un mot donne est present dans le dictionnaire
	 */
	@Test
	public void testChercherMots() {
		String mot = "chien";
		assertEquals(true, Dictionnaire.chercherMots(mot));
	}
	
	/**
	 * Verifie qu'un joueur gagne bien ses points apres avoir trouve un mot
	 */
	@Test
	public void ajouterPoints() {
		String mot = "chien";
		Joueur j = new Joueur();
		j.ajouterPoints(mot);
		assertEquals(5, j.getPoints());
	}
	
}
