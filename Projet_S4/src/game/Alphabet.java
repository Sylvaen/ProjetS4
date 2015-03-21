package game;

import java.util.HashMap;

public class Alphabet {

	private static HashMap<String, Integer> alphabet;
	
	public Alphabet () {
		Alphabet.alphabet = new HashMap <String, Integer> (26);
		this.initialise();
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
	public static HashMap<String, Integer> getAlphabet() {
		return alphabet;
	}

}
