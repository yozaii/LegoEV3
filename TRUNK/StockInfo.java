package real;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.Button;

public class StockInfo {
	
	private String campOpp; //camp oppos? o? le robot devrai d?poser le palet
	private String campDep; //camp de d?part du robot
	private boolean ExistePalet;

	//constructeur        
	public StockInfo (){
	
		this.campOpp = null;
		this.campDep = null;
		this.ExistePalet = true;

	}
	
	public void setExistePalet(boolean existe) {
		ExistePalet = existe;
	}
	
	public boolean getExistePalet() {
		return ExistePalet;
	}
	
	public String getCampDep() {
		return campDep;
	}
	
	
	public void affiche() {
		System.out.println("Camp Depart?");
		System.out.println("Boutton droit pour est, gauche pour ouest");
	}

	public void choisircamp(){ // permet de definir quel camp a ete choisi
		
		Button.waitForAnyPress();
		if(Button.LEFT.isDown()){
			campDep= "Ouest";
			campOpp= "Est";
		}
		else if(Button.RIGHT.isDown()) {
			campDep= "Est";
			campOpp= "Ouest";
		}
			
	}
	
	public void menu() {
		this.affiche();
		this.choisircamp();
	}
}
