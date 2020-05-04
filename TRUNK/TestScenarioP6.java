package real;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class TestScenarioP6 extends Robot{
	
	/*Une méthode qui dirige le robot vers la fin de surface (Jusqu'à rencontre du mur). Il tourne vers la direction passé en argument et avance
	*(les roues font 400 degrés en avant). Puis il tourne vers l'autre mur. Le robot s'arrete si il detecte une ligne blanche.
	*Cette methode sert à parcourir toute la surface en faisant des "zigzag".
	*/
	public void StopDistance(String Direction) {
		super.getRoues().MoveForwardSpeed(super.getRoues().getVitesse());//robot avance.
		do {	//le robot scan la distance devant lui et la couleur en dessous.
			super.getUltrason().DistanceScan();
			super.getColor().colorScan();
		}
		//Tant que la distance devant lui est >10 || un palet n'est pas touché || go est faux || couleure en dessous == blanche
		while (!super.getUltrason().DistanceLimit(0.1f) && !(super.ToucherDeux()) && super.getGo() && super.getColor().getCouleurCourant()!="WHITE");
	
		//Cas ou le robot rencontre un mur
		if (super.getUltrason().DistanceLimit(0.1f)) {
			super.getRoues().RotateToZero();
			super.getRoues().MoveForwardDeg(400);
			super.getRoues().RotateD(Direction, 90);
		}
		
		//Cas ou palet est attrapé
		else if (super.getTouch().isPressed()) {
			super.setGo(false);
			super.getRoues().Stop();
			super.getPinces().Close();
			super.getRoues().RotateToZero();
		}
		
		//Cas ou la couleure en dessous == blanche
		if (super.getColor().getCouleurCourant()=="WHITE") {
			super.setGo(false);//Go est mis a false
		}
		
	}
	
	//Une méthode ou le robot ferme et puis ouvre ses pinces.
	public void OpenClose() {
		super.getPinces().Close();
		Delay.msDelay(50);
		super.getPinces().Open();
	}
	
	
	public static void main (String args[]) {
		
		String StartCamp, EndCamp, StartColor;
		TestScenarioP6 robot = new TestScenarioP6();
		robot.getInfo().menu();
		System.out.println("Press any button");
		Button.waitForAnyPress();
		System.out.println(robot.getInfo().getCampDep()); //On affiche le camp de depart
		if (robot.getInfo().getCampDep()=="Ouest") { //Si camp de depart = Ouest
			StartCamp = "BLUE";	//Alors camp depart est cote bleu
			EndCamp ="GREEN";	//Et camp opposé est coté vert
		}
		else {
			StartCamp = "GREEN";
			EndCamp = "BLUE";
		}
		robot.CherchWhite();//Le robot rejoin une ligne blanche.
		robot.getRoues().RotateD("Left", 90);//Il tourne arbitrairement 90 degres à gauche.
		robot.getRoues().MoveForward();
		robot.FollowLine("WHITE");//Il suit la ligne blanche
		if (robot.getTouch().isPressed()) {//Si capteur tactile est touché
			robot.setGo(false);
			robot.getRoues().Stop();
			robot.getPinces().Close();
			robot.getRoues().RotateToZero();
		}
		if (robot.getGo()) {//Si Go est toujours vrai (palet pas attrapé)
			robot.getRoues().RotateToZero();
			robot.getRoues().MoveForwardDeg(300);
			robot.getRoues().RotateD("Right", 90);
			robot.OpenClose();
		}
		do {
			robot.StopDistance("Left");
			if(robot.getGo()) {
				robot.OpenClose();
			}
			robot.StopDistance("Right");
			if(robot.getGo()) {
				robot.OpenClose();
			}
		}//Le robot execute cette boucle de zigzag jusqu'à que go devient false(A l'arrive d'une ligne blanche ou l'attrape d'un palet)
		while(robot.getGo());
		
	}

}



