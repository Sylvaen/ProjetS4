package beans;

import java.io.Serializable;
import java.util.ArrayList;

 /**
  * Une saccoche represente les lettres disponibles lors de la pioche
  * @author amaury
  *
  */
public class Saccoche implements Serializable{


	private static final long serialVersionUID = 1L;
	private ArrayList <String> saccoche;
	private String listLettre = "";
	private int nbLettres;
	private int idparties;
	private int id;
	
	/**
	 * Retourne le nombre de lettres de la saccoche
	 * @return
	 */
	public int getNbLettres() {
		return nbLettres;
	}

	public void setNbLettres(int nbLettres) {
		this.nbLettres = nbLettres;
	}

	public String toString()
	{
		return "Je suis la saccoche "+this.getId();
	}
	public int getIdparties() {
		return idparties;
	}

	public void setIdparties(int idparties) {
		this.idparties = idparties;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Saccoche () {
		
		
		this.saccoche = new ArrayList <String> ();
		this.nbLettres = saccoche.size();
		for (int i=0; i < 10; i++) {
			saccoche.add("a");
		}
		
		saccoche.add("b");
		saccoche.add("b");
		
		saccoche.add("c");
		saccoche.add("c");
		
		saccoche.add("d");
		saccoche.add("d");
		saccoche.add("d");
		
		for (int i=0; i < 15; i++) {
			saccoche.add("e");
		}
		
		saccoche.add("f");
		saccoche.add("f");
		
		saccoche.add("g");
		saccoche.add("g");
		
		saccoche.add("h");
		saccoche.add("h");
		
		for (int i=0; i < 9; i++) {
			saccoche.add("i");
		}
		
		saccoche.add("j");
		
		saccoche.add("k");
		
		for (int i=0; i < 5; i++) {
			saccoche.add("l");
		}
		
		for (int i=0; i < 3; i++) {
			saccoche.add("m");
		}
		
		for (int i=0; i < 6; i++) {
			saccoche.add("n");
		}
		
		for (int i=0; i < 6; i++) {
			saccoche.add("o");
		}
		
		saccoche.add("p");
		saccoche.add("p");
		
		saccoche.add("q");
		for (int i=0; i < 6; i++) {
			saccoche.add("r");
		}
		for (int i=0; i < 6; i++) {
			saccoche.add("s");
		}
		for (int i=0; i < 6; i++) {
			saccoche.add("t");
		}
		for (int i=0; i < 6; i++) {
			saccoche.add("u");
		}
		saccoche.add("v");
		saccoche.add("v");
		
		saccoche.add("w");
		saccoche.add("x");
		saccoche.add("y");
		saccoche.add("z");
		listLettre = "";
		for(String s: this.saccoche){
			listLettre += s;
		}
	}

	public ArrayList<String> getSaccoche() {
		return saccoche;
	}

	public void setSaccoche(ArrayList<String> saccoche) {
		this.saccoche = saccoche;
		for(String s: this.saccoche){
			this.setListLettre(this.getListLettre() + s);
		}
	}

	public String getListLettre() {
		return listLettre;
	}

	public void setListLettre(String listLettre) {
		this.listLettre = listLettre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	
	
	
	
	
	
}
