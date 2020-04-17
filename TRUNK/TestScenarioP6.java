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
		robot.StopWhite();
}

}

