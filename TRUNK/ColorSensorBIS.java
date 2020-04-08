import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensorBIS {
	
	//Attributs
	private EV3ColorSensor colorSensor;
	private SampleProvider colorProvider;
	private float[] colorSample;
	private final int NBVAL = 5;// nombre de valeurs recueillies pour chaque couleurs lors du calibrage 
	private float [][] redTab, greenTab, blueTab, yellowTab, whiteTab, blackTab, greyTab;// tableaux qui serviront à contenir des valeurs RGB de chaque couleur lors du calibrage
	String couleurCourant; 
	
	
	//Constructeur
	public ColorSensorBIS () {
		Port s2 = LocalEV3.get().getPort("S2");
		colorSensor = new EV3ColorSensor(s2);
		colorProvider = colorSensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
		couleurCourant = null; 
	}
	
	//Méthodes
	public void ColorTesting() {//affiche les valeurs RGB se trouvant dans le tableau colorSample
		
		while(Button.ESCAPE.isUp()) {
			colorProvider.fetchSample(colorSample, 0);
			System.out.println("R" + colorSample[0]);
			System.out.println("G" + colorSample[1]);
			System.out.println("B" + colorSample[2]);
			Button.waitForAnyPress();
		}
		
		colorSensor.close();
	}
	
	public float[] echantillon() {//place les valeurs RGB scannées dans le tableau colorSample, renvoie colorSample
		colorProvider.fetchSample(colorSample, 0);
		return colorSample;
	}
	
	public float [][] setTab(String color){
		float[][] tab = null;
		
		switch (color){
		
		case "red":
			for(int i=0;i<NBVAL;i++) {
				tab[i] = this.echantillon();
				Button.waitForAnyPress();
			}
			redTab = tab;
			return redTab;
			
		case "green":
			for(int i=0;i<NBVAL;i++) {
				tab[i] = this.echantillon();
				Button.waitForAnyPress();
			}
			greenTab = tab;
			return greenTab;
			
		case "blue":
			for(int i=0;i<NBVAL;i++) {
				tab[i] = this.echantillon();
				Button.waitForAnyPress();
			}
			blueTab = tab;
			return blueTab;
			
		case "yellow":
			for(int i=0;i<NBVAL;i++) {
				tab[i] = this.echantillon();
				Button.waitForAnyPress();
			}
			yellowTab = tab;
			return yellowTab;
			
		case "white":
			for(int i=0;i<NBVAL;i++) {
				tab[i] = this.echantillon();
				Button.waitForAnyPress();
			}
			whiteTab = tab;
			return whiteTab;
			
		case "black":
			for(int i=0;i<NBVAL;i++) {
				tab[i] = this.echantillon();
				Button.waitForAnyPress();
			}
			blackTab = tab;
			return blackTab;
			
		case "grey": 
			for(int i=0;i<NBVAL;i++) {
				tab[i] = this.echantillon();
				Button.waitForAnyPress();
			}
			greyTab = tab;
			return greyTab;
			
		default:
			return null;
		
		}
	}
	
	
	public float[] min(String couleur) {
		float min[]=null; // vu qu'on ne peut retourner qu'une valeur c'est un tableau RGB qui contiendra minR,minG et minB;
		float minR, minG, minB; // valeur minimale de chaque couleur 
		
		switch (couleur) {
		case "red":
			minR = redTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(minR>redTab[i][0]) {
					minR = redTab [i][0];
				}
			}
			minG = redTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(minG>redTab[i][1]) {
					minG = redTab [i][1];
				}
			}		
			minB = redTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(minB>redTab[i][2]) {
					minB = redTab [i][2];
				}
			}
			
		case "green":
			minR = greenTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(minR>greenTab[i][0]) {
					minR = greenTab [i][0];
				}
			}
			minG = greenTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(minG>greenTab[i][1]) {
					minG = greenTab [i][1];
				}
			}	
			minB = greenTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(minB>greenTab[i][2]) {
					minB = greenTab [i][2];
				}
			}
			
		case "blue":
			minR = blueTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(minR>blueTab[i][0]) {
					minR = blueTab [i][0];
				}
			}
			
			minG = redTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(minG>blueTab[i][1]) {
					minG = blueTab [i][1];
				}
			}		
			minB = redTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(minB>blueTab[i][2]) {
					minB = blueTab [i][2];
				}
			}
			
		case "yellow":
			minR = yellowTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(minR>yellowTab[i][0]) {
					minR = yellowTab [i][0];
				}
			}
			
			minG = yellowTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(minG>yellowTab[i][1]) {
					minG = yellowTab [i][1];
				}
			}
					
			minB = yellowTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(minB>yellowTab[i][2]) {
					minB = yellowTab [i][2];
				}
			}
			
		case "white":
			minR = whiteTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(minR>whiteTab[i][0]) {
					minR = whiteTab [i][0];
				}
			}
			
			minG = whiteTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(minG>whiteTab[i][1]) {
					minG = whiteTab [i][1];
				}
			}
					
			minB = whiteTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(minB>whiteTab[i][2]) {
					minB = whiteTab [i][2];
				}
			}
			
		case "black":
			minR = blackTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(minR>blackTab[i][0]) {
					minR = blackTab [i][0];
				}
			}
			
			minG = blackTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(minG>blackTab[i][1]) {
					minG = blackTab [i][1];
				}
			}
					
			minB = blackTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(minB>blackTab[i][2]) {
					minB = blackTab [i][2];
				}
			}
			
		case "grey":
			minR = greyTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(minR>greyTab[i][0]) {
					minR = greyTab [i][0];
				}
			}
			
			minG = greyTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(minG>greyTab[i][1]) {
					minG = greyTab [i][1];
				}
			}
					
			minB = greyTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(minB>greyTab[i][2]) {
					minB = greyTab [i][2];
				}
			}	
		}
		
		return min;
	}
	
	public float[] max(String couleur) {
		
		float max[] = null; // vu qu'on ne peut retourner qu'une valeur c'est un tableau RGB qui contiendra minR,minG et minB;
		float maxR=0, maxG=0, maxB=0; // valeur minimale de chaque couleur 
		
		switch (couleur) {
		case "red":
			//pour la couleur rouge par exemple on prend les 3 variables et on parcourt le tableau redTab 3fois 
			//la premiere fois pour trouver le minimum de la valeur rouge
			maxR = redTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(maxR<redTab[i][0]) {
					maxR = redTab [i][0];
				}
			}
			//la deuxieme fois pour le minimum de la valeur verte
			maxG = redTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(maxG<redTab[i][1]) {
					maxG = redTab [i][1];
				}
			}	
			//la 3eme fois pour le minimum de la valeur bleu
			maxB = redTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(maxB<redTab[i][2]) {
					maxB = redTab [i][2];
				}
			}
			break;
					
		case "green":
			maxR = greenTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(maxR<greenTab[i][0]) {
					maxR = greenTab [i][0];
				}
			}
			maxG = greenTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(maxG>greenTab[i][1]) {
					maxG = greenTab [i][1];
				}
			}		
			maxB = greenTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(maxB>greenTab[i][2]) {
					maxB = greenTab [i][2];
				}
			}
			break;
			
			
		case "blue":
			maxR = blueTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>blueTab[i][0]) {
					maxR = blueTab [i][0];
				}
			}
			
			maxR = redTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>blueTab[i][1]) {
					maxR = blueTab [i][1];
				}
			}		
			maxR = redTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>blueTab[i][2]) {
					maxR = blueTab [i][2];
				}
			}
			break;
			
		case "yellow":
			maxR = yellowTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>yellowTab[i][0]) {
					maxR = yellowTab [i][0];
				}
			}
			
			maxR = yellowTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>yellowTab[i][1]) {
					maxR = yellowTab [i][1];
				}
			}
					
			maxR = yellowTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>yellowTab[i][2]) {
					maxR = yellowTab [i][2];
				}
			}
			break;
			
		case "white":
			maxR = whiteTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>whiteTab[i][0]) {
					maxR = whiteTab [i][0];
				}
			}
			
			maxR = whiteTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>whiteTab[i][1]) {
					maxR = whiteTab [i][1];
				}
			}
					
			maxR = whiteTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>whiteTab[i][2]) {
					maxR = whiteTab [i][2];
				}
			}
			break;
			
		case "black":
			maxR = blackTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>blackTab[i][0]) {
					maxR = blackTab [i][0];
				}
			}
			
			maxR = blackTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>blackTab[i][1]) {
					maxR = blackTab [i][1];
				}
			}
					
			maxR = blackTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>blackTab[i][2]) {
					maxR = blackTab [i][2];
				}
			}
			break;
			
		case "grey":
			maxR = greyTab[0][0];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>greyTab[i][0]) {
					maxR = greyTab [i][0];
				}
			}
			
			maxR = greyTab[0][1];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>greyTab[i][1]) {
					maxR = greyTab [i][1];
				}
			}
					
			maxR = greyTab[0][2];
			for (int i=0;i<NBVAL;i++) {
				if(maxR>greyTab[i][2]) {
					maxR = greyTab [i][2];
				}
			}
			break;
		}
		
		
		
		//on rentre les valeurs trouvées dans le tableau max
		max[0] = maxR;
		max[1] = maxG;
		max[2] = maxB;
		
		return max;
		
	}
	
	public float[] calculMoy(float[][] tab, int nbval) {
		// calcul la moyenne des valeurs rgb d'un tableau
		float moyenne []=null;
		float moyR=0,moyG=0,moyB=0;
		for(int i = 0; i<nbval; i++) {//additionne tous les elements
			moyR+=tab[i][0];
			moyG+=tab[i][1];
			moyB+=tab[i][2];
		}
		
		moyR=moyR/nbval;//les divise par le nombre d'elements du tableau
		moyG=moyG/nbval;
		moyB=moyB/nbval;
		
		moyenne[0]=moyR;
		moyenne[1]=moyG;
		moyenne[2]=moyB;
		
		return moyenne;
	}
	
	public float[] invervalle(float min,float max, float moyenne) {// donne  un intervalle élargit des min et max recuillit 
		float ecartMin=0,ecartMax=0;
		ecartMin = moyenne - min;
		ecartMax = max - moyenne;
		float[] tab = null;
		tab[0] = ecartMin;
		tab[1] = ecartMax;
		return tab;
	}
	
	public boolean estDansLInterval(float val, float min, float max,float moyenne) {
		/*permet de savoir si un nombre se trouve dans l'intervalle selon la definition de l'intervalle qu'on a donné
		*les argument sont: 
		*-une val qui va etre celle qu'on compare
		*-la valeur minimum enregistrée
		*-la valeur maximum enregistrée
		*-la moyenne enregistrée
		*/
		float[] intervalle = this.invervalle(min, max, moyenne);
		boolean trueFalse = false;
		for(int i = 0; i<3; i++) {// i<3 car il y a 3 valeurs dans un tableau RGB
			if (val>=intervalle[0]&&val<=intervalle[1]) {
				trueFalse = true;
			}
			else {
				trueFalse = false;
				return trueFalse;
			}
		}
				return trueFalse;
		
	}
	
	public boolean estDansLIntervalleRGB(float[] colorSample, float[] minTab, float[] maxTab,float[] moyenne) {
		boolean trueFalse=false;
		for(int i = 0; i<3;i++) {// car 3 valeurs dans un tableau rgb
			if(this.estDansLInterval(colorSample[i], minTab[i], maxTab[i], moyenne[i])){
				trueFalse = true;
			}
			else trueFalse = false;
		}
		if(trueFalse) {
			return true;
		}
		else return false;
	}
	
	
	
	public String colorRenvoi() {
		String color = null;
		
//	 permettra d'identifier une couleur en fonction de l'intervalle au quelle appartien ses valeurs RGB
		if(this.estDansLIntervalleRGB(colorSample, this.min("red"), this.max("red"), this.calculMoy(redTab, NBVAL))) {
			color = "RED";
		}
		else if (this.estDansLIntervalleRGB(colorSample, this.min("green"), this.max("green"), this.calculMoy(greenTab, NBVAL))) {
			color = "GREEN";
		}
		else if (this.estDansLIntervalleRGB(colorSample, this.min("blue"), this.max("blue"), this.calculMoy(blueTab, NBVAL))) {
			color = "BLUE";
		}
		else if (this.estDansLIntervalleRGB(colorSample, this.min("yellow"), this.max("yellow"), this.calculMoy(yellowTab, NBVAL))) {
			color = "YELLOW";
		}
		else if (this.estDansLIntervalleRGB(colorSample, this.min("black"), this.max("black"), this.calculMoy(blackTab, NBVAL))) {
			color = "BLACK";
		}
		else if(this.estDansLIntervalleRGB(colorSample, this.min("white"), this.max("white"), this.calculMoy(whiteTab, NBVAL))) {
			color = "WHITE";
		}
		else if(this.estDansLIntervalleRGB(colorSample, this.min("gre"), this.max("grey"), this.calculMoy(greyTab, NBVAL))) {
			color = "GREY";
		}

		System.out.println(color);
		return color; 
	}
	
	
	public String colorScan() {
		this.echantillon();
		couleurCourant = this.colorRenvoi();
		return  couleurCourant;
	}
	
	public String getCouleurCourant() {
		return couleurCourant;
	}
}
