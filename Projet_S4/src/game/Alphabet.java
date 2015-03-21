package game;

import java.util.HashMap;

public class Alphabet {

	private static HashMap <Integer, String> alphabet;
	
	public Alphabet () {
		Alphabet.alphabet = new HashMap <Integer, String> (26);
	}
	
	/**
	 * Range dans la map alphabet toutes les lettres
	 * et leur coefficient
	 */
	public void initialise () {
		Alphabet.getAlphabet().put(1, "a");
		Alphabet.alphabet.put(3, "b");
		Alphabet.alphabet.put(4, "c");
		Alphabet.alphabet.put(2, "d");
		Alphabet.alphabet.put(1, "e");
		Alphabet.alphabet.put(4, "f");
		Alphabet.alphabet.put(2, "g");
		Alphabet.alphabet.put(4, "h");
		Alphabet.alphabet.put(1, "i");
		Alphabet.alphabet.put(8, "j");
		Alphabet.alphabet.put(10, "k");
		Alphabet.alphabet.put(1, "l");
		Alphabet.alphabet.put(2, "m");
		Alphabet.alphabet.put(1, "n");
		Alphabet.alphabet.put(1, "o");
		Alphabet.alphabet.put(3, "p");
		Alphabet.alphabet.put(8, "q");
		Alphabet.alphabet.put(1, "r");
		Alphabet.alphabet.put(1, "s");
		Alphabet.alphabet.put(1, "t");
		Alphabet.alphabet.put(1, "u");
		Alphabet.alphabet.put(4, "v");
		Alphabet.alphabet.put(10, "w");
		Alphabet.alphabet.put(10, "x");
		Alphabet.alphabet.put(10, "y");
		Alphabet.alphabet.put(10, "z");
	}

	/**
	 * Retourne l'alphabet
	 * @return
	 */
	public static HashMap <Integer, String> getAlphabet() {
		return alphabet;
	}

	/**
	 * Permet de changer l'alphabet
	 * @param alphabet
	 */
	public static void setAlphabet(HashMap <Integer, String> alphabet) {
		Alphabet.alphabet = alphabet;
	}
}
