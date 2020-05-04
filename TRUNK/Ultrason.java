package real;
import lejos.hardware.ev3.LocalEV3;        
import lejos.hardware.sensor.EV3UltrasonicSensor;        
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.hardware.Button;
        
public class Ultrason {// permet de mesurer et renvoyer la distance entre le robot et le mur 
                // initialisation
        private EV3UltrasonicSensor ultrason;
        private SampleProvider distanceProvider;
        private float distance;
        private float [] distanceSample;
         public Ultrason(){
                 Port S3 = LocalEV3.get().getPort("S3");
                 this.ultrason= new EV3UltrasonicSensor(S3);
                 distanceProvider = ultrason.getDistanceMode();
                 this.distance=0f;
                 distanceSample = new float[distanceProvider.sampleSize()];
                 
         }
         
         public void DistanceTesting() {// cette methode permet de mesurer la distance lorquon appuie sur le bouton
                 
                 
                 ultrason.enable();
                 while(Button.ESCAPE.isUp()) {
                             distanceProvider.fetchSample(distanceSample,0);
                             System.out.println("distance:"+distanceSample[0]);
                             Button.waitForAnyPress();
                 }
                 ultrason.disable();
         }
         
         public void DistanceScan() {//cette methode permet de scanner la distance et lattribut distance prend la valeur de cette distance scannee
                 distanceProvider.fetchSample(distanceSample, 0);
                 distance = distanceSample[0];
         }
         
         public boolean DistanceLimit(float limit) {// renvoi vrai si le capteur ultrasson capte le mur
                 boolean retour=false;
                 if (distance < limit) retour = true;
                 return retour;
         }
         public double getDistanceMode(){// renvoi la distance
                 return distance;
         }
}