import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;


public class TestScenarioP2 {
	 

	public static void main(String[] args) {
		Robot robot = new Robot();
		System.out.println("Press any button");
		Button.waitForAnyPress();

		while (!(robot.ToucherDeux()) && Button.ESCAPE.isUp()) {
			robot.getRoues().MoveForward();
			do {
				robot.getColor().colorScan();
			}
			while (!(robot.ToucherDeux()) && (robot.getColor().getCouleurCourant()!= "GREEN" || robot.getColor().getCouleurCourant() != "BLUE"));
			if(robot.getColor().getCouleurCourant()=="GREEN") {
				robot.RotateUntilLine("GREEN", "Right");
		
				while  ( !(robot.ToucherDeux()) && (!(robot.getUltrason().DistanceLimit(0.1f)))){     // tant que le capteur ultrason ne capte pas de mur suit la ligne verte
					robot.FollowLine("GREEN");
				}
			
					robot.getRoues().RotateD("Left", 180); // sinon on fait demi tour pour parcourir le reste de la ligne verte
			
				
				while  (!(robot.ToucherDeux()) && (!(robot.getUltrason().DistanceLimit(0.1f))))//||Ultrason<une certaine distance*/){    // tant que le capteur de touché n'est pas activé et qu'il n'y a pas de mur, on suit la ligne verte dans l'autre sens
					robot.FollowLine("GREEN");
				
	
					robot.getRoues().RotateD("Right", 90); // sinon on fait un quart de tour pour rejoindre la ligne noire 
/* ****** */
				robot.getRoues().MoveForward();
				do {
					robot.getColor().colorScan();
				}
				while (!(robot.ToucherDeux()) && (robot.getColor().getCouleurCourant()!= "BLACK" || robot.getColor().getCouleurCourant() != "BLUE"));
				if(robot.getColor().getCouleurCourant()=="BLACK") {
					robot.RotateUntilLine("BLACK", "Right");
			
					while  ( !(robot.ToucherDeux()) && (!(robot.getUltrason().DistanceLimit(0.1f)))){     // tant que le capteur ultrason ne capte pas de mur suit la ligne verte
						robot.FollowLine("BLACK");
					}
				
						robot.getRoues().RotateD("Left", 180); // sinon on fait demi tour pour parcourir le reste de la ligne verte
				
					
					while  (!(robot.ToucherDeux()) && (!(robot.getUltrason().DistanceLimit(0.1f))))//||Ultrason<une certaine distance*/){    // tant que le capteur de touché n'est pas activé et qu'il n'y a pas de mur, on suit la ligne verte dans l'autre sens
						robot.FollowLine("GREEN");
					}
		
						robot.getRoues().RotateD("Right", 90); // sinon on fait un quart de tour pour rejoindre la ligne noire 
	/* ****** */
				do {
					robot.getRoues().MoveForward();
					robot.getColor().colorScan();// on avance tant qu'on ne croise pas la ligne bleue
				}while (!(robot.ToucherDeux()) && (robot.getColor().getCouleurCourant()!="BLUE"));
				
				robot.RotateUntilLine("BLUE", "Left"); // sinon on fait demi tour pour parcourir le reste de la ligne Bleue
	
				
			}
/////////
////////
		else if (robot.getColor().couleurCourant=="BLUE") {
			
			robot.RotateUntilLine("BLUE","Left");
			
			while  (!(robot.ToucherDeux()) && (  !(robot.getUltrason().DistanceLimit(0.1f))/*||Ultrason<une certaine distanece*/)){     // tant que le capteur ultrason ne capte pas de mur suit la ligne verte
			
				robot.FollowLine("BLUE");
			}
			
				robot.getRoues().RotateD("Right", 180);; // sinon on fait demi tour pour parcourir le reste de la ligne verte
		
			
			while  (!(robot.ToucherDeux()) && ( !(robot.getUltrason().DistanceLimit(0.1f))))//||Ultrason<une certaine distance*/){    // tant que le capteur de touché n'est pas activé et qu'il n'y a pas de mur, on suit la ligne verte dans l'autre sens
				robot.FollowLine("BLUE");
			}

				robot.getRoues().RotateD("Left", 90);; // sinon on fait un quart de tour pour rejoindre la ligne noire 
			
			
			do {
				robot.getRoues().MoveForward();// on avance tant qu'on ne croise pas la ligne noire
				robot.getColor().colorScan();
			}while (!(robot.ToucherDeux()) && (robot.getColor().getCouleurCourant()!="BLACK"));
				
			robot.RotateUntilLine("BLACK", "Left");
			
			while  ( !(robot.ToucherDeux()) && (!(robot.getUltrason().DistanceLimit(0.1f))) /*||Ultrason<une certaine distance*/) {   // tant que le capteur de touché n'est pas activé et qu'il n'y a pas de mur, on suit la ligne verte dans l'autre sens
				robot.FollowLine("BLACK");
			}
			
			robot.getRoues().RotateD("Right", 90); // sinon on fait demi tour pour parcourir le reste de la ligne Bleue
			
			do {
				robot.getRoues().MoveForward();// on avance tant qu'on ne croise pas la ligne bleue
				robot.getColor().colorScan();
			}while (!(robot.ToucherDeux()) && (robot.getColor().getCouleurCourant()!="GREEN"));
			
			robot.RotateUntilLine("GREEN", "Right");; // sinon on fait demi tour pour parcourir le reste de la ligne Bleue
			robot.FollowLine("GREEN");
			
		}
			
	
	
		// si le capteur de touché est activé, on atrape le palet et on l'amene vers la ligne blanche
			robot.Attrape();
			robot.getRoues().RotateToZero();
			robot.getRoues().MoveForward();
			do {
				robot.getColor().colorScan();
			}
			while(robot.getColor().getCouleurCourant()!="WHITE");}}

		// après le while touch is pressed