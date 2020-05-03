package real;

import java.io.IOException;

import lejos.hardware.Button;

public class Calibrage {
	
	public static void main (String[] args) throws IOException {
		Robot robot = new Robot();
		System.out.println("Press any button");
		Button.waitForAnyPress();
		System.out.println("Calibrage vert: 5 valeurs");
		robot.getColor().setTab("green");
		System.out.println("Calibrage bleu: 5 valeurs");
		robot.getColor().setTab("blue");
		System.out.println("Calibrage jaune: 5 valeurs");
		robot.getColor().setTab("yellow");
		System.out.println("Calibrage blanc: 5 valeurs");
		robot.getColor().setTab("white");
		System.out.println("Calibrage noir: 5 valeurs");
		robot.getColor().setTab("black");
		System.out.println("Calibrage rouge: 5 valeurs");
		robot.getColor().setTab("rouge");
	}

}

