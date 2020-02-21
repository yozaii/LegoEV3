package real;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class Robot {
	Movement roues;
	Pinces pince;
	TouchSensor touch;
	ColorSensor color;
	StockInfo info;
	
	public Robot () {
		this.roues = new Movement();
		this.pince = new Pinces();
		this.color = new ColorSensor();
		this.info = new StockInfo();
		Port s1 = LocalEV3.get().getPort("S1");
		this.touch = new TouchSensor(s1);
	}
	
	public boolean ToucherDeux(){ //Si capteur tactile touche > 500 ms
		boolean t1 = false;
		boolean t2 = false;
		if(touch.isPressed()) {
			t1 = true;
		}
		Delay.msDelay(50);
		if (touch.isPressed()) {
			t2 = true;
		}
		if (t1 == true && t2 == true) {
			return true;
			
		}
		else return false;
		
	
	}
	
	public void Attrape() { //Methode attraper
		boolean b;
		do {
			b=this.ToucherDeux();
		}
		while (b!=true);
		this.roues.Stop();
		pince.Close();
	}
	
	
}
