package real;

import java.io.IOException;

import lejos.hardware.Button;

public class Calibrage {
	
	public static void main (String[] args) throws IOException {
		Robot robot = new Robot();
		System.out.println("Press any button");
		Button.waitForAnyPress();
		System.out.println("calibrage vert: 5 valeurs");
		robot.getColor().setTab("green");
		System.out.println("calibrage bleu: 5 valeurs");
		robot.getColor().setTab("blue");
		System.out.println("calibrage jaune: 5 valeurs");
		robot.getColor().setTab("yellow");
		System.out.println("calibrage blanc: 5 valeurs");
		robot.getColor().setTab("white");
		System.out.println("calibrage noir: 5 valeurs");
		robot.getColor().setTab("black");
		System.out.println("calibrage rouge: 5 valeurs");
		robot.getColor().setTab("rouge");
	}

}

