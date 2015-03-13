package beans;

public class Case {

	private char lettre;

	public char getLettre() {
		return lettre;
	}

	public void setLettre(char lettre) {
		this.lettre = lettre;
	}
	
	public boolean isEmpty () {
		if (this.lettre == ' ')
			return true;
		return false;
	}
}