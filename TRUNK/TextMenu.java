package menu;
import lejos.hardware.Button;
public class TextMenu {
	private String campOpp; //camp oppose ou le robot devrai deposer le palet
    private String campDep; //camp de depart du robot
   
	public TextMenu (){
		 this.campOpp= null;
	     this.campDep=null;
	    
	     }
	
	
	
	public void affiche(){ 
		System.out.println("Camp depart ?");
		System.out.println("Boutton droit pour est, gauche pour ouest");
	}
	
	public void choisircamp(){ // permet de definir quel camp a ete choisi
		Button.waitForAnyPress();
		if(Button.LEFT.isDown()){
			campOpp = "ouest";
			
		}
		if(Button.RIGHT.isDown()){
			campDep = "est";
			
		}
		
	}
}
