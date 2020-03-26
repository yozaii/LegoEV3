package real;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;


public class TestScenarioP5 {
	
	public static void main (String[] args) {
		
		Robot robot = new Robot();
		String StartCamp, EndCamp, StartColor, FirstDir = null, SecondDir = null;
		robot.getInfo().menu();
		System.out.println(robot.getInfo().getCampDep());
		if (robot.getInfo().getCampDep()=="Ouest") {
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
			robot.getRoues().MoveForwardSpeed(200f);
			do {
				robot.getColor().colorScan();
			}
			while ((robot.getColor().getCouleurCourant()!="YELLOW") && (robot.getColor().getCouleurCourant()!="RED") );
			if (robot.getColor().getCouleurCourant()=="YELLOW"){
				while(!robot.ToucherDeux());
				robot.getRoues().Stop();
				robot.getPinces().Close();
				robot.getRoues().RotateD("Right", 90);
				robot.getRoues().MoveForwardSpeed(200f);
				do {
					robot.getUltrason().DistanceScan();
				}while(!robot.getUltrason().DistanceLimit(0.1f));
				robot.getRoues().RotateToZero();
				robot.StopWhite();
				FirstDir = "Left";
				SecondDir = "Right";

			}
			else if (robot.getColor().getCouleurCourant()=="RED") {
				while(!robot.ToucherDeux());
				robot.getRoues().Stop();
				robot.getPinces().Close();
				robot.getRoues().RotateD("Left", 90);
				robot.getRoues().MoveForwardSpeed(200f);
				do {
					robot.getUltrason().DistanceScan();
				}while(!robot.getUltrason().DistanceLimit(0.1f));
				robot.getRoues().RotateToZero();
				robot.StopWhite();
				FirstDir = "Right";
				SecondDir = "Left";

			}
		}
		
		
		else if (robot.getInfo().getCampDep()=="Est") {
			robot.getRoues().MoveForwardSpeed(200f);
			do {
				robot.getColor().colorScan();
			}
			while ((robot.getColor().getCouleurCourant()!="YELLOW") && (robot.getColor().getCouleurCourant()!="RED") );
			if (robot.getColor().getCouleurCourant()=="YELLOW"){
				while(!robot.ToucherDeux());
				robot.getRoues().Stop();
				robot.getPinces().Close();
				robot.getRoues().RotateD("Left", 90);
				robot.getRoues().MoveForwardSpeed(200f);
				do {
					robot.getUltrason().DistanceScan();
				}while(!robot.getUltrason().DistanceLimit(0.1f));
				robot.getRoues().RotateToZero();
				robot.StopWhite();
				FirstDir = "Right";
				SecondDir = "Left";

			}
			else if (robot.getColor().getCouleurCourant()=="RED") {
				while(!robot.ToucherDeux());
				robot.getRoues().Stop();
				robot.getPinces().Close();
				robot.getRoues().RotateD("Right", 90);
				robot.getRoues().MoveForwardSpeed(200f);
				do {
					robot.getUltrason().DistanceScan();
				}while(!robot.getUltrason().DistanceLimit(0.1f));
				robot.getRoues().RotateToZero();
				robot.StopWhite();
				FirstDir = "Left";
				SecondDir = "Right";
			}
			
		}
		
		robot.BiggerMethod(EndCamp, FirstDir);
		robot.getInfo().setExistePalet(true);
		robot.BiggerMethod("BLACK", SecondDir);
		robot.getInfo().setExistePalet(true);
		robot.BiggerMethod(StartCamp, FirstDir);



	}
}