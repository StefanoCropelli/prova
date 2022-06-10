package it.unibs.progettoarnaldo.planetarium;

import java.util.ArrayList;
import it.unibs.fp.mylib.InputDati;

public class Main {

    public static void main(String[] args) {
        int scelta;
        
        //Creazione dei Corpi Celesti gia' presenti nel Sistema
        //Stella del Sistema
        Stella stella = new Stella("Indra", 10007.0, 0.0, 0.0);
        //ArrayList dei Pianeti del Sistema
        ArrayList<Pianeta> pianeti = new ArrayList<Pianeta>();
        pianeti.add(new Pianeta("Nautilus", 32.0, 3.0, 5.0));
        pianeti.add(new Pianeta("Telonius", 18.0, 1.0, 2.0));
        pianeti.add(new Pianeta("Godrick", 112.0, 10.0, 13.0));
        //ArrayList delle Lune del Sistema
        ArrayList<Luna> lune = new ArrayList<Luna>();
        lune.add(new Luna("Olympia", 2.0, 2.0, 4.0, pianeti.get(0)));
        lune.add(new Luna("Heverest", 6.0, 10.0, 14.0, pianeti.get(2)));
        lune.add(new Luna("Pizzocalabro", 0.5, 9.0, 13.0, pianeti.get(2)));
        lune.add(new Luna("Sgracchino", 1.1, 8.0, 16.0, pianeti.get(2)));

        //Menu per interagire con l'utente
        do {
        	System.out.println("Benvenuto nel Sistema Secchioverde!\nCome possiamo aiutarla?\n");
            scelta = InputDati.leggiInteroNonNegativo("0)Esci dal programma\n"
                    + "1)Aggiungi corpo celeste\n"
                    + "2)Rimuovi corpo celeste\n"
                    + "3)Ricerca corpo celeste\n"
                    + "4)Visualizza informazioni\n"
                    + "5)Calcola il centro di massa\n"
                    + "6)Calcola rotta\n");
            switch (scelta) {
            //Aggiunta di un nuovo Corpo Celeste al Sistema
            case 1:
            	CorpoCeleste corpo = new CorpoCeleste("corpo", 0.0, 0.0, 0.0);
            	//Richiesta all'utente se vuole aggiungere un Pianeta o una Luna
                String corpoAggiunto = InputDati.leggiStringaNonVuota("Vuoi aggiungere un Pianeta o una Luna? ");
	            while(corpoAggiunto.compareTo("Pianeta") != 0 && corpoAggiunto.compareTo("Luna") != 0) {
	                corpoAggiunto = InputDati.leggiStringaNonVuota("\nScegliere se aggiungere un Pianeta o una Luna: ");
	            }
                
                corpo = corpo.creaCorpoCeleste(corpoAggiunto, pianeti);
            	//Controllo se l'utente ha scelto di creare un Pianeta
            	if(corpoAggiunto.compareTo("Pianeta") == 0) {
            		pianeti.add((Pianeta)corpo);
            		System.out.println("\nPianeta aggiunto al Sistema con successo!");
            	}
            	//Controllo se l'utente ha scelto di creare una Luna
            	else {
            		lune.add((Luna)corpo);
            		System.out.println("\nLuna aggiunta al Sistema con successo!");
            	}
                break;
            case 2:
            	boolean rimosso = false;
            	String corpoRimosso = InputDati.leggiStringaNonVuota("Vuoi rimuovere un Pianeta o una Luna? ");
	            while(corpoRimosso.compareTo("Pianeta") != 0 && corpoRimosso.compareTo("Luna") != 0) {
	            	corpoRimosso = InputDati.leggiStringaNonVuota("\nScegliere se rimuovere un Pianeta o una Luna: ");
	            }
	            
            	if(corpoRimosso.compareTo("Pianeta") == 0) {
            		System.out.println("Pianeti del Sistema:\n");
                	for(int i = 0; i < pianeti.size(); i++ ) {
        	        	System.out.println("\n" + i + ")" + pianeti.get(i).getNome());
        	        }
                
                	//Richiesta all'utente del Pianeta da rimuovere dal Sistema
            		String pianeta = InputDati.leggiStringaNonVuota("\nInserisci il pianeta da rimuovere: ");
                    do {
		            	for(int i = 0; i < pianeti.size(); i++) {
		            		//Se il Pianeta inserito dall'utente e' presente nel Sistema viene eliminato
		            		if(pianeta.compareTo(pianeti.get(i).getNome()) == 0) {
		            			pianeti.remove(i);
		            			//Inoltre vengono eliminate anche le sue Lune
		            			for(int j = 0; j < lune.size(); j++) {
		            				if(pianeta.compareTo(lune.get(j).getPianetaAppartenenza().getNome()) == 0) {
		            					lune.remove(j);
		            				}
		            			}
			            		rimosso = true;
			                	System.out.println("\nIl Pianeta e le sue Lune sono stati rimossi con successo!");
		            		}
		            	}
		            	if(!rimosso) {
		            		System.out.println("\nNon esiste il pianeta che vuoi rimuovere.");
		                	pianeta = InputDati.leggiStringaNonVuota("\nScegliere un altro pianeta da rimuovere: ");
		            	}
            		}while(!rimosso);
            	}
            	else {
            		System.out.println("Lune del Sistema:\n");
                	for(int i = 0; i < lune.size(); i++ ) {
        	        	System.out.println("\n" + i + ")" + lune.get(i).getNome());
        	        }
            		
                	//Richiesta all'utente della Luna da rimuovere dal Sistema
            		String luna = InputDati.leggiStringaNonVuota("\nInserisci la luna da rimuovere: ");
                    do {
		            	for(int i = 0; i < lune.size(); i++) {
		            		//Se la Luna inserita dall'utente e' presente nel Sistema viene eliminata
		            		if(luna.compareTo(lune.get(i).getNome()) == 0) {
		            			lune.remove(i);
		            			rimosso = true;
		                		System.out.println("\nLuna rimossa con successo!");
		            		}
		            	}
		            	
		            	if(!rimosso) {
		            		System.out.println("\nNon esiste la luna che vuoi rimuovere.");
		                	luna = InputDati.leggiStringaNonVuota("\nScegliere un'altra luna da rimuovere: ");
		            	}
                    }while(!rimosso);
            	}
                break;
            case 3: 
            	boolean trovato = false;
            	String corpoRicercato = InputDati.leggiStringaNonVuota("Vuoi cercare un Pianeta o una Luna? ");
	            while(corpoRicercato.compareTo("Pianeta") != 0 && corpoRicercato.compareTo("Luna") != 0) {
	            	corpoRicercato = InputDati.leggiStringaNonVuota("\nScegliere se cercare un Pianeta o una Luna: ");
	            }
	            
            	if(corpoRicercato.compareTo("Pianeta") == 0) {
            		System.out.println("Pianeti del Sistema:\n");
                	for(int i = 0; i < pianeti.size(); i++ ) {
        	        	System.out.println("\n" + i + ")" + pianeti.get(i).getNome());
        	        }
                
            		String pianeta = InputDati.leggiStringaNonVuota("\nInserisci il Pianeta da visualizzare: ");
            		do {
		            	for(int i = 0; i < pianeti.size(); i++) {
		            		//Ricerca del Pianeta inserito dall'utente e visualizzazione delle sue info, comprese le Lune orbitanti attorno
		            		if(pianeta.compareTo(pianeti.get(i).getNome()) == 0) {
		                    	System.out.println(pianeti.get(i).toString());
		                    	for(int j = 0; j < lune.size(); j++) {
		            				if(pianeta.compareTo(lune.get(j).getPianetaAppartenenza().getNome()) == 0) {
		            					System.out.println("Luna orbitante attorno al Pianeta: " + lune.get(j).getNome());
		            				}
		            			}
			            		trovato = true;			                	
			            		System.out.println("\nPianeta trovato con successo!");
		                    }
		            	}
		            	if(!trovato) {
		            		System.out.println("\nNon esiste il Pianeta che vuoi trovare.");
		                	pianeta = InputDati.leggiStringaNonVuota("\nScegliere un altro Pianeta da cercare: ");
		            	}
            		}while(!trovato);
            	}
            	else {
            		System.out.println("Lune del Sistema:\n");
                	for(int i = 0; i < lune.size(); i++ ) {
        	        	System.out.println("\n" + i + ")" + lune.get(i).getNome());
        	        }
                
            		String luna = InputDati.leggiStringaNonVuota("\nInserisci la Luna da visualizzare: ");
            		do {
		            	for(int i = 0; i < lune.size(); i++) {
		                	//Ricerca della Luna inserita dall'utente e visualizzazione delle sue info
		            		if(luna.compareTo(lune.get(i).getNome()) == 0) {
		                    	System.out.println(lune.get(i).toString() + "\n");
			            		trovato = true;			                	
			            		System.out.println("\nLuna trovato con successo!");
		                    }
		            	}
		            	if(!trovato) {
		            		System.out.println("\nNon esiste la Luna che vuoi trovare.");
		            		luna = InputDati.leggiStringaNonVuota("\nScegliere un'altra Luna da cercare: ");
		            	}
            		}while(!trovato);
            	}
                break;
            case 4:
            	//Visualizzazione di tutte le info di tutti i Corpi Celesti del Sistema
            	System.out.println("\nSISTEMA SECCHIOVERDE\n");
            	System.out.println(stella.toString() + "\n");
            	for(int i = 0; i < pianeti.size(); i++) {
	        		System.out.println(pianeti.get(i).toString());
	        		for(int j = 0; j < lune.size(); j++) {
	    				if(pianeti.get(i).getNome().compareTo(lune.get(j).getPianetaAppartenenza().getNome()) == 0) {
	    					System.out.println("Luna orbitante attorno al Pianeta: " + lune.get(j).getNome());
	    				}
	        		}
	    			System.out.println("\n");
    			}
            	for(int i = 0; i < lune.size(); i++) {
            		System.out.println(lune.get(i).toString() + "\n");
            	}
            	break;
            case 5:
            	//Visualizzazione del Centro di massa del Sistema
            	CorpoCeleste corpoCel = new CorpoCeleste("corpo", 0.0, 0.0, 0.0);
            	double centroMassaX = 0.0;
            	double centroMassaY = 0.0;
            	Punto centroMassa = new Punto(centroMassaX, centroMassaY);
            	centroMassa = corpoCel.centroDiMassa(stella, pianeti, lune);
            	System.out.println("Il centro di massa del Sistema Secchioverde e': (" + centroMassa.getX() + "," + centroMassa.getY() + ")");
                break;
            case 6:
            	String corpo1 = InputDati.leggiStringaNonVuota("Inserisci un corpo celeste da cui partire ");
            	String corpo2 = InputDati.leggiStringaNonVuota("Inserisci un corpo celeste da cui partire ");

            	break;

            }
        } while(scelta!=0);
    }

}
