package real;

import lejos.hardware.Button;



public class TestScenarioP4 {
	public static void main (String[] args) {
        
        Robot robot = new Robot();
        
        String StartCamp, EndCamp;
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
        if (robot.getInfo().getCampDep()=="EST") {//si camp de depart EST
        	 robot.getRoues().MoveForwardSpeed(200f);//le robot avance
             do { // le robot avance et renvoi la couleur tant que le capteur tacttile nest pas touche, la couleur captee nest pas verte ni bleue
                     robot.getColor().colorScan();
             }while ((!(robot.ToucherDeux())) && (robot.getColor().getCouleurCourant()!= "GREEN" && robot.getColor().getCouleurCourant() != "BLUE")); 
             if(robot.getColor().getCouleurCourant()=="BLUE"){// si la couleur captee est bleue
            	 robot.RchrchCamp(); // permet au robot de rejoindre le camp de depart, si il croise un palet il le depose dans le camp adverse.
            	 robot.getRoues().MoveForwardSpeed(200f);// le robot avance
            	 do {// le robot avance et renvoi la couleur tant que celle ci nest pas verte
            		 robot.getColor().colorScan();
            	 }
            	 while(robot.getColor().getCouleurCourant()!="GREEN");
             }
                 robot.RotateUntilLinePerp("GREEN", "Right");
         
                 robot.parcourirLigne("GREEN");//on parcour la ligne verte
                 if(robot.getGo()) {// si go est vrai ca veut dire que le robot n'a pas croise de palet
                         robot.getRoues().RotateD("Right", 90);// vu qu'il n'y a pas de palet on tourne en deux etapes pour parcourir le reste de la ligne
                         if(robot.getGo()) {
                                 robot.RotateUntilLine("GREEN","Right");
                                 if(robot.getGo()) {
                                         robot.parcourirLigne("GREEN");// on parcourt le reste de la ligne verte
                                         if(robot.getGo()) {
                                                 robot.getRoues().RotateD("Right", 90);// le robot tourne a 90 degre pour rejoindre la ligne noire
                                                 if(robot.getGo()) {
                                                         robot.rejoindreLigne("BLACK", "Right");// lorsqu'il arrive a la ligne noire il tourne a droite
                                                         if(robot.getGo()) {
                                                                 robot.parcourirLigne("BLACK");// le robot parcourt l'integralite de la ligne noire 
                                                                 if(robot.getGo()) {
                                                                         robot.getRoues().RotateD("Left", 90);// il tourne ? gauche 
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
                         robot.getRoues().Stop();// ? la fin de la ligne, le robot s'arrete
                 }
         
             
     
        }
        else if (robot.getInfo().getCampDep()=="Ouest") {//si le camp de depart est OUEST.
        	
       	 robot.CherchWhite(); //permet au robot de rejoindre la ligne blanche.
       	 robot.getRoues().MoveForwardSpeed(200f);//le robot avance
            do { //le robot avance et renvoi la couleur tant quela capteur tactile nest pas touche, la couleur captee nest pas bleue ni verte. 
                    robot.getColor().colorScan();
            }while ((!(robot.ToucherDeux())) && (robot.getColor().getCouleurCourant()!= "GREEN" && robot.getColor().getCouleurCourant() != "BLUE")); 
            if(robot.getColor().getCouleurCourant()=="GREEN"){// si le robot capte la couleur verte.
           	 robot.RchrchCamp(); // permet au robot de rejoindre le camp de depart, si il croise un palet il le depose dans le camp adverse.
           	 robot.getRoues().MoveForwardSpeed(200f);//le robot avance.
           	 do {// le robot avance et renvoi la couleur tant que celle ci nest pas bleue.
           		 robot.getColor().colorScan();
           	 }
           	 while(robot.getColor().getCouleurCourant()!="BLUE");
            }
                robot.RotateUntilLinePerp("BLUE", "Right");
        
                robot.parcourirLigne("BLUE");//on parcour la ligne bleue
                if(robot.getGo()) {// si go est vrai ca veut dire que le robot n'a pas croise de palet
                        robot.getRoues().RotateD("Right", 90);// vu qu'il n'y a pas de palet on tourne en deux etapes pour parcourir le reste de la ligne
                        if(robot.getGo()) {
                                robot.RotateUntilLine("BLUE","Right");
                                if(robot.getGo()) {
                                        robot.parcourirLigne("BLUE");// on parcourt lereste de la ligne bleue
                                        if(robot.getGo()) {
                                                robot.getRoues().RotateD("Right", 90);// le robot tourne a 90 degre pour rejoindre la ligne noire
                                                if(robot.getGo()) {
                                                        robot.rejoindreLigne("BLACK", "Right");// lorsqu'il arrive a la ligne noire il tourne a droite
                                                        if(robot.getGo()) {
                                                                robot.parcourirLigne("BLACK");// le robot parcourt l'integralite de la ligne noire 
                                                                if(robot.getGo()) {
                                                                        robot.getRoues().RotateD("Left", 90);// il tourne a gauche 
                                                                        if(robot.getGo()) {
                                                                                robot.rejoindreLigne("GREEN", "Left");// il rejoint la ligne verte
                                                                                if(robot.getGo()) {
                                                                                        robot.parcourirLigne("GREEN");// il parcourt la ligne verte
                                                                                }        
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                        robot.getRoues().Stop();// a la fin de la ligne, le robot s'arrete
                }
        
            
    
       }
	}
}