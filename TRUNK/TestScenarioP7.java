package real;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class TestScenarioP7 extends Robot{
	
	public void StopDistance(String Direction) {
		super.getRoues().MoveForwardSpeed(250f);
		do {
			super.getUltrason().DistanceScan();
			super.getColor().colorScan();
		}
		while (!super.getUltrason().DistanceLimit(0.1f) && !(super.ToucherDeux()) && super.getGo() && super.getColor().getCouleurCourant()!="WHITE");
	
		if (super.getUltrason().DistanceLimit(0.1f)) {
			super.getRoues().RotateToZero();
			super.getRoues().MoveForwardDeg(400);
			super.getRoues().RotateD(Direction, 90);
		}
		else if (super.getTouch().isPressed()) {
			super.setGo(false);
			super.getRoues().Stop();
			super.getPinces().Close();
			super.getRoues().RotateToZero();
		}
		
		if (super.getColor().getCouleurCourant()=="WHITE") {
			super.setGo(false);
		}
		
	}
	
	public void OpenClose() {
		super.getPinces().Close();
		Delay.msDelay(50);
		super.getPinces().Close();
		Delay.msDelay(50);
		super.getPinces().Open();
	}
	
	
	public static void main (String args[]) {
		
		TestScenarioP7 robot = new TestScenarioP7();
		System.out.println("Press any button");
		Button.waitForAnyPress();
		robot.CherchWhite();
		robot.getRoues().MoveForwardDeg(400);
		robot.getRoues().RotateD("Right", 90);
		do {
			robot.StopDistance("Left");
			if(robot.getGo()) {
				robot.OpenClose();
			}
			robot.StopDistance("Right");
			if(robot.getGo()) {
				robot.OpenClose();
			}
		}
		while(robot.getGo());

//On execute tous les lignes suivant apres la fin de boucle precedente (le palet est attrape)
		
		do {
			robot.getRoues().MoveForwardSpeed(250f);//Le robot avance
			robot.getColor().colorScan();//Il scan le couleur en dessous en avancant
		}
		while (robot.getColor().getCouleurCourant()!="WHITE" && robot.getColor().getCouleurCourant()!="BLACK");//Tant que les couleurs scanne ne sont pas noires ou blaches
		
		if (robot.getColor().getCouleurCourant()=="BLACK") {//Si le couleur est noire
			do {
				robot.getRoues().MoveForwardSpeed(250f);
				robot.getColor().colorScan();
			}
			while (robot.getColor().getCouleurCourant()!=EndCamp && robot.getColor().getCouleurCourant()!=StartCamp );//On avance jusqu'a la ligne de camp depart ou de camp oppose
			
			if (robot.getColor().getCouleurCourant()==StartCamp) {//Si le couleur detecte est celui du camp de depart
				robot.getRoues().RotateD("Left", 180);//On fait un demitour
				robot.StopWhite();//On avance et on pose le palet a la ligne blanche en face
			}
			else if (robot.getColor().getCouleurCourant()==EndCamp) {//Sinon si le couleur est celui du camp adverse
				robot.StopWhite();//On avance et on pose le palet a la ligne blanche en face
			}
		}
		else if (robot.getColor().getCouleurCourant()=="WHITE") {//Si le couleur est blanc
			robot.getRoues().RotateD("Left", 180);//On fait un demitour
			do {
				robot.getRoues().MoveForwardSpeed(250f);
				robot.getColor().colorScan();
			}
			while (robot.getColor().getCouleurCourant()!="BLACK");//On avance jusqu'a la ligne noire.
			do {
				robot.getRoues().MoveForwardSpeed(250f);
				robot.getColor().colorScan();
			}
			while (robot.getColor().getCouleurCourant()!=EndCamp && robot.getColor().getCouleurCourant()!=StartCamp );
			
			if (robot.getColor().getCouleurCourant()==StartCamp) {
				robot.getRoues().RotateD("Left", 180);
				robot.StopWhite();
			}
			else if (robot.getColor().getCouleurCourant()==EndCamp) {
				robot.StopWhite();
			}
		}
	}

}

