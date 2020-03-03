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
	
	public void MoveForward() {		//Méthode de mouvement en avant (480 degrés de rotation des roues par seconde)
		float dps = 480f; 		// float degrés de rotation par seconde
		lMotor.setSpeed(dps);	//vitesse de rotation lMotor = dps
		rMotor.setSpeed(dps);	//vitesse de rotation rMotor = dps//
		lMotor.startSynchronization();
		lMotor.forward();
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	public void MoveForwardSpeed(float speed) {	//Mouvement en avant avec argument float speed = degrés do rotation par seconde
		lMotor.setSpeed(speed);	//vitesse de rotation lMotor = speed;
		rMotor.setSpeed(speed); //vitesse de rotation lMotor = speed;
		lMotor.startSynchronization();
		lMotor.forward();
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	public void MoveBackward() {
		float dps = 480f; 		
		lMotor.setSpeed(dps);	
		rMotor.setSpeed(dps);	
		lMotor.startSynchronization();
		lMotor.backward();
		rMotor.backward();
		lMotor.endSynchronization();
	}
	
	
	public void Stop() {	//Méthode qui arrête les deux moteurs
		lMotor.startSynchronization();
		lMotor.stop();
		rMotor.stop();
		lMotor.endSynchronization();
	}
	
	public void RotateClockwise() { //Méthode qui 
		lMotor.forward();
		rMotor.backward();
	}
	
	public void RotateCounterClockwise() {
		lMotor.startSynchronization();
		lMotor.backward();
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	public void RotateClockwise180() {
		lMotor.startSynchronization();
		lMotor.rotate(400);
		rMotor.rotate(-400);
		lMotor.endSynchronization();
		lMotor.waitComplete();
		rMotor.waitComplete();
	
	}
	
	public void RotateCounterClockwise180() {
		lMotor.startSynchronization();
		lMotor.rotate(-400);
		rMotor.rotate(400);
		lMotor.endSynchronization();
		lMotor.waitComplete();
		rMotor.waitComplete();
	}
	
	public void RotateClockwise90()	{
		lMotor.startSynchronization();
		lMotor.rotate(200);
		rMotor.rotate(-200);
		lMotor.endSynchronization();
		lMotor.waitComplete();
		rMotor.waitComplete();
	}
	
	public void RotateCounterClockwise90()	{
		lMotor.startSynchronization();
		lMotor.rotate(200);
		rMotor.rotate(-200);
		lMotor.endSynchronization();
		lMotor.waitComplete();
		rMotor.waitComplete();

	}
	
	public void arcRight() {
		lMotor.setSpeed(480f);
		rMotor.setSpeed(400f);
		lMotor.startSynchronization();
		lMotor.forward();
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	public void arcLeft()	{
		lMotor.setSpeed(400f);
		rMotor.setSpeed(480f);
		lMotor.startSynchronization();
		lMotor.forward();
		rMotor.forward();
		lMotor.endSynchronization();
	}
	

}

