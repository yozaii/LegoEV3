package scenario4;

import lejos.hardware.Button;
import scénario3.Robot;

public class Testp4 {
	 Robot robot = new Robot();
     String StartCamp, EndCamp, StartColor, FirstDir = null, SecondDir = null;
     robot.getInfo().menu();
     System.out.println(robot.getInfo().getCampDep());
     if (robot.getInfo().getCampDep()=="Ouest") {// le camp est désigne au robot
             StartCamp = "BLUE";
             EndCamp ="GREEN";
     }
     else {
             StartCamp = "GREEN";
             EndCamp = "BLUE";
     }
     System.out.println("Press any button");
     Button.waitForAnyPress();
     if (robot.getInfo().getCampDep()=="Ouest") {
         robot.CherchWhite(); // permet au robot de rejoindre le ligne blanche
         do { 
             robot.getColor().colorScan();
     }while ((!(robot.ToucherDeux())) && (robot.getColor().getCouleurCourant()!= "GREEN" && robot.getColor().getCouleurCourant() != "BLUE")); 
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



}
