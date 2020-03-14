package real;

import lejos.hardware.Button;
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
			t1 = true;	//si capteur tactile est touche t1 renvoie true
		}
		Delay.msDelay(50);	//Un delai de 50ms apres que t1 est touche
		if (touch.isPressed()) {
			t2 = true;	//Si capteur tactile est touche apres ces 50ms t2 renvoie true
		}
		if (t1 == true && t2 == true) {//Si les deux valeurs t1,t2 ont renvoye true,
			return true;	//ca signifie que le capteur etait touche pour 50ms et on retourne true
		}
		else return false; //sinon on retourne false
		
	
	}
	
	public void Attrape() { //Méthode attraper
		boolean b;	//Un booleen qui va prendre la valeur de la methode ToucherDeux
		do {
			b=this.ToucherDeux(); //Une boucle pour verifier a tout moment la valeur de ToucherDeux
		}
		while (b!=true);	//Tant que ToucherDeux est faux, on continue la boucle
		this.roues.Stop();	//Si ToucherDeux est vrai, le Robot s'arrete
		pinces.Close();	//Les pinces ferment
	}
	
	public void Deposer() { //Methode deposer. Le robot ouvre ses pinces, recule et puis tourne 180 degres.
		this.roues.Stop();	//Le robot s'arrete
		this.pinces.Open();	//Le robot ouvre ses pinces
		this.roues.MoveBackward();
		Delay.msDelay(500);	//Le robot recule pour 500ms
		this.roues.RotateD("Right",180);	//Robot fait un demi-tour
	}
	
	//Une methode ou le Robot tourne autour de lui-meme jusqu'a qu'il capte le couleur passe en argument
	public void RotateUntilLine(String Color, String Direction) {	//Argument Color pour choisir couleur desire, et Direction pour choisir la direction de rotation
		Delay.msDelay(200);
		this.getRoues().Stop();
		
		if(Direction=="Left"){
			this.getRoues().RotateCounterClockwise();
			do {
				this.getColor().colorScan();
			}
			while(this.getColor().getCouleurCourant()!=Color && Button.ESCAPE.isUp());
			this.getRoues().Stop();
			this.getRoues().setOrrien(-90);
		}
		else if(Direction=="Right"){
			this.getRoues().RotateClockwise();
			do {
				this.getColor().colorScan();
			}
			while(this.getColor().getCouleurCourant()!=Color && Button.ESCAPE.isUp());
			Delay.msDelay(100);
			this.getRoues().Stop();
			this.getRoues().setOrrien(90);
		}
	}
	
	/*Methode followline avec un string couleur en argument.
	 Le Robot suit cette couleur tant que le capteur tactile n'est pas touche*/
	public void FollowLine(String followcollor) {
		do {
			this.ultrason.DistanceScan();
			this.color.colorScan(); //Prise de couleur
			this.roues.arcDirection("Right");	//Mouvement en avant avec une petite courbe a droite
			if (this.color.getCouleurCourant() != followcollor) {	//Si le robot sort de la couleur en argument
				this.roues.arcDirection("Left");	//Mouvement en avant avec une petite courbe a gauche
			}
		}
		while(!this.ToucherDeux());	//Tant que le capteur tactile n'est pas touche
		this.roues.Stop();
	}
	
	
}
