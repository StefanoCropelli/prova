package it.unibs.progettoarnaldo.planetarium;

public class Luna extends CorpoCeleste{

	//Attributi
    private Pianeta pianetaAppartenenza;
    
    //Metodo Costruttore
    public Luna(String nome, double massa, double coordinataX, double coordinataY, Pianeta pianetaAppartenenza) {
        super(nome, massa, coordinataX, coordinataY);
        this.pianetaAppartenenza = pianetaAppartenenza;
    }

    //Metodo Getter
    public Pianeta getPianetaAppartenenza() {
        return pianetaAppartenenza;
    }
    
    @Override
	public String toString() {
		return "Luna: " + getNome() + "\nMassa: " + getMassa() + "\nPosizione: " + "(" + getCoordinataX() + "," + getCoordinataY() + ")"
				+ "\nPianeta di appartenenza: " + pianetaAppartenenza.getNome();
	}

}