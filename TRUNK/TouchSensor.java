package real;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
public class TouchSensor extends EV3TouchSensor {
	/** La classe TouchSensor controle le capteur tactile. Elle permet au robot de detecter la presence dun palet

	*/
        
        public TouchSensor(Port port) {// cette class permet au robot de detecter la presence dun palet
                super(port);
        }
        
        
        public boolean isPressed() {// cette methode renvoi un booleen: si le capteur est touche elle renvoi true et false sinon
                float [] sample = new float[1];
                fetchSample(sample, 0);
                return sample[0] !=0;
                
        }
}