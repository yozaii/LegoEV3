package real;

import lejos.hardware.ev3.LocalEV3;	
import lejos.hardware.sensor.EV3UltrasonicSensor;	
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.hardware.Button;
	
public class Ultrason {
		
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
         
         public void DistanceTesting() {
        	 
        	 
        	 ultrason.enable();
        	 while(Button.ESCAPE.isUp()) {
		             distanceProvider.fetchSample(distanceSample,0);
		             System.out.println("distance:"+distanceSample[0]);
		             Button.waitForAnyPress();
        	 }
        	 ultrason.disable();
         }
         
         public void DistanceScan() {
        	 distanceProvider.fetchSample(distanceSample, 0);
        	 distance = distanceSample[0];
         }
         
         public boolean DistanceLimit(float limit) {
        	 boolean retour=false;

        	 if (distance < limit) retour = true;
        	 return retour;

         }
         public double getDistanceMode(){
        	 return distance;
         }

}

