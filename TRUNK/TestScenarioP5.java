package real;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class TestScenarioP5 extends Robot {
	
	
	/*	Methode LineMovement avec argument de couleur de ligne. Le robot suit la ligne de couleur jusqu'a
		qu'il rencontre un mur ou un palet*/
	public void LineMovement(String color) {
		
		this.FollowLine(color); //Methode de suivie de ligne (Plus d'info dans la classe Robot)
		
		if (this.getTouch().isPressed()) {	//Si le capteur tactile est touche:
			this.getPinces().Close();				//Robot ferme ses pinces
			this.getRoues().RotateToZero();			//Puis il tourne vers l'orientation d'ou il a commence, qui est vers le camp oppose
		}
		else { //Sinon (lors de detection du mur a la fin de la ligne)
			this.getRoues().RotateTo180();  //On tourne vers le camp de depart, pour parcourir la prochaine ligne
			this.setGo(false);	//La valeur de existe palet = false pour indiquer au robot qu'il ya plus de palets sur la ligne
		}
	}
	
	
	/*	Methode Line meeting avec un argument de couleur de ligne et direction ou il faut tourner lors de rencotre de la ligne
	 	Ensuite, le robot suit cette ligne en utilisant LineMovement*/
	public void LineMeeting(String color,String Direction) {
		if (this.getRoues().getOrrien()==180) { //Si le robot est dans l'orientation oppose d'ou il a commence
			
			if (Direction == "Right") {	 // Si argument de direction == droit
				this.RotateUntilLinePerp(color, "Left"); // On utilise la methode RotateUntilLinePerp (methode dans la classe Robot) dans la direction oppose de l'argument
				this.LineMovement(color);
			}
			
			else if (Direction == "Left") {
				this.RotateUntilLinePerp(color, "Right");//On utilise la methode RotateUntilLinePerp (methode dans la classe Robot) dans la direction oppose de l'argument
				this.LineMovement(color);
			}
		}
		else { // Sinon (robot est dans l'orientation ou il a commence)
			this.RotateUntilLinePerp(color, Direction); //On utilise la methode RotateUntilLinePerp (methode dans la classe Robot) dans la meme direction de l'argument
			this.LineMovement(color);
		}
	}
	

	/*	Methode MainMethod qui prend le couleur de ligne et la direction en argument
		Cette methode sert a avancer jusqu'a rencontrer une ligne de couleur = color ou un palet
		Si le robot rencontre une ligne, il la parcour en utilisant LineMeeting */
	public void MainMethod(String color,String direction) {
		this.getRoues().MoveForwardSpeed(super.getRoues().getVitesse()); //Le robot avance
		do {
			this.getColor().colorScan(); // Le robot scan le couleur en dessous de lui
		}
		while (this.getColor().getCouleurCourant()!=color && !(this.ToucherDeux()));//Jusqu'a qu'il rencontre la ligne avec la couleur de l'argument color ou le capteur tactile est touche
		if (this.ToucherDeux()) { // Si capteur tactile est touche
			this.getRoues().Stop(); // Robot s'arrete
			this.getPinces().Close();	// Robot ferme ses pinces
			this.getRoues().RotateToZero();	// Robot tourne vers camp oppose
		}
		else{
			this.LineMeeting(color,direction); // Sinon, dans le cas ou on a rencontre le couleur desire, on execute la methode LineMeeting
		}
	}
	
	/*	Methode BiggerMethod qui prend le couleur de ligne et la direction en argument
		Cette Methode utilise MainMethod dans un boucle tant qu'un palet existe sur la ligne de couleur = color*/
	public void BiggerMethod(String color,String direction) {
		while (Button.ESCAPE.isUp() && this.getGo()) { //Si existePalet = true on repete la boucle
			this.MainMethod(color, direction);	//On execute MainMethod. Mainmethod s'arrete de s'executer lorsque le robot attrape un palet ou rencontre le mur.
			if (this.getGo()) { //Dans le cas ou on attrape un palet (La rencontre de mur met la valeur de ExistePalet = false)
				this.StopWhite();					//On lance la methode StopWhite (methode dans la classe Robot)
			}
		}
	}
	
	public static void main (String[] args) throws IOException {
		
		TestScenarioP5 robot = new TestScenarioP5();
		String StartCamp, EndCamp, StartColor, FirstDir = null, SecondDir = null;	/*	Des Strings qui vont modifier les parametres des methodes de Scenario5
																						StartCamp,EndCamp, et StartColor vont modifier les parametres des couleurs,
																						FirstDir et SecondDir vont modifier les directions*/
		
		if (robot.menuCalibJouer()) {//Si la methude menuCalibJouer renvoie true (bouton ESCAPE n'etait pas appuye), on continue le programme.
			Delay.msDelay(1000);
			robot.getInfo().menu();
			System.out.println(robot.getInfo().getCampDep()); //On affiche le camp de depart
			if (robot.getInfo().getCampDep()=="Ouest") { //Si camp de depart = Ouest
				StartCamp = "BLUE";	//Alors camp depart est cote bleu
				EndCamp ="GREEN";	//Et camp oppose est cote vert
			}
			else {
				StartCamp = "GREEN";
				EndCamp = "BLUE";
			}
			System.out.println("Press any button");
			Button.waitForAnyPress();	// On attend l'appuie d'un bouton avant de commancer le programme
			
			/*	Il y'aura 6 conditions de depart: Ouest-Jaune,Ouest-Rouge,Ouest-Noire,Est-Jaune,Est-Rouge, et Est-noire. Ils suivent les memes algorithmes mais avec des parametres different pour chaque cas
			 	L'algorithme sert a prendre le premier palet et l'apporter vers le camp oppose. Apres le depot du premier palet,
			 	le robot va commencer avec BiggerMethod et le reste du main pour recuperer le reste des palets*/
			
			//Condition de depart : Ouest
			if (robot.getInfo().getCampDep()=="Ouest") {
				robot.getRoues().MoveForwardSpeed(robot.getRoues().getVitesse()); //Robot avance
				do {
					robot.getColor().colorScan();	//Robot scan le couleur en dessous jusqu'a l'arrive de la ligne de couleur jaune, rouge, ou noire
				}
				while ((robot.getColor().getCouleurCourant()!="YELLOW") && (robot.getColor().getCouleurCourant()!="RED") && (robot.getColor().getCouleurCourant()!="BLACK") );
				
				//Condition de depart : Ouest - Jaune
				if (robot.getColor().getCouleurCourant()=="YELLOW"){ //Si le couleur renconter = jaune
					while(!robot.ToucherDeux()); //Le robot execute cette boucle (et avance) jusqu'a que le capteur tactile est touche.
					robot.getRoues().Stop(); //Robot s'arrete
					robot.getPinces().Close();//Et ferme ses pinces
					robot.getRoues().RotateD("Right", 90);//Le robot tourne 90 degre a droite
					robot.getRoues().MoveForwardSpeed(robot.getRoues().getVitesse());//Et avance
					do {
						robot.getUltrason().DistanceScan();
					}while(!robot.getUltrason().DistanceLimit(0.1f));//Robot continue a avance jusqu'a qu'il soit 10cm (0.1m) du mur
					robot.getRoues().RotateToZero();//Puis il tourne vers le camp de depart
					robot.StopWhite();
					FirstDir = "Left";//La premiere direction que le robot va prendre apres le depot du palet sera vers le gauche. (gauche par rapport a son orientation au debut du programme)
					SecondDir = "Right";
	
				}
				
				//Le meme algorithme s'applique dans le cas : Ouest - Rouge (Que les parametres qui changent)
				else if (robot.getColor().getCouleurCourant()=="RED") {
					while(!robot.ToucherDeux());
					robot.getRoues().Stop();
					robot.getPinces().Close();
					robot.getRoues().RotateD("Left", 90);
					robot.getRoues().MoveForwardSpeed(robot.getRoues().getVitesse());
					do {
						robot.getUltrason().DistanceScan();
					}while(!robot.getUltrason().DistanceLimit(0.1f));
					robot.getRoues().RotateToZero();
					robot.StopWhite();
					FirstDir = "Right";
					SecondDir = "Left";
	
				}
				
				//Cas Ouest - Noire
				else if (robot.getColor().getCouleurCourant()=="BLACK"){ //Si le couleur renconter = noire
					while(!robot.ToucherDeux()); //Le robot execute cette boucle (et avance) jusqu'a que le capteur tactile est touche.
					robot.getRoues().Stop(); //Robot s'arrete
					robot.getPinces().Close();//Et ferme ses pinces
					robot.getRoues().MoveForwardDeg(700);//Robot avance un peu dans le cas noire (les 2 roues font 700 deg en avant) pour eviter les autres palets lorsque le robot tourne. (noire est au mileu)
					robot.getRoues().RotateD("Right", 90);//Le robot tourne 90 degre a droite
					robot.getRoues().MoveForwardSpeed(robot.getRoues().getVitesse());//Et avance
					do {
						robot.getUltrason().DistanceScan();
					}while(!robot.getUltrason().DistanceLimit(0.1f));//Robot continue a avance jusqu'a qu'il soit 10cm (0.1m) du mur
					robot.getRoues().RotateToZero();//Puis il tourne vers le camp de depart
					robot.StopWhite();
					FirstDir = "Left";//La premiere direction que le robot va prendre apres le depot du palet sera vers le gauche. (gauche par rapport a son orientation au debut du programme)
					SecondDir = "Right";
	
				}
			}
			
			//Condition de depart : Est
			else if (robot.getInfo().getCampDep()=="Est") {
				robot.getRoues().MoveForwardSpeed(robot.getRoues().getVitesse());//Robot avance
				do {
					robot.getColor().colorScan();//Robot scan le couleur en dessous jusqu'a l'arrive de la ligne de couleur jaune,rouge, ou noire
				}
				while ((robot.getColor().getCouleurCourant()!="YELLOW") && (robot.getColor().getCouleurCourant()!="RED") && (robot.getColor().getCouleurCourant()!="BLACK") );
				
				//Condition de depart : Est - Jaune
				if (robot.getColor().getCouleurCourant()=="YELLOW"){
					while(!robot.ToucherDeux());
					robot.getRoues().Stop();
					robot.getPinces().Close();
					robot.getRoues().RotateD("Left", 90);
					robot.getRoues().MoveForwardSpeed(robot.getRoues().getVitesse());
					do {
						robot.getUltrason().DistanceScan();
					}while(!robot.getUltrason().DistanceLimit(0.1f));
					robot.getRoues().RotateToZero();
					robot.StopWhite();
					FirstDir = "Right";
					SecondDir = "Left";
	
				}
				
				//Condition de depart : Est - Jaune
				else if (robot.getColor().getCouleurCourant()=="RED") {
					while(!robot.ToucherDeux());
					robot.getRoues().Stop();
					robot.getPinces().Close();
					robot.getRoues().RotateD("Right", 90);
					robot.getRoues().MoveForwardSpeed(robot.getRoues().getVitesse());
					do {
						robot.getUltrason().DistanceScan();
					}while(!robot.getUltrason().DistanceLimit(0.1f));
					robot.getRoues().RotateToZero();
					robot.StopWhite();
					FirstDir = "Left";
					SecondDir = "Right";
				}
				
				//Cas Est - Noire
				else if (robot.getColor().getCouleurCourant()=="BLACK"){ //Si le couleur renconter = noire
					while(!robot.ToucherDeux()); //Le robot execute cette boucle (et avance) jusqu'a que le capteur tactile est touche.
					robot.getRoues().Stop(); //Robot s'arrete
					robot.getPinces().Close();//Et ferme ses pinces
					robot.getRoues().MoveForwardDeg(700);//Robot avance un peu dans le cas noire (les 2 roues font 700 degre en avant) pour eviter les autres palets lorsque le robot tourne. (noire est au mileu)
					robot.getRoues().RotateD("Right", 90);//Le robot tourne 90 degre a droite
					robot.getRoues().MoveForwardSpeed(robot.getRoues().getVitesse());//Et avance
					do {
						robot.getUltrason().DistanceScan();
					}while(!robot.getUltrason().DistanceLimit(0.1f));//Robot continue a avance jusqu'a qu'il soit 10cm (0.1m) du mur
					robot.getRoues().RotateToZero();//Puis il tourne vers le camp de depart
					robot.StopWhite();
					FirstDir = "Left";//La premiere direction que le robot va prendre apres le depot du palet sera vers le gauche. (gauche par rapport a son orientation au debut du programme)
					SecondDir = "Right";
	
				}
				
			}
			
			/*BiggerMethod pour parcourir le premier couleur vers apres depot du premier palet.
			 * La direction en argument prend FirstDir qui est definie dans les conditions de depart*/
			robot.BiggerMethod(EndCamp, FirstDir);
			robot.setGo(true);//Apres avoir parcouru toute la ligne, setGo devient false.
												//On remet a true pour pouvoir executer les prochains BiggerMethod / MainMethod.
			
			
			/* Pour eviter la probleme du croisements du lignes noires, (robot sait pas differentier entre ligne noire vertical et horizontale)
			 * on utilise pas BiggerMethod pour la couleur noire(BiggerMethod execute MainMethod en boucle). On execute MainMethod plusieurs
			 * fois avec une modification dans la deuxieme iteration de MainMethod. Dans la deuxieme iteration le robot se detourne de la ligne
			 * noire verticale pour assurer qu'il ne la croise pas.
			 * 
			 */
			robot.MainMethod("BLACK", SecondDir);//1ere iteration de MainMethod.
			if (robot.getGo()) {//Si un palet existe(le robot a toucher un palet et n'a toujours pas detecte le mur)
				robot.StopWhite();//Le robot amene le palet vers le camp oppose.
			}
			if (robot.getGo()) {//Petite modification pour le 2eme iteration de MainMethod.
				robot.MainMethod("BLACK", SecondDir);//2eme iteration de MainMethod.
				if (robot.getGo()) {
					robot.StopWhite();
				}
			}
			if (robot.getGo()) {//Petite modification pour le 3eme iteration de MainMethod.
				if (SecondDir == "Right") { //SecondDir nous indique la direction ou le robot va parcourir la ligne noire.
					robot.getRoues().RotateClockwise();//Dans le cas ou SecondDir == "Right", on tourne dans les sens des aiguilles legerement.(Eviter ligne noire verticale)
					Delay.msDelay(100);
				}
				else if (SecondDir=="Left") {//La meme idee mais que le if d'avant mais si SecondDir == "Left".
					robot.getRoues().RotateCounterClockwise();
					Delay.msDelay(100);
				}
				robot.MainMethod("BLACK", SecondDir);
				if (robot.getGo()) {
					robot.StopWhite();
				}
			}
			if (robot.getGo()) {//Derniere et 4e iteration de MainMethod.
				robot.MainMethod("BLACK", SecondDir);
				if (robot.getGo()) {
					robot.StopWhite();
				}
			}
			robot.setGo(true);//Apres avoir parcouru toute la ligne, on remet setGo a true pour executer le prochain BiggerMethod
			robot.BiggerMethod(StartCamp, FirstDir);
	
	
		}
	}
}
