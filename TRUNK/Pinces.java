package real;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

/** la classe Pinces permet au robot douvrir et fermer ses pinces
 * les attributs sont:
 *  degreOpenClose determine le nombre de degres requis par le moteur afin douvrir les pinces
 *  cMotor 
 */
public class Pinces {
        private EV3MediumRegulatedMotor cMotor;
        private int degreOpenClose = 2560; /*Nombres de degrés requis par le moteur pour ouvrir les pinces
                                                                                 presque complètement si ils étaient fermés. Le contraire est
                                                                                 applicable aussi si on prend la valeur négative de degreOpenClose*/
        public Pinces() {
                cMotor = new EV3MediumRegulatedMotor (MotorPort.C);
                cMotor.setSpeed(1080f);
        }
        
        public void Open() {// permet louverture des pinces
                cMotor.rotate(degreOpenClose);
                Delay.msDelay(100);
                cMotor.stop();
        }
        
        public void Close() {// permet la fermuture des pinces
                cMotor.rotate(-degreOpenClose);
                Delay.msDelay(100);
                cMotor.stop();
        }
        
        public void Stop()        {// permet larret
                cMotor.stop();
        }
}
