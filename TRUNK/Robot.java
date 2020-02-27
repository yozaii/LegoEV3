package real;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class Robot {
	private Movement roues;
	private Pinces pinces;
	private TouchSensor touch;
	private ColorSensor color;
	private StockInfo info;
	
	public Robot () {
		this.roues = new Movement();
		this.pinces = new Pinces();
		this.color = new ColorSensor();
		this.info = new StockInfo();
		Port s1 = LocalEV3.get().getPort("S1");
		this.touch = new TouchSensor(s1);
	}
	
	/*Méthode getRoues qui retourne l'objet des roues afin de pouvoir utiliser
	ses méthodes dans une fonction main comme l'attribut roues est privé */
	public Movement getRoues() { 
		return this.roues;		
	}
	
	/*Méthode getPinces qui retourne l'objet des pinces afin de pouvoir utiliser
	ses méthodes dans une fonction main comme l'attribut pinces est privé */
	public Pinces getPinces() {
		return this.pinces;	
	}
	
	/*Méthode getTouch qui retourne l'objet de capteur tactile afin de pouvoir utiliser
	ses méthodes dans une fonction main comme l'attribut touch est privé */
	public TouchSensor getTouch() {
		return this.touch;
	}
	
	/*Méthode getColor qui retourne l'objet de capteur couleur afin de pouvoir utiliser
	ses méthodes dans une fonction main comme l'attribut colorsen est privé */
	public ColorSensor getColor() {
		return this.color;
	}
	
	/*Méthode getInfo qui retourne l'objet de StockInfo afin de pouvoir utiliser
	ses méthodes dans une fonction main comme l'attribut info est privé */
	public StockInfo getInfo() {
		return this.info;
	}
	
	/*Une méthode ToucherDeux booléenne qui renvoie vrai si le capteur tactile
	est touché, et reste touché pour 50ms*/
	public boolean ToucherDeux(){ 
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
	
	public void Attrape() { //Méthode attraper
		boolean b;
		do {
			b=this.ToucherDeux();
		}
		while (b!=true);
		this.roues.Stop();
		pinces.Close();
	}
	
	
}
