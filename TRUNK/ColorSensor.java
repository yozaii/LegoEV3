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
	float[] colorSample; //tableau qui permettra de recuperer 3 valeur RGB
	
	public ColorSensor () {
		Port s2 = LocalEV3.get().getPort("S2"); // le capteur de couleur est branché au port 2
		colorSensor = new EV3ColorSensor(s2);
		colorProvider = colorSensor.getRGBMode();// le capteur est en mode RGB
		colorSample = new float[colorProvider.sampleSize()]; //colorSample est un tableau de taille 3 (la taille de l'echantillion)
		
		while(Button.ESCAPE.isUp()) { // tant que le bouton escape n'est pas touché prendre un échantillion grace colorProvider et rentrer les valeurs dans colorSample.
			colorProvider.fetchSample(colorSample, 0);
			System.out.println("R" + colorSample[0]);
			System.out.println("G" + colorSample[1]);
			System.out.println("B" + colorSample[2]);
			Delay.msDelay(250);
		}
		
		colorSensor.close();
	}
	
	public String colorRenvoi() {
		String color = null;
		// chaque if permettra d'identifier une couleur si les valeurs RGB sont entre le minimum et le maximum de Rouge Vert et Bleu renvoyé (cf. tableau calibrage) les valeurs ont été prises sur la table (entre 5 et 10 par couleur)
		if(colorSample[0]>=0.146 && colorSample[0]<=0.170 && colorSample[1]>=0.054 && colorSample[1]<=0.063 && colorSample[2]>=0.31 && colorSample[2]<=0.274) {
			color = "RED";
		}
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
}
	
		
	