package real;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;
import lejos.hardware.Button;

public class Ultrason {
	SampleProvider distanceProvider;
	EV3UltrasonicSensor ultrason;
	private float distance;
	float [] distanceSample;

	 public Ultrason(){
		 Port S3 = LocalEV3.get().getPort("S3");
		 distanceProvider = ultrason.getDistanceMode();
		 this.ultrason= new EV3UltrasonicSensor(S3);
		 this.distance=0f;
		 distanceSample = new float[distanceProvider.sampleSize()];;
	 }
	 public void DistanceTesting() {
		 while(Button.ESCAPE.isUp()) {
             distanceProvider.fetchSample(distanceSample,0);
             System.out.println("distance:"+distanceSample[0]);
            
             Button.waitForAnyPress();
     }
     
     ultrason.close();
}

	 public double getDistanceMode(){
		 return distance;
	 }
	 
		 
	 }