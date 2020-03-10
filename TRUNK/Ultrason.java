
package real;
import lejos.hardware.ev3.LocalEV3;        
import lejos.hardware.sensor.EV3UltrasonicSensor;        
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.hardware.Button;
        
public class Ultrason {// création de la classe ultrason
                
        private EV3UltrasonicSensor ultrason;
        private SampleProvider distanceProvider;
        private float distance;
        private float [] distanceSample;
        
         		 public Ultrason(){ // initialisation des variables de la classe ultrason.
                 Port S3 = LocalEV3.get().getPort("S3");
                 this.ultrason= new EV3UltrasonicSensor(S3);
                 distanceProvider = ultrason.getDistanceMode();
                 this.distance=0f;
                 distanceSample = new float[distanceProvider.sampleSize()];
                 
         }
         
         public void DistanceTesting() { //permet de mesurer la distance entre le capteur et un obstacle.
                 
                 
                 ultrason.enable();
                 while(Button.ESCAPE.isUp()) {
                             distanceProvider.fetchSample(distanceSample,0);
                             System.out.println("distance:"+distanceSample[0]);
                             Button.waitForAnyPress();
                 }
                 ultrason.disable();
         }
         public double getDistanceMode(){ // permet d'accéder à la variable distance en lecture.
                 return distance;
         }

