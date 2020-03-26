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
		robot.getRoues().MoveForwardSpeed(250f); // changed (je l'ai sortie du boucle)
		// avancer jusqu'a la ligne VERTE ou BLEUE
		do { // changed from while to do while
			robot.getColor().colorScan();
		}while ((!(robot.ToucherDeux())) && (robot.getColor().getCouleurCourant()!= "GREEN" && robot.getColor().getCouleurCourant() != "BLUE")); //changed || to &&
		robot.RotateUntilLinePerp("GREEN", "Right");
		// Si c'est VERT:
		robot.parcourirLigne("GREEN");//on parcour la ligne verte
		if(robot.getGo()) {
			robot.getRoues().RotateD("Right", 90);// vu qu'il n'y a pas de palet on tourne en deux étapes pour parcourir le reste de la ligne
			if(robot.getGo()) {
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
}