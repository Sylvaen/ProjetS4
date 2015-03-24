package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Alphabet {

	private static HashMap<String, Integer> alphabet;
	private static HashMap<String, Integer> sac;
	private static ArrayList <String> saccoche;
	
	public Alphabet () {
		Alphabet.alphabet = new HashMap <String, Integer> (26);
		Alphabet.sac = new HashMap <String, Integer> (26);
		Alphabet.saccoche = new ArrayList <String>();
		this.initialise();
		this.initialiseSac();
		this.initialiseSaccoche();
	}
	


	public ArrayList<String> getSaccoche() {
		return saccoche;
	}



	public static void setSaccoche(ArrayList<String> saccoche) {
		Alphabet.saccoche = saccoche;
	}



	/**
	 * Range dans la map alphabet toutes les lettres
	 * et leur coefficient
	 */
	public void initialise () {
		Alphabet.alphabet.put("a", 1);
		Alphabet.alphabet.put("b", 3);
		Alphabet.alphabet.put("c", 4);
		Alphabet.alphabet.put("d", 2);
		Alphabet.alphabet.put("e", 1);
		Alphabet.alphabet.put("f", 4);
		Alphabet.alphabet.put("g", 2);
		Alphabet.alphabet.put("h", 4);
		Alphabet.alphabet.put("i", 1);
		Alphabet.alphabet.put("j", 8);
		Alphabet.alphabet.put("k", 10);
		Alphabet.alphabet.put("l", 1);
		Alphabet.alphabet.put("m", 2);
		Alphabet.alphabet.put("n", 1);
		Alphabet.alphabet.put("o", 1);
		Alphabet.alphabet.put("p", 3);
		Alphabet.alphabet.put("q", 8);
		Alphabet.alphabet.put("r", 1);
		Alphabet.alphabet.put("s", 1);
		Alphabet.alphabet.put("t", 1);
		Alphabet.alphabet.put("u", 1);
		Alphabet.alphabet.put("v", 4);
		Alphabet.alphabet.put("w", 10);
		Alphabet.alphabet.put("x", 10);
		Alphabet.alphabet.put("y", 10);
		Alphabet.alphabet.put("z", 10);
	}

	/**
	 * Retourne l'alphabet
	 * @return
	 */
	public HashMap<String, Integer> getAlphabet() {
		return alphabet;
	}
	public void initialiseSac () {
		Alphabet.sac.put("a", 9);
		Alphabet.sac.put("b", 2);
		Alphabet.sac.put("c", 2);
		Alphabet.sac.put("d", 3);
		Alphabet.sac.put("e", 15);
		Alphabet.sac.put("f", 2);
		Alphabet.sac.put("g", 2);
		Alphabet.sac.put("h", 2);
		Alphabet.sac.put("i", 8);
		Alphabet.sac.put("j", 1);
		Alphabet.sac.put("k", 1);
		Alphabet.sac.put("l", 5);
		Alphabet.sac.put("m", 3);
		Alphabet.sac.put("n", 6);
		Alphabet.sac.put("o", 6);
		Alphabet.sac.put("p", 2);
		Alphabet.sac.put("q", 1);
		Alphabet.sac.put("r", 6);
		Alphabet.sac.put("s", 6);
		Alphabet.sac.put("t", 6);
		Alphabet.sac.put("u", 6);
		Alphabet.sac.put("v", 2);
		Alphabet.sac.put("w", 1);
		Alphabet.sac.put("x", 1);
		Alphabet.sac.put("y", 1);
		Alphabet.sac.put("z", 1);
	}

	public HashMap<String, Integer> getSac() {
		return sac;
	}

	public static void setSac(HashMap<String, Integer> sac) {
		Alphabet.sac = sac;
	}

	public static void setAlphabet(HashMap<String, Integer> alphabet) {
		Alphabet.alphabet = alphabet;
	}
	
	public void initialiseSaccoche () {
		for (int i=0; i < 10; i++) {
			Alphabet.saccoche.add("a");
		}
		
		Alphabet.saccoche.add("b");
		Alphabet.saccoche.add("b");
		
		Alphabet.saccoche.add("c");
		Alphabet.saccoche.add("c");
		
		Alphabet.saccoche.add("d");
		Alphabet.saccoche.add("d");
		Alphabet.saccoche.add("d");
		
		for (int i=0; i < 15; i++) {
			Alphabet.saccoche.add("e");
		}
		
		Alphabet.saccoche.add("f");
		Alphabet.saccoche.add("f");
		
		Alphabet.saccoche.add("g");
		Alphabet.saccoche.add("g");
		
		Alphabet.saccoche.add("h");
		Alphabet.saccoche.add("h");
		
		for (int i=0; i < 9; i++) {
			Alphabet.saccoche.add("i");
		}
		
		Alphabet.saccoche.add("j");
		
		Alphabet.saccoche.add("k");
		
		for (int i=0; i < 5; i++) {
			Alphabet.saccoche.add("l");
		}
		
		for (int i=0; i < 3; i++) {
			Alphabet.saccoche.add("m");
		}
		
		for (int i=0; i < 6; i++) {
			Alphabet.saccoche.add("n");
		}
		
		for (int i=0; i < 6; i++) {
			Alphabet.saccoche.add("o");
		}
		
		Alphabet.saccoche.add("p");
		Alphabet.saccoche.add("p");
		
		Alphabet.saccoche.add("q");
		for (int i=0; i < 6; i++) {
			Alphabet.saccoche.add("r");
		}
		for (int i=0; i < 6; i++) {
			Alphabet.saccoche.add("s");
		}
		for (int i=0; i < 6; i++) {
			Alphabet.saccoche.add("t");
		}
		for (int i=0; i < 6; i++) {
			Alphabet.saccoche.add("u");
		}
		Alphabet.saccoche.add("v");
		Alphabet.saccoche.add("v");
		
		Alphabet.saccoche.add("w");
		Alphabet.saccoche.add("x");
		Alphabet.saccoche.add("y");
		Alphabet.saccoche.add("z");
	}
}
