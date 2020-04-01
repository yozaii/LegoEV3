package sc�nario3;
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
         }while ((!(robot.ToucherDeux())) && (robot.getColor().getCouleurCourant()!= "GREEN" && robot.getColor().getCouleurCourant() != "BLUE")); 
         
         if(robot.getColor().getCouleurCourant()=="GREEN") {        // Si c'est VERT:
                 robot.RotateUntilLinePerp("GREEN", "Right");
         
                 robot.parcourirLigne("GREEN");//on parcour la ligne verte
                 if(robot.getGo()) {// si go est vrai �a veut dire que le robot n'a pas croiis� de palet
                         robot.getRoues().RotateD("Right", 90);// vu qu'il n'y a pas de palet on tourne en deux �tapes pour parcourir le reste de la ligne
                         if(robot.getGo()) {
                                 robot.RotateUntilLine("GREEN","Right");
                                 if(robot.getGo()) {
                                         robot.parcourirLigne("GREEN");// on parcourt lereste de la ligne verte
                                         if(robot.getGo()) {
                                                 robot.getRoues().RotateD("Right", 90);// le robot tourne a 90 degre pour rejoindre la ligne noire
                                                 if(robot.getGo()) {
                                                         robot.rejoindreLigne("BLACK", "Right");// lorsqu'il arrive a la ligne noire il tourne � droite
                                                         if(robot.getGo()) {
                                                                 robot.parcourirLigne("BLACK");// le robot parcourt l'int�gralit� de la ligne noire 
                                                                 if(robot.getGo()) {
                                                                         robot.getRoues().RotateD("Left", 90);// il tourne � gauche 
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
                         robot.getRoues().Stop();// � la fin de la ligne, le robot s'arrete
                 }
         }
         
         else if (robot.getColor().getCouleurCourant()=="BLUE") {
                 robot.RotateUntilLinePerp("BLUE", "Right");
                 
                 robot.parcourirLigne("BLUE");//on parcour la ligne verte
                 if(robot.getGo()) {// si go est vrai �a veut dire que le robot n'a pas croiis� de palet
                         robot.getRoues().RotateD("Right", 90);// vu qu'il n'y a pas de palet on tourne en deux �tapes pour parcourir le reste de la ligne
                         if(robot.getGo()) {
                                 robot.RotateUntilLine("BLUE","Right");
                                 if(robot.getGo()) {
                                         robot.parcourirLigne("BLUE");// on parcourt lereste de la ligne verte
                                         if(robot.getGo()) {
                                                 robot.getRoues().RotateD("Right", 90);// le robot tourne a 90 degre pour rejoindre la ligne noire
                                                 if(robot.getGo()) {
                                                         robot.rejoindreLigne("BLACK", "Right");// lorsqu'il arrive a la ligne noire il tourne � droite
                                                         if(robot.getGo()) {
                                                                 robot.parcourirLigne("BLACK");// le robot parcourt l'int�gralit� de la ligne noire 
                                                                 if(robot.getGo()) {
                                                                         robot.getRoues().RotateD("Left", 90);// il tourne � gauche 
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
                         robot.getRoues().Stop();// � la fin de la ligne, le robot s'arrete
                 }
         }
 }        
}