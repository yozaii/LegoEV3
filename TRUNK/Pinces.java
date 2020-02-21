package real;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Pinces {
	private EV3MediumRegulatedMotor cMotor;
	
	public Pinces() {
		cMotor = new EV3MediumRegulatedMotor (MotorPort.C);
		cMotor.setSpeed(1080f);
	}
	
	public void Open() {
		cMotor.rotate(2420);
		Delay.msDelay(100);
		cMotor.stop();
	}
	
	public void Close() {
		cMotor.rotate(-2420);
		Delay.msDelay(100);
		cMotor.stop();
	}
	
	public void Stop()	{
		cMotor.stop();
	}

}
