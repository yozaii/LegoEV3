package real;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Movement {
	
	private EV3LargeRegulatedMotor lMotor ; //a left
	private EV3LargeRegulatedMotor rMotor ;//b right
	
	public Movement() {
		lMotor = new EV3LargeRegulatedMotor (MotorPort.A);
		rMotor = new EV3LargeRegulatedMotor (MotorPort.B);
		lMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {rMotor});
	}
	
	public void MoveForward() {		//Mouvement en avant (360 degre par seconde par defaut)
		lMotor.setSpeed(360f);
		rMotor.setSpeed(360f);
		lMotor.startSynchronization();
		lMotor.forward();
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	public void MoveForwardSpeed(float speed) {	//Mouvement en avant avec argumen:vitesse
		lMotor.setSpeed(speed);
		rMotor.setSpeed(speed);
		lMotor.forward();
		rMotor.forward();
	}
	
	public void Stop() {
		lMotor.startSynchronization();
		lMotor.stop();
		rMotor.stop();
		lMotor.endSynchronization();
	}
	
	public void RotateClockwise() {
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
	
	}
	
	public void RotateCounterClockwise180() {
		lMotor.startSynchronization();
		lMotor.rotate(-400);
		rMotor.rotate(400);
		lMotor.endSynchronization();
	}
	
	public void RotateClockwise90()	{
		lMotor.startSynchronization();
		lMotor.rotate(200);
		rMotor.rotate(-200);
		lMotor.endSynchronization();
	}
	
	public void RotateCounterClockwise90()	{
		lMotor.startSynchronization();
		lMotor.rotate(200);
		rMotor.rotate(-200);
		lMotor.endSynchronization();

	}
	
	

}
