package it.unibs.progettoarnaldo.planetarium;

public class Pianeta extends CorpoCeleste {

    //Metodo Costruttore
	public Pianeta(String nome, double massa, double coordinataX, double coordinataY) {
		super(nome, massa, coordinataX, coordinataY);
	}

	@Override
	public String toString() {
		return "Pianeta: " + getNome() + "\nMassa: " + getMassa() + "\nPosizione: " + "(" + getCoordinataX() + "," + getCoordinataY() + ")";
	}	

}
