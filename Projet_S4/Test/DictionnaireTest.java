import static org.junit.Assert.assertEquals;
import game.Alphabet;
import game.Dictionnaire;

import org.junit.Test;

import beans.Parties;
import beans.Saccoche;
import beans.User;

public class DictionnaireTest {


	
	/**
	 * Verifie qu'un mot donne est present dans le dictionnaire
	 */
	@Test
	public void testChercherMots() {
		String mot = "chien";
		assertEquals(true, Dictionnaire.chercherMots(mot));
	}
	
//	/**
//	 * Verifie qu'un joueur gagne bien ses points apres avoir trouve un mot
//	 */
//	@Test
//	public void testAjouterPoints() {
//		Alphabet al = new Alphabet ();
//		al.initialise();
//		String mot = "zizi";
//		User j = new User();
//		j.ajouterPoints(mot,al);
//		System.out.println("points de J = " + j.getPoints());
//		assertEquals(22, j.getPoints());
//	}
	
	
	/**
	 * Verifie que le sac contient bien 102 caracteres
	 */
	@Test
	public void testTailleSaccoche () {
		Alphabet al = new Alphabet ();
		Integer taille =  al.getSaccoche().size();
		assertEquals(new Integer(102), taille);
		
	}

}
