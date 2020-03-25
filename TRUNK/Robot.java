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
	private boolean go;
	
public Robot () {
	this.roues = new Movement();
	this.pinces = new Pinces();
	this.color = new ColorSensor();
	this.info = new StockInfo();
	Port s1 = LocalEV3.get().getPort("S1");
	this.touch = new TouchSensor(s1);
	this.ultrason = new Ultrason();
	this.go = true;
}
	
	//Getters des objets definis dans les attributs
	
public boolean getGo() {
	return this.go;
}

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
	Delay.msDelay(150);	//Un delai de 50ms apres que t1 est touche
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
	//Ce methode marche que si on est perpendiculaire au couleur desire
public void RotateUntilLinePerp(String Color, String Direction) {	//Argument Color pour choisir couleur desire, et Direction pour choisir la direction de rotation
	this.getRoues().Stop();
	this.getRoues().MoveForwardDeg(180);
	
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
		Delay.msDelay(20);
		this.getRoues().Stop();
		this.getRoues().setOrrien(90);
	}
}
	
public void RotateUntilLine(String Color, String Direction) {	//Argument Color pour choisir couleur desire, et Direction pour choisir la direction de rotation
		
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
		Delay.msDelay(20);
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
	while(!this.ToucherDeux() && (!(this.getUltrason().DistanceLimit(0.1f))));	//Tant que le capteur tactile n'est pas touche
	this.roues.Stop();
}

	
	
public void StopWhite() {
	this.getRoues().MoveForwardSpeed(200f);
	do {
		this.getColor().colorScan();
	}
	while (this.getColor().getCouleurCourant()!="WHITE");
	Delay.msDelay(500);
	this.Deposer();
	
}

public void rejoindreLigne(String couleur, String sens) {// permet de rejoindre une ligne d'une certaine couleur, s'il y a une palet le robot l'attrape sinon de tourner sur la ligne
	do {
		this.getRoues().MoveForwardSpeed(300f);
	this.getColor().colorScan();
	}
	while (!(this.ToucherDeux()) && (this.getColor().getCouleurCourant()!= couleur) && Button.ESCAPE.isUp() ); //changed from isDown to isUp, changed from || to &&
		
	if(this.ToucherDeux()) {
		this.roues.Stop(); 
		this.getPinces().Close(); //attrape le palet 
		this.getRoues().RotateToZero();// tourne dans la direction du camp opposé
		this.StopWhite(); 
		this.go = false; // go = false donc bloque les actions future jusqu'a la prochaine autorisation
	}
		
	else if (Button.ESCAPE.isDown()) {
		this.getRoues().Stop();
	}
		
	else if(this.getColor().getCouleurCourant()== couleur) {
		this.RotateUntilLinePerp(couleur, sens); 
	}
	
}


public void parcourirLigne(String couleur) { 
	
	this.FollowLine(couleur);// suit la ligne tant qu'il n'y a pas de palet 
	if(this.getTouch().isPressed())	 
	{
		this.getRoues().Stop();
		this.getPinces().Close();;
		this.roues.RotateToZero();
		this.StopWhite(); //s'arrete quand il arrive a la ligne blanche
		this.go = false; // met le go en false pour que si dans une boucle, le robot ne coutinue pas alors qu'il a attrapé le palet
	}
	
}
	
	
}


