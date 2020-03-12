package real;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Movement {
	
	private EV3LargeRegulatedMotor lMotor ; //Declaration d'une variable EV3RegulatedMotor lMotor (left motor)
	private EV3LargeRegulatedMotor rMotor ;//Declaration d'une variable EV3RegulatedMotor lMotor (left motor)
	
	public Movement() {
		lMotor = new EV3LargeRegulatedMotor (MotorPort.A); //lMotor controle désormais le motor connecté au PortA
		rMotor = new EV3LargeRegulatedMotor (MotorPort.B); //rMotor controle désormais le motor connecté au PortB
		lMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {rMotor});/* lMotor est synchronisé avec rMotor lors d'appels
																		des méthodes startSynchronization et endSynchronization*/
	}
	
	//Méthode de mouvement en avant
	public void MoveForward() {
		float dps = 480f; 		// float degrés de rotation par seconde
		lMotor.setSpeed(dps);	//vitesse de rotation lMotor = dps
		rMotor.setSpeed(dps);
		lMotor.startSynchronization();
		lMotor.forward();	//movement de lMotor en avant
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	//Methode de mouvement en avant avec argument float speed = degrés do rotation par seconde
	public void MoveForwardSpeed(float speed) {
		lMotor.setSpeed(speed);	//vitesse de rotation lMotor = speed;
		rMotor.setSpeed(speed);
		lMotor.startSynchronization();
		lMotor.forward();	//movement de lMotor en avant
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	//Methode de mouvement vers l'arriere
	public void MoveBackward() {
		float dps = 480f; 		
		lMotor.setSpeed(dps);	
		rMotor.setSpeed(dps);	
		lMotor.startSynchronization();
		lMotor.backward();	//movement de lMotor en arriere
		rMotor.backward();
		lMotor.endSynchronization();
	}
	
	
	public void Stop() {	//Méthode qui arrête les deux moteurs
		lMotor.startSynchronization();
		lMotor.stop();
		rMotor.stop();
		lMotor.endSynchronization();
	}
	
	//Methode de faire une rotation au sens d'aiguille
	public void RotateClockwise() {
		lMotor.setSpeed(200);
		rMotor.setSpeed(200);
		lMotor.startSynchronization();
		lMotor.forward();
		rMotor.backward();
		lMotor.endSynchronization();
	}
	
	//Methode de faire une rotation au sens contraire d'aiguille
	public void RotateCounterClockwise() {
		lMotor.setSpeed(200);
		rMotor.setSpeed(200);
		lMotor.startSynchronization();
		lMotor.backward();
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	/*Methode RotateD qui prend en argument un String Direction:"Left" ou "Right" et
	 *un int degre 90 ou 180. Fait une rotation dans le direction et degre desire.
	 *Exemple d'appel: RotateD("Left",90) */
	public void RotateD(String Direction,int deg){
		lMotor.startSynchronization();
		if (deg==90) {	//Les rotations de 90 degres
			if (Direction=="Left") {	//Dans le sens contraire de l'aiguille
				lMotor.rotate(-200);
				rMotor.rotate(200);
			}
			else if (Direction=="Right") {	//Dans le sens de l'aigulle
				lMotor.rotate(200);
				rMotor.rotate(-200);
			}
			else {
				System.out.println("Direction en argument pas possible");//Si Direction !=("Left" || "Right") renvoie message erreur
			}
			
		}
		else if (deg==180) {	//Les rotations de 180 degres
			if (Direction=="Left") {	//Dans le sens contraire de l'aiguille
				lMotor.rotate(-400);
				rMotor.rotate(400);
			}
			else if (Direction=="Right") {	//Dans le sens de l'aigulle
				lMotor.rotate(400);
				rMotor.rotate(-400);
			}
			else {
				System.out.println("Direction en argument pas possible");	//Si Direction !=("Left" || "Right") renvoie message erreur
			}
			
		}
		else {
			System.out.println("Degre en argument pas possible"); // Si deg !=(90 || 180) renvoie message erreur
		}
		lMotor.endSynchronization();
		lMotor.waitComplete();
		rMotor.waitComplete();
	}
	
	//Methode pour mouvement en courbe a droite a gauche. Prend la direction desire en argument
	public void arcDirection(String Direction) {
		lMotor.startSynchronization();
		if (Direction == "Left") {	//Courbe a gauche
			lMotor.setSpeed(320f);
			rMotor.setSpeed(400f);
			lMotor.forward();
			rMotor.forward();
		}
		else if (Direction =="Right") { //Courbe a droite
			lMotor.setSpeed(400f);
			rMotor.setSpeed(320f);
			lMotor.forward();
			rMotor.forward();
		}
		else {
			System.out.println("Direction en argument pas possible"); // Si Direction !=("Left" || "Right") renvoie message erreur
		}
		lMotor.endSynchronization();
	}
	

}
