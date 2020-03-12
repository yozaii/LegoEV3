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
	private Ultrason ultrason;
	
	public Robot () {
		this.roues = new Movement();
		this.pinces = new Pinces();
		this.color = new ColorSensor();
		this.info = new StockInfo();
		Port s1 = LocalEV3.get().getPort("S1");
		this.touch = new TouchSensor(s1);
		this.ultrason = new Ultrason();
	}
	
	//Getters des objets definis dans les attributs
	public Movement getRoues() { 
		return this.roues;		
	}
	
	public Pinces getPinces() {
		return this.pinces;	
	}
	
	public TouchSensor getTouch() {
		return this.touch;
	}
	
	public ColorSensor getColor() {
		return this.color;
	}
	
	public StockInfo getInfo() {
		return this.info;
	}
	
	public Ultrason getUltrason() {
		return this.ultrason;
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
	
	public void Deposer() { //Methode deposer. Le robot ouvre ses pinces, recule et puis tourne 180 degres.
		this.roues.Stop();
		this.pinces.Open();
		this.roues.MoveBackward();
		Delay.msDelay(500);
		this.roues.RotateD("Right",180);
	}
	
	/*Methode followline avec un string couleur en argument.
	 Le Robot suit cette couleur tant que le capteur tactile n'est pas touche*/
	public void FollowLine(String followcollor) {
		do {
			this.color.colorScan(); //Prise de couleur
			this.roues.arcDirection("Right");	//Mouvement en avant avec une petite courbe a droite
			if (this.color.getCouleurCourant() != followcollor) {	//Si le robot sort de la couleur en argument
				this.roues.arcDirection("Left");	//Mouvement en avant avec une petite courbe a gauche
			}
		}
		while(!this.touch.isPressed());	//Tant que le capteur tactile n'est pas touche
		this.roues.Stop();
	}
	
	
}
