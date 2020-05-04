package real;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;



public class TestScenarioP2 {
	 

	public static void main(String[] args) {
		Robot robot = new Robot();
		System.out.println("Press any button");
		Button.waitForAnyPress();
		robot.getRoues().MoveForwardSpeed(250f); 
		// avancer jusqu'a la ligne VERTE ou BLEUE
		do { 
			robot.getColor().colorScan();
		}while ((!(robot.ToucherDeux())) && (robot.getColor().getCouleurCourant()!= "GREEN" && robot.getColor().getCouleurCourant() != "BLUE"));
		
		if(robot.getColor().getCouleurCourant() == "GREEN") {// Si c'est VERT:
			robot.RotateUntilLinePerp("GREEN", "Right");
			robot.parcourirLigne("GREEN");//on parcour la ligne verte
			if(robot.getGo()) {
				robot.getRoues().RotateD("Right", 90);// vu qu'il n'y a pas de palet on tourne en deux étapes pour parcourir le reste de la ligne
				if(robot.getGo()) { // on continue l'algorithme tant que go est vrai (voir methode parcourirLigne et rejoindreLigne)
					robot.RotateUntilLine("GREEN","Right");
					if(robot.getGo()) {
						robot.parcourirLigne("GREEN");
						if(robot.getGo()) {
							robot.getRoues().RotateD("Right", 90);
							if(robot.getGo()) {
								robot.rejoindreLigne("BLACK", "Right");
								if(robot.getGo()) {
									robot.parcourirLigne("BLACK");
									if(robot.getGo()) {
										robot.getRoues().RotateD("Left", 90);
										if(robot.getGo()) {
											robot.rejoindreLigne("BLUE", "Left");
											if(robot.getGo()) {
												robot.parcourirLigne("BLUE");
											}	
										}
									}
								}
							}
						}
					}
				}
				robot.getRoues().Stop();
			}
		}
		
		else if (robot.getColor().getCouleurCourant()=="BLUE") {// on fait la meme chose si on tombe sur du bleu
			robot.RotateUntilLinePerp("BLUE", "Right");
			robot.parcourirLigne("BLUE");//on parcour la ligne verte
			if(robot.getGo()) {
				robot.getRoues().RotateD("Right", 90);// vu qu'il n'y a pas de palet on tourne en deux étapes pour parcourir le reste de la ligne
				if(robot.getGo()) { // on continue l'algorithme tant que go est vrai (voir methode parcourirLigne et rejoindreLigne)
					robot.RotateUntilLine("BLUE","Right");
					if(robot.getGo()) {
						robot.parcourirLigne("BLUE");
						if(robot.getGo()) {
							robot.getRoues().RotateD("Right", 90);
							if(robot.getGo()) {
								robot.rejoindreLigne("BLACK", "Right");
								if(robot.getGo()) {
									robot.parcourirLigne("BLACK");
									if(robot.getGo()) {
										robot.getRoues().RotateD("Left", 90);
										if(robot.getGo()) {
											robot.rejoindreLigne("GREEN", "Left");
											if(robot.getGo()) {
												robot.parcourirLigne("GREEN");
											}	
										}
									}
								}
							}
						}
					}
				}
				robot.getRoues().Stop();
			}
		}
		
	}
}