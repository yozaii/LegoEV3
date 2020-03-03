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
		if(colorSample[0]>=0.146 && colorSample[0]<=0.170 && colorSample[1]>=0.054 && colorSample[1]<=0.063 && colorSample[2]>=0.023 && colorSample[2]<=0.03) {
			color = "RED";
		}		}
		else if (colorSample[0]>=0.063 && colorSample[0]<=0.072 && colorSample[1]>=0.204 && colorSample[1]<=0.225 && colorSample[2]>=0.041 && colorSample[2]<=0.051) {
			color = "GREEN";
		}
		else if (colorSample[0]>=0.042 && colorSample[0]<=0.047 && colorSample[1]>=0.184 && colorSample[1]<=0.209 && colorSample[2]>=0.113 && colorSample[2]<=0.144) {
			color = "BLUE";
		}
		else if (colorSample[0]>=0.222 && colorSample[0]<=0.258 && colorSample[1]>=0.321 && colorSample[1]<=0.373 && colorSample[2]>=0.051 && colorSample[2]<=0.06) {
			color = "YELLOW";
		}
		else if (colorSample[0]>=0.013 && colorSample[0]<=0.032 && colorSample[1]>=0.017 && colorSample[1]<=0.061 && colorSample[2]>=0.006 && colorSample[2]<=0.229) {
			color = "BLACK";
		}
		else if(colorSample[0]>=0.219 && colorSample[0]<=0.294 && colorSample[1]>=0.333 && colorSample[1]<=0.463 && colorSample[2]>=0.162 && colorSample[2]<=0.241) {
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
