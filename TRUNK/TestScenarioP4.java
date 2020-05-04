package real;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class TestScenarioP4 extends Robot {
	
	//rejoindreLigne est modifie dans le scenario 4. Lorsque le palet est attrape, le robot n'execute plus StopWhite() comme dans la classe Robot
	public void rejoindreLigne(String couleur, String sens) {
		do {
			this.getRoues().MoveForwardSpeed(getRoues().getVitesse());// le robot avance
			this.getColor().colorScan();// le robot scnanne la couleur
		}
		while (!(this.ToucherDeux()) && (this.getColor().getCouleurCourant()!= couleur));// le robot avance tant quil ne touche pas de palet et quil ne capte pas une couleur
		
		if(this.ToucherDeux()) {// si le capteur tactile est touche le robot sarrete et attrape le palet et setgo est faux
			this.getRoues().Stop();
			this.getPinces().Close();
			this.getRoues().RotateToZero();
			this.setGo(false);
		}
		
		
		
		else if(this.getColor().getCouleurCourant()== couleur) {// si le robot capte une couleur
			this.RotateUntilLinePerp(couleur, sens); 
		}
	
	}

	//parcourirLigne est modifie dans le scenario 4. Lorsque le palet est attrape, le robot n'execute plus StopWhite() comme dans la classe Robot
	public void parcourirLigne(String couleur) {
		
		this.FollowLine(couleur);
		if(this.getTouch().isPressed())	// si le capteur tactile est touche le robot sarrete et attrape le palet
		{
			this.getRoues().Stop();
			this.getPinces().Close();
			this.setGo(false);
		}
		
	}
	
	//Methode qui sert a rejoindre la ligne blanche du camp Oppose. Prend les couleurs du camp Depart et Camp oppose en argument
	public void stopCampOpp(String EndCamp, String StartCamp) {
		do {
			this.getRoues().MoveForwardSpeed(250f);//Le this avance
			this.getColor().colorScan();//Il scan le couleur en dessous en avancant
		}
		while (this.getColor().getCouleurCourant()!="WHITE" && this.getColor().getCouleurCourant()!="BLACK");//Tant que les couleurs scanne ne sont pas noires ou blaches
		
		if (this.getColor().getCouleurCourant()=="BLACK") {//Si le couleur est noire
			do {
				this.getRoues().MoveForwardSpeed(250f);// le robot avance
				this.getColor().colorScan();// le robot scanne la couleur
			}
			while (this.getColor().getCouleurCourant()!=EndCamp && this.getColor().getCouleurCourant()!=StartCamp );//On avance jusqu'a la ligne de camp depart ou de camp oppose
			
			if (this.getColor().getCouleurCourant()==StartCamp) {//Si le couleur detecte est celui du camp de depart
				this.getRoues().RotateD("Left", 180);//On fait un demitour
				this.StopWhite();//On avance et on pose le palet a la ligne blanche en face
			}
			else if (this.getColor().getCouleurCourant()==EndCamp) {//Sinon si le couleur est celui du camp adverse
				this.StopWhite();//On avance et on pose le palet a la ligne blanche en face
			}
		}
		else if (this.getColor().getCouleurCourant()=="WHITE") {//Si le couleur est blanc
			this.getRoues().RotateD("Left", 180);//On fait un demitour
			do {// le robot avance et scanne la couleur
				this.getRoues().MoveForwardSpeed(250f);
				this.getColor().colorScan();
			}
			while (this.getColor().getCouleurCourant()!="BLACK");//On avance jusqu'a la ligne noire.
			do {
				this.getRoues().MoveForwardSpeed(250f);
				this.getColor().colorScan();
			}
			while (this.getColor().getCouleurCourant()!=EndCamp && this.getColor().getCouleurCourant()!=StartCamp );//le robot  tant quil nest pas sur la ligne blanche du camp oppose ou du camp de depart
			
			if (this.getColor().getCouleurCourant()==StartCamp) {// si le robot se trouve dans le camp de depart il se dirige vers le camp oppose
				this.getRoues().RotateD("Left", 180);// le robot faitt une rotation de 180degres par la gauche
				this.StopWhite();// le robot se dirige vers la ligne blanche
			}
			else if (this.getColor().getCouleurCourant()==EndCamp) {// si le robot se trouve dans le camp oppose il se dirige vers la ligne blanche du camp oppose
				this.StopWhite();
			}
		}
	}
	
	
	 public static void main (String[] args) {
         
		 TestScenarioP4 robot = new TestScenarioP4();
		 String StartCamp,EndCamp;
	     robot.getInfo().menu();
	     System.out.println(robot.getInfo().getCampDep());
	     if (robot.getInfo().getCampDep()=="Ouest") {// si le camp de depart est OUEST 
	                StartCamp = "BLUE";// le camp de depart est du cote de la ligne bleue.
	                EndCamp ="GREEN";//le camp adverse est du cote de la ligne verte.
	     }
	     else {//sinon si le camp de depart est EST
	                StartCamp = "GREEN";//le camp de depart est du cote de la ligne verte.
	                EndCamp = "BLUE";// le camp adverse est du cote de la ligne bleue.
	     }
         System.out.println("Press any button");
         Button.waitForAnyPress();
         robot.RchrchCamp();// le robot se dirige vers le camp depart
         if (!robot.getGo()) {//getGo devien false si on attrape un palet lors du RchrChamp()
        	 robot.stopCampOpp(EndCamp, StartCamp);//Si palet attrape on lance stopCampOpp
         }
         else {
	         robot.getRoues().MoveForwardSpeed(robot.getRoues().getVitesse());
	         // avancer jusqu'a la ligne VERTE ou BLEUE
	         do { 
	                 robot.getColor().colorScan();
	         }while ((!(robot.ToucherDeux())) && (robot.getColor().getCouleurCourant()!= "GREEN" && robot.getColor().getCouleurCourant() != "BLUE")); 
	
	 		if(robot.getColor().getCouleurCourant()=="GREEN") {	// Si c'est VERT:
	 			robot.RotateUntilLinePerp("GREEN", "Right");
	 		
	 			robot.parcourirLigne("GREEN");//on parcour la ligne verte
	 			if(robot.getGo()) {// si go est vrai ça veut dire que le robot n'a pas croiisé de palet
	 				robot.getRoues().RotateD("Right", 90);// vu qu'il n'y a pas de palet on tourne en deux étapes pour parcourir le reste de la ligne
	 				if(robot.getGo()) {
	 					robot.RotateUntilLine("GREEN","Right");
	 					if(robot.getGo()) {
	 						robot.parcourirLigne("GREEN");// on parcourt lereste de la ligne verte
	 						if(robot.getGo()) {
	 							robot.getRoues().RotateD("Right", 90);// le robot tourne a 90 degre pour rejoindre la ligne noire
	 							if(robot.getGo()) {
	 								robot.rejoindreLigne("BLACK", "Right");// lorsqu'il arrive a la ligne noire il tourne à droite
	 								if(robot.getGo()) {
	 									robot.parcourirLigne("BLACK");// le robot parcourt l'intégralité de la ligne noire 
	 									if(robot.getGo()) {
	 										robot.getRoues().RotateD("Left", 90);// il tourne à gauche 
	 										if(robot.getGo()) {
	 											robot.rejoindreLigne("BLUE", "Left");// il rejoint la ligne bleue
	 											if(robot.getGo()) {
	 												robot.parcourirLigne("BLUE");// il parcourt la ligne bleue
	 											}	
	 										}
	 									}
	 								}
	 							}
	 						}
	 					}
	 				}
	 				robot.getRoues().Stop();// à la fin de la ligne, le robot s'arrete
	 			}
	 		}
	 		
	 		else if (robot.getColor().getCouleurCourant()=="BLUE") {
	 			robot.RotateUntilLinePerp("BLUE", "Right");
	 			
	 			robot.parcourirLigne("BLUE");//on parcour la ligne verte
	 			if(robot.getGo()) {// si go est vrai ça veut dire que le robot n'a pas croiisé de palet
	 				robot.getRoues().RotateD("Right", 90);// vu qu'il n'y a pas de palet on tourne en deux étapes pour parcourir le reste de la ligne
	 				if(robot.getGo()) {
	 					robot.RotateUntilLine("BLUE","Right");
	 					if(robot.getGo()) {
	 						robot.parcourirLigne("BLUE");// on parcourt lereste de la ligne verte
	 						if(robot.getGo()) {
	 							robot.getRoues().RotateD("Right", 90);// le robot tourne a 90 degre pour rejoindre la ligne noire
	 							if(robot.getGo()) {
	 								robot.rejoindreLigne("BLACK", "Right");// lorsqu'il arrive a la ligne noire il tourne à droite
	 								if(robot.getGo()) {
	 									robot.parcourirLigne("BLACK");// le robot parcourt l'intégralité de la ligne noire 
	 									if(robot.getGo()) {
	 										robot.getRoues().RotateD("Left", 90);// il tourne à gauche 
	 										if(robot.getGo()) {
	 											robot.rejoindreLigne("GREEN", "Left");// il rejoint la ligne bleue
	 											if(robot.getGo()) {
	 												robot.parcourirLigne("GREEN");// il parcourt la ligne bleue
	 											}	
	 										}
	 									}
	 								}
	 							}
	 						}
	 					}
	 				}
	 				robot.getRoues().Stop();// à la fin de la ligne, le robot s'arrete
	 			}
	 		}
	 		
	 		if(!robot.getGo()) {
	 			robot.getRoues().RotateToZero();
	 			robot.stopCampOpp(EndCamp, StartCamp);
	 		}
         }
 	}	
 }



