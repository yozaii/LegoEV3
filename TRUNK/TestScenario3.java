package scénario3;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;
import p3.Robot;

public class TestScenario3 {
	 public static void main(String[] args) {
         Robot robot = new Robot();
         System.out.println("Press any button");
         Button.waitForAnyPress();
         robot.CherchWhite(); // permet au robot de rejoindre le ligne blanche
         do { 
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


