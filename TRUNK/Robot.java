package real;

import java.io.IOException;

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
	private boolean go; //sert de "feu vert" pour les méthodes rejoidreLigne() et parcourirLigne() (voir P2)
	private float speed;	//Cette valeur va modifier les MoveForwardSpeed(float) qui se trouvent dans la classe
	
	public Robot () {
		this.roues = new Movement();
		this.pinces = new Pinces();
		this.color = new ColorSensor();
		this.info = new StockInfo();
		Port s1 = LocalEV3.get().getPort("S1");
		this.touch = new TouchSensor(s1);
		this.ultrason = new Ultrason();
		this.go = true;
		//Les geTabs font une lecture des fichiers avec des valeurs de couleurs.
		this.getColor().getTabCalibrage("RED");
		this.getColor().getTabCalibrage("BLUE");
		this.getColor().getTabCalibrage("GREEN");
		this.getColor().getTabCalibrage("YELLOW");
		this.getColor().getTabCalibrage("BLACK");
		this.getColor().getTabCalibrage("WHITE");
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
	
	
	//Setters des attribut
	public void setGo(boolean bool) {
		this.go = bool;
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
		this.getRoues().MoveForwardDeg(150);
		
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
	
	//P5

	
	public void StopWhite() {
		this.getRoues().MoveForwardSpeed(getRoues().getVitesse());
		do {
			this.getColor().colorScan();
		}
		while (this.getColor().getCouleurCourant()!="WHITE");
		this.Deposer();
		
	}
	
	//P2
	public void rejoindreLigne(String couleur, String sens) {// permet de rejoindre une ligne d'une certaine couleur, s'il y a une palet le robot l'attrape sinon de tourner sur la ligne
		do {
			this.getRoues().MoveForwardSpeed(getRoues().getVitesse());
			this.getColor().colorScan();
		}
		while (!(this.ToucherDeux()) && (this.getColor().getCouleurCourant()!= couleur) && Button.ESCAPE.isUp() );
		
		if(this.ToucherDeux()) {
			this.roues.Stop();
			this.getPinces().Close();
			this.getRoues().RotateToZero();
			this.StopWhite();
			this.go = false; 
		}
		
		else if (Button.ESCAPE.isDown()) {
			this.getRoues().Stop();
		}
		
		else if(this.getColor().getCouleurCourant()== couleur) {
			this.RotateUntilLinePerp(couleur, sens); 
		}
	
	}

	//P2
	public void parcourirLigne(String couleur) {// parcours la lingne
		
		this.FollowLine(couleur);
		if(this.getTouch().isPressed())	// si le robot rencontre un palet
		{
			this.getRoues().Stop();
			this.getPinces().Close();;
			this.roues.RotateToZero();
			this.StopWhite(); 
			this.go = false; // go deviens false (sert au scénario p2
		}
		
	}
	
	public void CherchWhite() {// methode qui permet de diriger le robot vers la ligne blanche quel que soit sa position de depart.
		this.getRoues().MoveForwardSpeed(this.getRoues().getVitesse());// le robot avance
		do {
		    this.getColor().colorScan();//renvoi la couleur
		    this.getUltrason().DistanceScan();//renvoi la distance entre le robot et le mur.
		    
		}while ((!(this.ToucherDeux()) && !(this.getUltrason().DistanceLimit(0.1f)) && (this.getColor().getCouleurCourant()!= "YELLOW" && this.getColor().getCouleurCourant()!= "WHITE"&& this.getColor().getCouleurCourant() != "ReD")));//avancer jusqu'a la ligne rougre, jaune ou blanche.
	
		if ( this.getColor().getCouleurCourant()=="WHITE"){ //si cest le robot est sur la ligne blanche il fait une rotation de 90degres et s'arrete.
		    this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
		    this.getRoues().RotateD("Left", 90);//Le robot fait une rotation de 90degres vers la gauche.
		}
	
		else if (this.getUltrason().DistanceLimit(0.1f)) {//si le robot capte le mur il fait une rotation de 180degres par la gauche et le programme est recommence.
		
		this.getRoues().RotateD("Left", 180);
		this.CherchWhite();
	
		}
		else if ( this.getColor().getCouleurCourant()=="YELLOW"){// si le robot est sur la ligne jaune il avance jusqu'a ce que il capte la ligne blanche.
			this.RotateUntilLinePerp("YELLOW", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur jaune.
			this.getRoues().MoveForwardSpeed(this.getRoues().getVitesse());//Le robot avance.
			do {// Le robot renvoi la couleur tant que celle ci n'est pas blanche.
				this.getColor().colorScan();//renvoi la couleur.
			}
			while (this.getColor().getCouleurCourant()!="WHITE");
			this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
		    this.getRoues().RotateD("Left", 90);
			
		}
		else if ( this.getColor().getCouleurCourant()=="RED"){// si cest rouge le robot avance jusqu'a la ligne blanche.
				this.RotateUntilLinePerp("RED", "Left");
				this.getRoues().MoveForwardSpeed(this.getRoues().getVitesse());
				do {
					this.getColor().colorScan();
				}
				while (this.getColor().getCouleurCourant()!="WHITE");
				this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
			    this.getRoues().RotateD("Left", 90);
	
		}

	this.getRoues().resetOrrien();	//l'oreintation est reinitialise a 0

	}
	


	public void RchrchCamp() {// cette methode permet au robot de se diriger vers le camps de depart, si il rencontre un palet il lattrape et le depose dans le camp oppose.
		this.getRoues().MoveForwardSpeed(this.getRoues().getVitesse());// le robot avance
	    do {
	            this.getColor().colorScan();//renvoi la couleur
	            this.getUltrason().DistanceScan();//renvoi la distance entre le robot et le mur.
	    }while ((!(this.ToucherDeux()) && !(this.getUltrason().DistanceLimit(0.1f)) && (this.getColor().getCouleurCourant()!= "YELLOW" && this.getColor().getCouleurCourant()!= "WHITE"&& this.getColor().getCouleurCourant() != "ReD")));
	    
	    if(this.ToucherDeux()) {// si le capteur tactile est touche
	        this.roues.Stop();// le robot sarrete
	        this.getPinces().Close();//il attrape le palet
	        this.getRoues().RotateD("Left", 180);//le robot fait une rotation de 180degres.
	        this.CherchWhite();// il se dirige vers la ligne blanche et depose le palet
	        this.setGo(false);//comme cest false donc le robot a croise un palet il devra le deposer.
	    }
	    
	    else if ( this.getColor().getCouleurCourant()=="WHITE"){ //si cest le robot est sur la ligne blanche il fait une rotation de 90degres et s'arrete.
	    	    this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
	    	    this.getRoues().RotateD("Left", 90);//Le robot fait une rotation de 90degres vers la gauche.
	    }
	    
	    else if (this.getUltrason().DistanceLimit(0.1f)) {//si le robot capte le mur il fait une rotation de 180degres par la gauche et le programme est recommence.
	    	this.getRoues().RotateD("Left", 180);
	    	this.CherchWhite();
	    	
	    }
	    else if ( this.getColor().getCouleurCourant()=="YELLOW"){// si le robot est sur la ligne jaune il avance jusqu'a ce que il capte la ligne blanche.
	    		this.RotateUntilLinePerp("YELLOW", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur jaune.
	    		this.getRoues().MoveForwardSpeed(this.getRoues().getVitesse());// Le robot avance.
	    		do {//Le robot renvoi la couleur tant que celle ci n est pas blanche.
	    			this.getColor().colorScan();// renvoi la couleur.
	    		}
	    		while (this.getColor().getCouleurCourant()!="WHITE");
	    		this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
	    	    this.getRoues().RotateD("Left", 90);// rotation par la gauche de 90degres.
	    		
	    }
	    else if ( this.getColor().getCouleurCourant()=="RED"){// si la couleur captee est rouge le robot avance jusqua capater la couleur blanche.
	    			this.RotateUntilLinePerp("RED", "Left");
	    			this.getRoues().MoveForwardSpeed(this.getRoues().getVitesse());
		    		do {
		    			this.getColor().colorScan();
		    		}
		    		while (this.getColor().getCouleurCourant()!="WHITE");
		    		this.RotateUntilLinePerp("WHITE", "Left");
		    	    this.getRoues().RotateD("Left", 90);
	   	 
	    }
	    
	    this.getRoues().resetOrrien();	//l'oreintation est reinitialise a 0
	    
	}
	
	/*Une methode de calibrage pour toutes les couleurs. 5 valeurs pour scanner pour chaque couleur. Voir la classe colorSensor
	 *pour les methodes setTab/getTabCalibrage */
	public void Calibrage () throws IOException {
		System.out.println("Calibrage: Press any button");
		Button.waitForAnyPress();
		System.out.println("calibrage vert: 5 valeurs");
		this.getColor().setTab("green");
		System.out.println("calibrage bleu: 5 valeurs");
		this.getColor().setTab("blue");
		System.out.println("calibrage jaune: 5 valeurs");
		this.getColor().setTab("yellow");
		System.out.println("calibrage blanc: 5 valeurs");
		this.getColor().setTab("white");
		System.out.println("calibrage noir: 5 valeurs");
		this.getColor().setTab("black");
		System.out.println("calibrage rouge: 5 valeurs");
		this.getColor().setTab("red");
		this.getColor().getTabCalibrage("RED");
		this.getColor().getTabCalibrage("BLUE");
		this.getColor().getTabCalibrage("GREEN");
		this.getColor().getTabCalibrage("YELLOW");
		this.getColor().getTabCalibrage("BLACK");
		this.getColor().getTabCalibrage("WHITE");
	}
	
	//Affichage d'un interface avec un fleche qui pointe sur Jouer
	public void afficheJouer() {
		System.out.println();
		System.out.println();
		System.out.println("  >Jouer");
		System.out.println("   Calibrer");
		System.out.println();
		System.out.println();
		System.out.println(" ESCAPE TO EXIT");
		
	}
	
	//Affichage d'un interface avec un fleche qui pointe sur Calibrer
	public void afficheCalibrer()	{
		System.out.println();
		System.out.println();
		System.out.println("   Jouer");
		System.out.println("  >Calibrer");
		System.out.println();
		System.out.println();
		System.out.println(" ESCAPE TO EXIT");
	}

	public boolean menuCalibJouer() throws IOException {
		int i =0;
		boolean exit=false;
		while (Button.ESCAPE.isUp() && Button.ENTER.isUp()) {//Tant que les boutons ESCAPE et ENTER ne sont pas appuyes.
			if (i%2==0) {//Si la valeur de i est positif on affiche afficheJouer
				afficheJouer();
			}
			else {//Sinon(i negatif) on affiche afficheCalibrer
				afficheCalibrer();
			}
			Button.waitForAnyPress();//On attend un appuie de bouton
			if (Button.DOWN.isDown() || Button.UP.isDown()) {//Si le bouton appuye et le bouton en haut ouen bas on incremente i par 1.
				i++;//L'incrementation de i sert alterner entre afficheJouer et afficheCalibrer pour deplacer la fleche.
			}
			else if (Button.ESCAPE.isDown()) {
				exit = true;//Sinon si le bouton appuye est le bouton escape, exit devient true (voir la suite).
			}
		}
		if (!exit) {//Si !=exit == true, c'est a dire que le bouton ESCAPE n'etait pas appuye
			if (i%2==1) {//Si la fleche etait sur Calibrer (lorsque i est negatif)
				this.Calibrage();//On calibre
			}
			return true;//On  retourne true
		}
		return false;//Si ESCAPE etait appuye on retourne false
	}
	
	




	
	
}
