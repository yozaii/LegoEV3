package real;

import java.io.IOException;

import lejos.hardware.Button;

public class TestScenarioP1 {
	
	public static void main (String[] args)throws IOException {
		Robot robot = new Robot();
		System.out.println("Press any button");
		Button.waitForAnyPress();
		while(Button.ESCAPE.isUp()) {
			robot.getColor().colorScan();
			Button.waitForAnyPress();
		}
	}
}

