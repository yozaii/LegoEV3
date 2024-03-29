package real;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**Movement permet de manipuler les roues
 * lMotor et rMotor indique au programme que le robot contient des roues qu'il peut utiliser => les deux moteurs doivent être synchronisés
 * orrien garde en mémoire l'orrientation du robot en fonction de ses rotations
 * vitesse indique la vitesse des roues
 */
public class Movement {
	
	private EV3LargeRegulatedMotor lMotor ; //Declaration d'une variable EV3RegulatedMotor lMotor (left motor)
	private EV3LargeRegulatedMotor rMotor ;//Declaration d'une variable EV3RegulatedMotor lMotor (left motor)
	private int orrien; //*****
	private float vitesse;
	
	public Movement() {
		lMotor = new EV3LargeRegulatedMotor (MotorPort.A); //lMotor controle désormais le motor connecté au PortA
		rMotor = new EV3LargeRegulatedMotor (MotorPort.B); //rMotor controle désormais le motor connecté au PortB
		lMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {rMotor});/* lMotor est synchronisé avec rMotor lors d'appels
																		des méthodes startSynchronization et endSynchronization*/
		orrien = 0;
		vitesse = 250f;
	}
	
	/**Méthode pour avancer, vitesse deja fixée
	 */
	public void MoveForward() {
		float dps = 480f; 		// float degrés de rotation par seconde
		lMotor.setSpeed(dps);	//vitesse de rotation lMotor = dps
		rMotor.setSpeed(dps);
		lMotor.startSynchronization();
		lMotor.forward();	//movement de lMotor en avant
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	/**Methode pour avancer. 
	 * @param float speed (rotations par seconde)
	 */
	public void MoveForwardSpeed(float speed) {
		lMotor.setSpeed(speed);	//vitesse de rotation lMotor = speed;
		rMotor.setSpeed(speed);
		lMotor.startSynchronization();
		lMotor.forward();	//movement de lMotor en avant
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	/**Méthode pour avancer. 
	 *@param deg (nombre de degré de rotation de chaque roue)
	 */
	public void MoveForwardDeg(int deg) {
		lMotor.startSynchronization();
		lMotor.rotate(deg);
		rMotor.rotate(deg);
		lMotor.endSynchronization();
		lMotor.waitComplete();
		rMotor.waitComplete();
		
	}
	
	/**Fait reculer le robot
	 */
	public void MoveBackward() {
		float dps = 480f; 		
		lMotor.setSpeed(dps);	
		rMotor.setSpeed(dps);	
		lMotor.startSynchronization();
		lMotor.backward();	//movement de lMotor en arriere
		rMotor.backward();
		lMotor.endSynchronization();
	}
	
/**Méthode qui arrête les deux moteurs
 */
	public void Stop() {	
		lMotor.startSynchronization();
		lMotor.stop();
		rMotor.stop();
		lMotor.endSynchronization();
	}
	
	/**Methode de faire une rotation au sens des aiguilles d'une montre
	 */
	public void RotateClockwise() {
		lMotor.setSpeed(200);
		rMotor.setSpeed(200);
		lMotor.startSynchronization();
		lMotor.forward();
		rMotor.backward();
		lMotor.endSynchronization();
	}
	
	/**Methode pour faire une rotation au sens contraire des aiguilles d'une montre
	 */
	public void RotateCounterClockwise() {
		lMotor.setSpeed(200);
		rMotor.setSpeed(200);
		lMotor.startSynchronization();
		lMotor.backward();
		rMotor.forward();
		lMotor.endSynchronization();
	}
	
	/**La méthode RotateD prend en argument un String Direction("Left" ou "Right") et
	 *un int degre (90 ou 180). Elle fait une rotation dans le direction et degre desiré.
	 *Exemple d'appel: RotateD("Left",90) 
	 *@param Direction
	 */
	public void RotateD(String Direction,int deg){
		lMotor.startSynchronization();
		if (deg==90) {	//Les rotations de 90 degres
			if (Direction=="Left") {	//Dans le sens contraire de l'aiguille
				this.setOrrien(-90);
				lMotor.rotate(-200);
				rMotor.rotate(200);
			}
			else if (Direction=="Right") {	//Dans le sens de l'aigulle
				this.setOrrien(90);
				lMotor.rotate(200);
				rMotor.rotate(-200);
			}
			else {
				System.out.println("Direction en argument pas possible");//Si Direction !=("Left" || "Right") renvoie message erreur
			}
			
		}
		else if (deg==180) {	//Les rotations de 180 degres
			if (Direction=="Left") {	//Dans le sens contraire de l'aiguille
				this.setOrrien(180);
				lMotor.rotate(-400);
				rMotor.rotate(400);
			}
			else if (Direction=="Right") {	//Dans le sens de l'aigulle
				this.setOrrien(180);
				lMotor.rotate(400);
				rMotor.rotate(-400);
			}
			else {
				System.out.println("Direction en argument pas possible");	//Si Direction !=("Left" || "Right") renvoie message erreur
			}
			
		}
		else {
			System.out.println("Degre en argument pas possible"); // Si deg !=(90 || 180) renvoie message erreur
		}
		lMotor.endSynchronization();
		lMotor.waitComplete();
		rMotor.waitComplete();
	}
	
	/**Avance en courbe à droite ou à gauche. Prend la direction desirée en argument
	 * @param Direction
	 */
	public void arcDirection(String Direction) {
		float dps=213;
		lMotor.startSynchronization();
		if (Direction == "Left") {	//Courbe a gauche
			lMotor.setSpeed(dps*0.8f);
			rMotor.setSpeed(dps);
			lMotor.forward();
			rMotor.forward();
		}
		else if (Direction =="Right") { //Courbe a droite
			lMotor.setSpeed(dps);
			rMotor.setSpeed(dps*0.8f);
			lMotor.forward();
			rMotor.forward();
		}
		else {
			System.out.println("Direction en argument pas possible"); // Si Direction !=("Left" || "Right") renvoie message erreur
		}
		lMotor.endSynchronization();
	}
	/**Setter
	 */
	public void setOrrien(int x) {
        orrien+=x;
        if(orrien>=360) {
       	 orrien-=360;
        }
        else if (orrien<0) {
       	 orrien+=360;
        }
	}
	
	public void resetOrrien() {
		orrien = 0;
	}
	
	/**Getters
	 */
	public int getOrrien(){
        return orrien;
	}
	
	public float getVitesse() {
		return vitesse;
	}
	
	public void setVitesse(float vit) {
		vitesse = vit;
	}
	//Fin getters
	
/**fait tourner le robot jusqu'à que son orientation soit à 0 (que le robot soit face au camp opposé).
 * 
 */
	public void RotateToZero(){
		if (this.getOrrien() == 90) this.RotateD("Left", 90);
		else if (this.getOrrien()==180) this.RotateD("Right", 180);
		else if (this.getOrrien()==270) this.RotateD("Right", 90);
	}
	
/**fait tourner le robot jusqu'à que son orientation soit à 180.
 */
	public void RotateTo180(){
		if (this.getOrrien() == 90) this.RotateD("Right", 90);
		else if (this.getOrrien()==180) this.RotateD("Right", 180);
		else if (this.getOrrien()==270) this.RotateD("Left", 90);
	}
	
}

