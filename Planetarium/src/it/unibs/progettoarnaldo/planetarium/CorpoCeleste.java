package it.unibs.progettoarnaldo.planetarium;

import java.util.ArrayList;

public class CorpoCeleste {

	//Attributi
    private String nome;
    private double massa;
    private double coordinataX;
    private double coordinataY;

    //Metodo Costruttore
    public CorpoCeleste(String nome, double massa, double coordinataX, double coordinataY) {
        this.nome = nome;
        this.massa = massa;
        this.coordinataX = coordinataX;
        this.coordinataY = coordinataY;
    }

    //Metodi Getter e Setter
    public double getMassa() {
        return massa;
    }

    public void setMassa(double massa) {
        this.massa = massa;
    }

    public double getCoordinataX() {
        return coordinataX;
    }

    public void setCoordinataX(double coordinataX) {
        this.coordinataX = coordinataX;
    }

    public double getCoordinataY() {
        return coordinataY;
    }

    public void setCoordinataY(double coordinataY) {
        this.coordinataY = coordinataY;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Metodo per creare un Pianeta o una Luna in base alla scelta dell'utente
     * @param corpoAggiunto
     * @param pianeti
     * @return nuovo Corpo Celeste
     */
    public CorpoCeleste creaCorpoCeleste(String corpoAggiunto, ArrayList<Pianeta> pianeti) {
    	
    	//Controllo se l'utente ha scelto di creare un Pianeta
    	if(corpoAggiunto.compareTo("Pianeta") == 0) {
	        String nome = InputDati.leggiStringaNonVuota("\nInserisci il nome del pianeta: ");
	        double massa = InputDati.leggiDouble("\nInserisci la massa: ");
	        double coordinataX = InputDati.leggiDouble("\nInserisci coordinata X: ");
	        double coordinataY = InputDati.leggiDouble("\nInserisci coordinata Y: ");
	        CorpoCeleste corpo = new Pianeta(nome, massa, coordinataX, coordinataY);
	        return corpo;
    	}
    	//Altrimenti l'utente ha scelto di creare una Luna
    	else {
        	int i = 0;
    		String nome = InputDati.leggiStringaNonVuota("\nInserisci il nome della luna: ");
	        double massa = InputDati.leggiDouble("\nInserisci la massa: ");
	        double coordinataX = InputDati.leggiDouble("\nInserisci coordinata X: ");
	        double coordinataY = InputDati.leggiDouble("\nInserisci coordinata Y: ");
	
	        //Stampo la lista dei Pianeti per far scegliere a quale la nuova Luna deve orbitare attorno
	        System.out.println("Pianeti del Sistema:\n");
        	for(i = 0; i < pianeti.size(); i++ ) {
	        	System.out.println("\n" + i + ")" + pianeti.get(i).getNome());
	        }
        	
        	String pianeta = InputDati.leggiStringaNonVuota("\nInserisci il pianeta di appartenenza della luna: ");
		    i = 0;
		    
		    //Controllo se l'utente inserisce un Pianeta presente nel Sistema per far orbitare attorno la nuova Luna
		    while(pianeta.compareTo(pianeti.get(i).getNome()) != 0) {
	        	i++;
		    	if(i == pianeti.size()) {
		        	pianeta = InputDati.leggiStringaNonVuota("\nScegliere a quale pianeta della lista la luna orbita attorno: ");
		        	i = 0;
		    	}
		    }
	        
	        CorpoCeleste corpo = new Luna(nome, massa, coordinataX, coordinataY, pianeti.get(i));	        
	        return corpo;
    	}
    }
    
    /**
     * Metodo per calcolare il centro di massa del Sistema
     * @param stella
     * @param pianeti
     * @param lune
     * @return Centro di massa del Sistema
     */
    public Punto centroDiMassa(Stella stella, ArrayList<Pianeta> pianeti, ArrayList<Luna> lune) {
    	
    	double massaSistema = 0.0;
    	double sommaPosX = 0.0;
    	double sommaPosY = 0.0;
    	
    	//Calcolo la massa totale di tutti i Corpi Celesti del Sistema
    	massaSistema += stella.getMassa();
    	for(int i = 0; i < pianeti.size(); i++) {
    		massaSistema += pianeti.get(i).getMassa();
    	}
    	for(int i = 0; i < lune.size(); i++) {
    		massaSistema += lune.get(i).getMassa();
    	}
    	
    	//Calcolo la somma pesata delle coordinate X dei Corpi Celesti del Sistema
    	sommaPosX += (stella.getCoordinataX() * stella.getMassa());
    	for(int i = 0; i < pianeti.size(); i++) {
    		sommaPosX += (pianeti.get(i).getMassa() * pianeti.get(i).getCoordinataX());
    	}
    	for(int i = 0; i < lune.size(); i++) {
    		sommaPosX += (lune.get(i).getMassa() * lune.get(i).getCoordinataX());
    	}
    	
    	//Calcolo la somma pesata delle coordinate Y dei Corpi Celesti del Sistema
    	sommaPosY += (stella.getCoordinataY() * stella.getMassa());
    	for(int i = 0; i < pianeti.size(); i++) {
    		sommaPosY += (pianeti.get(i).getMassa() * pianeti.get(i).getCoordinataY());
    	}
    	for(int i = 0; i < lune.size(); i++) {
    		sommaPosY += (lune.get(i).getMassa() * lune.get(i).getCoordinataY());
    	}
    	
    	//Creo il Punto Centro di massa che ha come coordinate la divisione tra la somma totale del Sistema e la somma delle coordinate pesate del Sistema
    	Punto centroMassa = new Punto(sommaPosX / massaSistema, sommaPosY / massaSistema);
    	return centroMassa;
    }   
    
}

