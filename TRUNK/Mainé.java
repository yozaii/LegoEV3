import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;


public class main {
	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Robot robot = new Robot();
		System.out.println("Press any button");
		Button.waitForAnyPress();

		while (!(robot.getTouch().isPressed())) {
		
		while (robot.getColor().colorRenvoi()!= "GREEN" || robot.getColor().colorRenvoi() != "BLUE") {
			robot.getRoues().MoveForward();
		}
		
		if(robot.getColor().couleurCourant="GREEN") {
			robot.getRoues().RotateClockwise90();
	
			while  ( true/*||Ultrason<une certaine distanece*/){     // tant que le capteur ultrason ne capte pas de mur suit la ligne verte
			
				robot.FollowLine("GREEN");
			}
		
				robot.getRoues().RotateCounterClockwise180(); // sinon on fait demi tour pour parcourir le reste de la ligne verte
		
			
			while  (true)//||Ultrason<une certaine distance*/){    // tant que le capteur de touché n'est pas activé et qu'il n'y a pas de mur, on suit la ligne verte dans l'autre sens
				robot.FollowLine("GREEN");
			}

				robot.getRoues().RotateClockwise90(); // sinon on fait un quart de tour pour rejoindre la ligne noire 
			
			
			do {
				robot.getRoues().MoveForward();// on avance tant qu'on ne croise pas la ligne noire
			}while (robot.getColor().couleurCourant()!="BLACK");
				
			robot.getRoues().RotateClockwise90();
			
			while  (!(robot.getTouch().isPressed()) /*||Ultrason<une certaine distance*/){   // tant que le capteur de touché n'est pas activé et qu'il n'y a pas de mur, on suit la ligne verte dans l'autre sens
				robot.FollowLine("BLACK");
			}
			
			robot.getRoues().RotateCounterClockwise90(); // sinon on fait demi tour pour parcourir le reste de la ligne Bleue
			
			do {
				robot.getRoues().MoveForward();// on avance tant qu'on ne croise pas la ligne bleue
			}while (robot.getColor().colorRenvoi()!="BLUE");
			
			robot.getRoues().RotateCounterClockwise90(); // sinon on fait demi tour pour parcourir le reste de la ligne Bleue

			
	}
		
		else  if (robot.couleurCourant()="BLUE") {
			
			robot.getRoues().RotateCounterClockwise90();
			
			while  ( true/*||Ultrason<une certaine distanece*/){     // tant que le capteur ultrason ne capte pas de mur suit la ligne verte
			
				robot.FollowLine("BLUE");
			}
		}	
				robot.getRoues().RotateClockwise180(); // sinon on fait demi tour pour parcourir le reste de la ligne verte
		
			
			while  (true)//||Ultrason<une certaine distance*/){    // tant que le capteur de touché n'est pas activé et qu'il n'y a pas de mur, on suit la ligne verte dans l'autre sens
				robot.FollowLine("BLUE");
			}

				robot.getRoues().RotateCounterClockwise90(); // sinon on fait un quart de tour pour rejoindre la ligne noire 
			
			
			do {
				robot.getRoues().MoveForward();// on avance tant qu'on ne croise pas la ligne noire
			}while (robot.getColor().colorRenvoi()!="BLACK");
				
			robot.getRoues().RotateCounterClockwise90();
			
			while  (!(robot.getTouch().isPressed()) /*||Ultrason<une certaine distance*/){   // tant que le capteur de touché n'est pas activé et qu'il n'y a pas de mur, on suit la ligne verte dans l'autre sens
				robot.FollowLine("BLACK");
			}
			
			robot.getRoues().RotateClockwise90(); // sinon on fait demi tour pour parcourir le reste de la ligne Bleue
			
			do {
				robot.getRoues().MoveForward();// on avance tant qu'on ne croise pas la ligne bleue
			}while (robot.couleurCourant().colorRenvoi()!="GREEN");
			
			robot.getRoues().RotateClockwise90(); // sinon on fait demi tour pour parcourir le reste de la ligne Bleue
			
			}
	
	
		if ((robot.getTouch().isPressed())){// si le capteur de touché est activé, on atrape le palet et on l'amene vers la ligne blanche
			robot.Attrape();
			robot.getRoues().RotateClockwise90();
			while(robot.couleurCourant().colorRenvoi()!="WHITE") {
				robot.getRoues().MoveForward();
		
		// apprès le while touch is pressed
		while(robot.getColor().colorRenvoi()!="WHITE") {
			robot.getRoues().MoveForward();
		}
		robot.Deposer();// on depose le palet derriere la ligne blanche et on fait demi-tour
		robot.getRoues().RotateCounterClockwise180();
	}
	
}
}

