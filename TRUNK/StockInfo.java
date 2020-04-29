package real;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.Button;

public class StockInfo {
	
	private String campOpp; //camp oppos? o? le robot devrai d?poser le palet
	private String campDep; //camp de d?part du robot

	//constructeur        
	public StockInfo (){
	
		this.campOpp = null;
		this.campDep = null;

	}
	
	
	public String getCampDep() {
		return campDep;
	}
	
	//affichage d'un interface de menu avec un fleche qui pointe sur vert
	public void afficheCamp1() {
		System.out.println();
		System.out.println("  -Camp Depart?-");
		System.out.println("  >Vert");
		System.out.println("   Bleu");
		System.out.println();
		System.out.println();
		System.out.println(" ESCAPE TO EXIT");
		
	}
	
	//affichage d'un interface de menu avec un fleche qui pointe sur bleu
	public void afficheCamp2()	{
		System.out.println();
		System.out.println("  -Camp Depart?-");
		System.out.println("   Vert");
		System.out.println("  >Bleu");
		System.out.println();
		System.out.println();
		System.out.println(" ESCAPE TO EXIT");
	}

	public void choisircamp(){ // permet de definir quel camp a ete choisi
		
		Button.waitForAnyPress();
		if(Button.UP.isDown()){
			campDep= "Ouest";
			campOpp= "Est";
		}
		else if(Button.DOWN.isDown()) {
			campDep= "Est";
			campOpp= "Ouest";
		}
			
	}
	
	public void menu() {
		
		int i =0;
		while (Button.ESCAPE.isUp() && Button.ENTER.isUp()) {//Tant que les boutons ENTER et ESCAPE ne sont pas appuye
			if (i%2==0) {//Si i est positif on affiche afficheCamp1
				afficheCamp1();
				campDep= "Est"; //Est est affecte au CampDep
				campOpp= "Ouest";//Ouest est affecte au CampOpp
				
			}
			else {//sinon (i negatif) on affiche afficheCamp2
				afficheCamp2();
				campDep= "Ouest";//Ouest est affecte au CampOpp
				campOpp= "Est";//Est est affecte au CampDep
			}
			Button.waitForAnyPress();//On attend un appuie de bouton par l'utilisateur apres l'affichage
			if (Button.DOWN.isDown() || Button.UP.isDown()) {
				i++;/*A chaque appuie de bouton en haut ou en bas on incremente i par 1. Cela nous permet de changer l'affichage
						entre afficheCamp1 et afficheCamp2 si ces boutons sont appuyes*/
			}
			else if (Button.ESCAPE.isDown()) {//
				campDep = null;
				campOpp = null;
			}
			
		}
	}
}



