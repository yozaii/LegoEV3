package real;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor {
	
	EV3ColorSensor colorSensor;
	SampleProvider colorProvider;
	float[] colorSample;
	String couleurCourant; //Hier
	
	public ColorSensor () {
		Port s2 = LocalEV3.get().getPort("S2");
		colorSensor = new EV3ColorSensor(s2);
		colorProvider = colorSensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
		couleurCourant = null; //Hier
	
	}
	
	public void ColorTesting() {
		
		while(Button.ESCAPE.isUp()) {
			colorProvider.fetchSample(colorSample, 0);
			System.out.println("R" + colorSample[0]);
			System.out.println("G" + colorSample[1]);
			System.out.println("B" + colorSample[2]);
			Button.waitForAnyPress();
		}
		
		colorSensor.close();
	}
	
	public float[] echantillion() {
		colorProvider.fetchSample(colorSample, 0);
		return colorSample;
	}
	
	public String colorRenvoi() {
		String color = null;
		// chaque if permettra d'identifier une couleur si les valeurs RGB sont entre le minimum et le maximum de Rouge Vert et Bleu renvoyé (cf. tableau calibrage)
		if(colorSample[0]>=0.1 && colorSample[0]<=0.190 && colorSample[1]>=0.03 && colorSample[1]<=0.1 && colorSample[2]>=0.01 && colorSample[2]<=0.06) {
			color = "RED";
		}
		else if (colorSample[0]>=0.04 && colorSample[0]<=0.08 && colorSample[1]>=0.204 && colorSample[1]<=0.25 && colorSample[2]>=0.03 && colorSample[2]<=0.07) {
			color = "GREEN";
		}
		else if (colorSample[0]>=0.020 && colorSample[0]<=0.060 && colorSample[1]>=0.160 && colorSample[1]<=0.220 && colorSample[2]>=0.1 && colorSample[2]<=0.15) {
			color = "BLUE";
		}
		else if (colorSample[0]>=0.160 && colorSample[0]<=0.290 && colorSample[1]>=0.290 && colorSample[1]<=0.38 && colorSample[2]>=0.03 && colorSample[2]<=0.08) {
			color = "YELLOW";
		}
		else if (colorSample[0]>=0.01 && colorSample[0]<=0.04 && colorSample[1]>=0.01 && colorSample[1]<=0.07 && colorSample[2]>=0.006 && colorSample[2]<=0.24) {
			color = "BLACK";
		}
		else if(colorSample[0]>=0.180 && colorSample[0]<=0.330 && colorSample[1]>=0.260 && colorSample[1]<=0.480 && colorSample[2]>=0.120 && colorSample[2]<=0.280) {
			color = "WHITE";
		}
		else if(colorSample[0]>=0.113 && colorSample[0]<=0.131 && colorSample[1]>=0.175 && colorSample[1]<=0.195 && colorSample[2]>=0.087 && colorSample[2]<=0.1) {
			color = "GREY";
		}

		System.out.println(color);
		return color; 
	}
	
	//Hier
	/*Méthode colorScan qui renvoie le String coulerCourant, qui est le couleur en dessous du
	robot au moment d'appel du fonction */
	public void colorScan() {
		this.echantillion();
		couleurCourant = this.colorRenvoi();
	}
	
	//Hier
	public String getCouleurCourant() {
		return couleurCourant;
	}
}
