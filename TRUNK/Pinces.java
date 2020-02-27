package real;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Pinces {
	private EV3MediumRegulatedMotor cMotor;
	private int degreOpenClose = 2560; /*Nombres de degrés requis par le moteur pour ouvrir les pinces
	 									presque complètement si ils étaient fermés. Le contraire est
	 									applicable aussi si on prend la valeur négative de degreOpenClose*/
	public Pinces() {
		cMotor = new EV3MediumRegulatedMotor (MotorPort.C);
		cMotor.setSpeed(1080f);
	}
	
	public void Open() {
		cMotor.rotate(degreOpenClose);
		Delay.msDelay(100);
		cMotor.stop();
	}
	
	public void Close() {
		cMotor.rotate(-degreOpenClose);
		Delay.msDelay(100);
		cMotor.stop();
	}
	
	public void Stop()	{
		cMotor.stop();
	}

}
