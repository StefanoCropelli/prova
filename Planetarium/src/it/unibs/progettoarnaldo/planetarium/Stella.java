package it.unibs.progettoarnaldo.planetarium;

public class Stella extends CorpoCeleste {
    
	//Metodo Costruttore
	public Stella(String nome, double massa, double coordinataX, double coordinataY) {
		super(nome, massa, coordinataX, coordinataY);
	}

	@Override
	public String toString() {
		return "Stella: " + getNome() + "\nMassa: " + getMassa() + "\nPosizione: " + "(" + getCoordinataX() + "," + getCoordinataY() + ")";
	}
	
}
