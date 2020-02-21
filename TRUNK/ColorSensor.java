package real;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ColorSensor {
	
	EV3ColorSensor colorSensor;
	SampleProvider colorProvider;
	float[] colorSample;
	
	public static void main(String[] args) {
		new ColorSensor();
	}
	
	public ColorSensor () {
		Port s2 = LocalEV3.get().getPort("S2");
		colorSensor = new EV3ColorSensor(s2);
		colorProvider = colorSensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
		
		while(Button.ESCAPE.isUp()) {
			colorProvider.fetchSample(colorSample, 0);
			System.out.println("R" + colorSample[0]);
			System.out.println("G" + colorSample[1]);
			System.out.println("B" + colorSample[2]);
			Delay.msDelay(250);
		}
		
		colorSensor.close();
	}
}
