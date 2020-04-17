import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor{

	// Attributs
	private EV3ColorSensor colorSensor;
	private SampleProvider colorProvider;
	private float[] colorSample;
	private final static int NBVAL = 5;// nombre de valeurs recueillies pour chaque couleurs lors du calibrage
	private float[][] redTab, greenTab, blueTab, yellowTab, whiteTab, blackTab, greyTab;// tableaux qui serviront à
																						// contenir des valeurs RGB de
																						// chaque couleur lors du
																						// calibrage
	String couleurCourant;
	File redData, greenData, blueData, yellowData, whiteData, blackData, greyData;

	// Constructeur
	public ColorSensor() {
		Port s2 = LocalEV3.get().getPort("S2");
		colorSensor = new EV3ColorSensor(s2);
		colorProvider = colorSensor.getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
		couleurCourant = null;
		redTab = new float[NBVAL][colorProvider.sampleSize()];
		greenTab = new float[NBVAL][colorProvider.sampleSize()];
		blueTab = new float[NBVAL][colorProvider.sampleSize()];
		yellowTab = new float[NBVAL][colorProvider.sampleSize()];
		whiteTab = new float[NBVAL][colorProvider.sampleSize()];
		blackTab = new float[NBVAL][colorProvider.sampleSize()];
		greyTab = new float[NBVAL][colorProvider.sampleSize()];
		redData = new File("redData.txt");
		greenData = new File("greenData.txt");
		blueData = new File("blueData.txt");
		yellowData = new File("yellowData.txt");
		whiteData = new File("whiteData.txt");
		blackData = new File("blackData.txt");
		greyData = new File("greyData.txt");

	}

	// Méthodes
//affiche les valeurs RGB se trouvant dans le tableau colorSample
	public void ColorTesting() {

		while (Button.ESCAPE.isUp()) {
			colorProvider.fetchSample(colorSample, 0);
			System.out.println("R" + colorSample[0]);
			System.out.println("G" + colorSample[1]);
			System.out.println("B" + colorSample[2]);
			Button.waitForAnyPress();
		}

		colorSensor.close();
	}

//place les valeurs RGB scannées dans le tableau colorSample, renvoie colorSample	
	public float[] echantillon() {
		colorProvider.fetchSample(colorSample, 0);
		return colorSample;
	}

// Cette methode prend en argument la couleur que l'on veut calibrer et fait un tableau de NBVAL valeurs RGB + met les valeurs dans un fichier
	public void setTab(String color) throws IOException {

		if (color == "red") {

			FileWriter writer = new FileWriter(redData);
			BufferedWriter bw = new BufferedWriter(writer);
	
			for (int i = 0; i < NBVAL; i++) {
				float[] echantillon = this.echantillon();
				try {
					this.redTab[i][0] = echantillon[0];// la premiere valeur (R) va dans la premiere case
					bw.write(String.valueOf(redTab[i][0]));// puis copie la valeur dans le fichier data
					bw.newLine();
					this.redTab[i][1] = echantillon[1];// (G) dans la 2e
					bw.write(String.valueOf(redTab[i][1]));
					bw.newLine();
					this.redTab[i][2] = echantillon[2];// (B) dans la 3e
					bw.write(String.valueOf(redTab[i][2]));
					bw.newLine();
					Button.waitForAnyPress();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bw.close();
		
		}

		else if (color == "green") {
			if (!greenData.exists()) {
				try {
					greenData.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.setTab(color);
			} else if (redData.exists()) {

				FileWriter writer = new FileWriter(greenData);
				BufferedWriter bw = new BufferedWriter(writer);


			for (int i = 0; i < NBVAL; i++) {
				float[] echantillon = this.echantillon();
				try {
					bw.newLine();
					this.greenTab[i][0] = echantillon[0];
					bw.write(String.valueOf(greenTab[i][0]));
					this.greenTab[i][1] = echantillon[1];
					bw.write(String.valueOf(greenTab[i][1]));
					this.greenTab[i][2] = echantillon[2];
					bw.write(String.valueOf(greenTab[i][2]));
					Button.waitForAnyPress();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bw.close();
			}
		}

		else if (color == "blue") {
			if (!blueData.exists()) {
				try {
					blueData.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.setTab(color);
			} 
			else if (blueData.exists()) {

				FileWriter writer = new FileWriter(blueData);
				BufferedWriter bw = new BufferedWriter(writer);

		
				for (int i = 0; i < NBVAL; i++) {
					float[] echantillon = this.echantillon();
					try {
						bw.newLine();
						this.blueTab[i][0] = echantillon[0];
						bw.write(String.valueOf(blueTab[i][0]));
						this.blueTab[i][1] = echantillon[1];
						bw.write(String.valueOf(blueTab[i][1]));
						this.blueTab[i][2] = echantillon[2];
						bw.write(String.valueOf(blueTab[i][2]));
						Button.waitForAnyPress();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			bw.close();
			}
		}

		else if (color == "yellow") {
			if (!yellowData.exists()) {
				try {
					yellowData.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.setTab(color);
			} else if (yellowData.exists()) {

				FileWriter writer = new FileWriter(yellowData);
				BufferedWriter bw = new BufferedWriter(writer);

				for (int i = 0; i < NBVAL; i++) {
					float[] echantillon = this.echantillon();
					try {
						bw.newLine();
						this.yellowTab[i][0] = echantillon[0];
						bw.write(String.valueOf(yellowTab[i][0]));
						this.yellowTab[i][1] = echantillon[1];
						bw.write(String.valueOf(yellowTab[i][1]));
						this.yellowTab[i][2] = echantillon[2];
						bw.write(String.valueOf(yellowTab[i][2]));
						Button.waitForAnyPress();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				bw.close();
			}
		}

		else if (color == "white") {
			if (!whiteData.exists()) {
				try {
					whiteData.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.setTab(color);
				
			} 
			else if (whiteData.exists()) {
				FileWriter writer = new FileWriter(whiteData);
				BufferedWriter bw = new BufferedWriter(writer);

				for (int i = 0; i < NBVAL; i++) {
					float[] echantillon = this.echantillon();
					try {
						bw.newLine();
						this.whiteTab[i][0] = echantillon[0];
						bw.write(String.valueOf(whiteTab[i][0]));
						this.whiteTab[i][1] = echantillon[1];
						bw.write(String.valueOf(whiteTab[i][1]));
						this.whiteTab[i][2] = echantillon[2];
						bw.write(String.valueOf(whiteTab[i][2]));
						Button.waitForAnyPress();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				bw.close();
			}
		}

		else if (color == "black") {
			if (!blackData.exists()) {
				try {
					blackData.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.setTab(color);
			} else if (blackData.exists()) {

				FileWriter writer = new FileWriter(blackData);
				BufferedWriter bw = new BufferedWriter(writer);
	
				for (int i = 0; i < NBVAL; i++) {
					float[] echantillon = this.echantillon();
					try {
						bw.newLine();
						this.blackTab[i][0] = echantillon[0];
						bw.write(String.valueOf(blackTab[i][0]));
						this.blackTab[i][1] = echantillon[1];
						bw.write(String.valueOf(blackTab[i][1]));
						this.blackTab[i][2] = echantillon[2];
						bw.write(String.valueOf(blackTab[i][2]));
						Button.waitForAnyPress();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				bw.close();
			}
		}

		else if (color == "grey") {
			if (!greyData.exists()) {
				try {
					greyData.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.setTab(color);
			} 
			else if (greyData.exists()) {

				FileWriter writer = new FileWriter(greyData);
				BufferedWriter bw = new BufferedWriter(writer);

				for (int i = 0; i < NBVAL; i++) {
					float[] echantillon = this.echantillon();
					try {
						bw.newLine();
						this.greyTab[i][0] = echantillon[0];
						bw.write(String.valueOf(greyTab[i][0]));
						this.greyTab[i][1] = echantillon[1];
						bw.write(String.valueOf(greyTab[i][1]));
						this.greyTab[i][2] = echantillon[2];
						bw.write(String.valueOf(greyTab[i][2]));
						Button.waitForAnyPress();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				bw.close();
			}
		}
	}

// sert a recuperer les valeurs du calibrage a partir du fichier data.txt
	public float[][] getTabCalibrage(String color) {// choix:  redData greenData etc...
		Scanner scanner;
		int iRGB = 0;
		
		switch (color) {
		case "RED":

			try {
				File file = new File("redData.txt");
				scanner = new Scanner(file); // on va dans le fichier data

				for (int i = 0; i < NBVAL; i++) { // A chaque iteration on recupere les valeurs pour un echantillon
					while(scanner.hasNextLine()) {
						
						for (int j = 0; j < 3; j++) {
							/*if (scanner.nextLine().isEmpty()&& scanner.hasNextLine()) {
								scanner.nextLine();
							}	*/
							this.redTab[i][j] = Float.valueOf(scanner.nextLine()).floatValue(); // Et on les met dans le tableau
							System.out.println(this.redTab[i][j]);//test
						}			
					}					
							// test:
							// System.out.println("Valeur : " + redTab[i][iRGB-1]);
											
					}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return this.redTab;
			
		case "GREEN":
			try {
				File file = new File("greenData.txt");
				scanner = new Scanner(file); // on va dans le fichier data

				for (int i = 0; i < NBVAL; i++) { // A chaque iteration on recupere les valeurs pour un echantillon
					while(scanner.hasNextLine()) {
						
						for (int j = 0; j < 3; j++) {
							/*if (scanner.nextLine().isEmpty()&& scanner.hasNextLine()) {
								scanner.nextLine();
							}	*/
							this.greenTab[i][j] = Float.valueOf(scanner.nextLine()).floatValue(); // Et on les met dans le tableau
							System.out.println(this.greenTab[i][j]);//test
						}			
					}					
							// test:
							// System.out.println("Valeur : " + greenTab[i][iRGB-1]);
											
					}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return greenTab;

		case "BLUE":
			try {
				File file = new File("blueData.txt");
				scanner = new Scanner(file); // on va dans le fichier data

				for (int i = 0; i < NBVAL; i++) { // A chaque iteration on recupere les valeurs pour un echantillon
					while(scanner.hasNextLine()) {
						
						for (int j = 0; j < 3; j++) {
							/*if (scanner.nextLine().isEmpty()&& scanner.hasNextLine()) {
								scanner.nextLine();
							}	*/
							this.blueTab[i][j] = Float.valueOf(scanner.nextLine()).floatValue(); // Et on les met dans le tableau
							System.out.println(this.blueTab[i][j]);//test
						}			
					}					
							// test:
							// System.out.println("Valeur : " + blueTab[i][iRGB-1]);
											
					}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return blueTab;

		case "YELLOW":
			try {
				File file = new File("yellowData.txt");
				scanner = new Scanner(file); // on va dans le fichier data

				for (int i = 0; i < NBVAL; i++) { // A chaque iteration on recupere les valeurs pour un echantillon
					while(scanner.hasNextLine()) {
						
						for (int j = 0; j < 3; j++) {
							/*if (scanner.nextLine().isEmpty()&& scanner.hasNextLine()) {
								scanner.nextLine();
							}	*/
							this.yellowTab[i][j] = Float.valueOf(scanner.nextLine()).floatValue(); // Et on les met dans le tableau
							System.out.println(this.yellowTab[i][j]);//test
						}			
					}					
							// test:
							// System.out.println("Valeur : " + yellowTab[i][iRGB-1]);
											
					}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return yellowTab;

		case "WHITE":
			try {
				File file = new File("whiteData.txt");
				scanner = new Scanner(file); // on va dans le fichier data

				for (int i = 0; i < NBVAL; i++) { // A chaque iteration on recupere les valeurs pour un echantillon
					while(scanner.hasNextLine()) {
						
						for (int j = 0; j < 3; j++) {
							/*if (scanner.nextLine().isEmpty()&& scanner.hasNextLine()) {
								scanner.nextLine();
							}	*/
							this.whiteTab[i][j] = Float.valueOf(scanner.nextLine()).floatValue(); // Et on les met dans le tableau
							System.out.println(this.whiteTab[i][j]);//test
						}			
					}					
							// test:
							// System.out.println("Valeur : " + whiteTab[i][iRGB-1]);
											
					}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return whiteTab;

		case "BLACK":
			try {
				File file = new File("blackData.txt");
				scanner = new Scanner(file); // on va dans le fichier data

				for (int i = 0; i < NBVAL; i++) { // A chaque iteration on recupere les valeurs pour un echantillon
					while(scanner.hasNextLine()) {
						
						for (int j = 0; j < 3; j++) {
							/*if (scanner.nextLine().isEmpty()&& scanner.hasNextLine()) {
								scanner.nextLine();
							}	*/
							this.blackTab[i][j] = Float.valueOf(scanner.nextLine()).floatValue(); // Et on les met dans le tableau
							System.out.println(this.blackTab[i][j]);//test
						}			
					}					
							// test:
							// System.out.println("Valeur : " + blackTab[i][iRGB-1]);
											
					}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return blackTab;

		case "GREY":
			try {
				File file = new File("greyData.txt");
				scanner = new Scanner(file); // on va dans le fichier data

				for (int i = 0; i < NBVAL; i++) { // A chaque iteration on recupere les valeurs pour un echantillon
					while(scanner.hasNextLine()) {
						
						for (int j = 0; j < 3; j++) {
							/*if (scanner.nextLine().isEmpty()&& scanner.hasNextLine()) {
								scanner.nextLine();
							}	*/
							this.greyTab[i][j] = Float.valueOf(scanner.nextLine()).floatValue(); // Et on les met dans le tableau
							System.out.println(this.greyTab[i][j]);//test
						}			
					}					
							// test:
							// System.out.println("Valeur : " + redTab[i][iRGB-1]);
											
					}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return greyTab;

		default:
			return null;
		}
	}

//cette methode trouve la plus petite valeur pour R, G et B en fonction de la couleur en commentaire	
	public float[] min(String couleur) {
		float min[] = { 0, 0, 0 }; 
								
		switch (couleur) {
		case "red":
			min[0] = redTab[0][0];
			for (int i = 0; i < NBVAL; i++) {// parcourt le tableau pour trouver le minimum
				if (min[0] > redTab[i][0]) {
					min[0] = redTab[i][0];
				}
			}
			min[1] = redTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (min[1] > redTab[i][1]) {
					min[1] = redTab[i][1];
				}
			}
			min[2] = redTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (min[2] > redTab[i][2]) {
					min[2] = redTab[i][2];
				}
			}

		case "green":
			min[0] = greenTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (min[0] > greenTab[i][0]) {
					min[0] = greenTab[i][0];
				}
			}
			min[1] = greenTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (min[1] > greenTab[i][1]) {
					min[1] = greenTab[i][1];
				}
			}
			min[2] = greenTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (min[2] > greenTab[i][2]) {
					min[2] = greenTab[i][2];
				}
			}

		case "blue":
			min[0] = blueTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (min[0] > blueTab[i][0]) {
					min[0] = blueTab[i][0];
				}
			}

			min[1] = redTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (min[1] > blueTab[i][1]) {
					min[1] = blueTab[i][1];
				}
			}
			min[2] = redTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (min[2] > blueTab[i][2]) {
					min[2] = blueTab[i][2];
				}
			}

		case "yellow":
			min[0] = yellowTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (min[0] > yellowTab[i][0]) {
					min[0] = yellowTab[i][0];
				}
			}

			min[1] = yellowTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (min[1] > yellowTab[i][1]) {
					min[1] = yellowTab[i][1];
				}
			}

			min[2] = yellowTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (min[2] > yellowTab[i][2]) {
					min[2] = yellowTab[i][2];
				}
			}

		case "white":
			min[0] = whiteTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (min[0] > whiteTab[i][0]) {
					min[0] = whiteTab[i][0];
				}
			}

			min[1] = whiteTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (min[1] > whiteTab[i][1]) {
					min[1] = whiteTab[i][1];
				}
			}

			min[2] = whiteTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (min[2] > whiteTab[i][2]) {
					min[2] = whiteTab[i][2];
				}
			}

		case "black":
			min[0] = blackTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (min[0] > blackTab[i][0]) {
					min[0] = blackTab[i][0];
				}
			}

			min[1] = blackTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (min[1] > blackTab[i][1]) {
					min[1] = blackTab[i][1];
				}
			}

			min[2] = blackTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (min[2] > blackTab[i][2]) {
					min[2] = blackTab[i][2];
				}
			}

		case "grey":
			min[0] = greyTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (min[0] > greyTab[i][0]) {
					min[0] = greyTab[i][0];
				}
			}

			min[1] = greyTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (min[1] > greyTab[i][1]) {
					min[1] = greyTab[i][1];
				}
			}

			min[2] = greyTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (min[2] > greyTab[i][2]) {
					min[2] = greyTab[i][2];
				}
			}
		}
		return min;
	}

	public float[] max(String couleur) {
		// trouve la plus grande valeur r,G et B pour une couleur mise en parametre
		float max[] = { 0, 0, 0 }; // vu qu'on ne peut retourner qu'une valeur c'est un tableau RGB qui contiendra
									// minR,minG et minB;

		switch (couleur) {
		case "red":
			// pour la couleur rouge par exemple on prend les 3 variables et on parcourt le
			// tableau redTab 3fois
			// la premiere fois pour trouver le minimum de la valeur rouge
			max[0] = redTab[0][0];
			for (int i = 0; i < NBVAL; i++) {// parcours le tableau pour touver le maximum
				if (max[0] < redTab[i][0]) {
					max[0] = redTab[i][0];
				}
			}
			// la deuxieme fois pour le minimum de la valeur verte
			max[1] = redTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (max[1] < redTab[i][1]) {
					max[1] = redTab[i][1];
				}
			}
			// la 3eme fois pour le minimum de la valeur bleu
			max[2] = redTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (max[2] < redTab[i][2]) {
					max[2] = redTab[i][2];
				}
			}
			break;

		case "green":
			max[0] = greenTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (max[0] < greenTab[i][0]) {
					max[0] = greenTab[i][0];
				}
			}
			max[1] = greenTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (max[1] > greenTab[i][1]) {
					max[1] = greenTab[i][1];
				}
			}
			max[2] = greenTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (max[2] > greenTab[i][2]) {
					max[2] = greenTab[i][2];
				}
			}
			break;

		case "blue":
			max[0] = blueTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (max[0] > blueTab[i][0]) {
					max[0] = blueTab[i][0];
				}
			}

			max[1] = redTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (max[1] > blueTab[i][1]) {
					max[1] = blueTab[i][1];
				}
			}
			max[2] = redTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (max[2] > blueTab[i][2]) {
					max[2] = blueTab[i][2];
				}
			}
			break;

		case "yellow":
			max[0] = yellowTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (max[0] > yellowTab[i][0]) {
					max[0] = yellowTab[i][0];
				}
			}

			max[1] = yellowTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (max[1] > yellowTab[i][1]) {
					max[1] = yellowTab[i][1];
				}
			}

			max[2] = yellowTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (max[2] > yellowTab[i][2]) {
					max[2] = yellowTab[i][2];
				}
			}
			break;

		case "white":
			max[0] = whiteTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (max[0] > whiteTab[i][0]) {
					max[0] = whiteTab[i][0];
				}
			}

			max[1] = whiteTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (max[1] > whiteTab[i][1]) {
					max[1] = whiteTab[i][1];
				}
			}

			max[2] = whiteTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (max[2] > whiteTab[i][2]) {
					max[2] = whiteTab[i][2];
				}
			}
			break;

		case "black":
			max[0] = blackTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (max[0] > blackTab[i][0]) {
					max[0] = blackTab[i][0];
				}
			}

			max[1] = blackTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (max[1] > blackTab[i][1]) {
					max[1] = blackTab[i][1];
				}
			}

			max[2] = blackTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (max[2] > blackTab[i][2]) {
					max[2] = blackTab[i][2];
				}
			}
			break;

		case "grey":
			max[0] = greyTab[0][0];
			for (int i = 0; i < NBVAL; i++) {
				if (max[0] > greyTab[i][0]) {
					max[0] = greyTab[i][0];
				}
			}

			max[1] = greyTab[0][1];
			for (int i = 0; i < NBVAL; i++) {
				if (max[1] > greyTab[i][1]) {
					max[1] = greyTab[i][1];
				}
			}

			max[2] = greyTab[0][2];
			for (int i = 0; i < NBVAL; i++) {
				if (max[2] > greyTab[i][2]) {
					max[2] = greyTab[i][2];
				}
			}
			break;
		}
		return max;
	}

	public float[] calculMoy(float[][] tab) {
		// calcul la moyenne des valeurs rgb d'un tableau
		float[] moyenne = { 0, 0, 0 };
		for (int i = 0; i < NBVAL; i++) {// additionne tous les elements
			moyenne[0] += tab[i][0];
			moyenne[1] += tab[i][1];
			moyenne[2] += tab[i][2];
		}

		moyenne[0] = moyenne[0] / NBVAL;// les divise par le nombre d'elements du tableau
		moyenne[1] = moyenne[1] / NBVAL;
		moyenne[2] = moyenne[2] / NBVAL;

		return moyenne;
	}

	public float[] invervalle(float min, float max, float moyenne) {// donne un intervalle élargit des min et max
																	// recuillit
		float ecartMin = 0, ecartMax = 0;
		ecartMin = moyenne - min;
		ecartMax = max - moyenne;
		float[] tab = null;
		tab[0] = ecartMin;
		tab[1] = ecartMax;
		return tab;
	}

	public boolean estDansLInterval(float val, float min, float max, float moyenne) {
		/*
		 * permet de savoir si un nombre se trouve dans l'intervalle selon la definition
		 * de l'intervalle qu'on a donné les argument sont: -une val qui va etre celle
		 * qu'on compare -la valeur minimum enregistrée -la valeur maximum enregistrée
		 * -la moyenne enregistrée
		 */
		float[] intervalle = this.invervalle(min, max, moyenne);
		boolean trueFalse = false;
		for (int i = 0; i < 3; i++) {// i<3 car il y a 3 valeurs dans un tableau RGB
			if (val >= intervalle[0] && val <= intervalle[1]) {
				trueFalse = true;
			} else {
				trueFalse = false;
				return trueFalse;
			}
		}
		return trueFalse;

	}

	public boolean estDansLIntervalleRGB(float[] colorSample, float[] minTab, float[] maxTab, float[] moyenne) {
//on utilise la methode estDansLIntervalle et on l'applique a un echantillion
		boolean trueFalse = false;
		for (int i = 0; i < 3; i++) {// car 3 valeurs dans un tableau rgb
			if (this.estDansLInterval(colorSample[i], minTab[i], maxTab[i], moyenne[i])) {
				trueFalse = true;
			} else
				trueFalse = false;
		}
		if (trueFalse) {
			return true;
		} else
			return false;
	}

	public String colorRenvoi() {
		String color = null;

//	 permettra d'identifier une couleur en fonction de l'intervalle auquel appartient ses valeurs RGB
		if (this.estDansLIntervalleRGB(colorSample, this.min("red"), this.max("red"), this.calculMoy(redTab))) {
			color = "RED";
		} else if (this.estDansLIntervalleRGB(colorSample, this.min("green"), this.max("green"),
				this.calculMoy(greenTab))) {
			color = "GREEN";
		} else if (this.estDansLIntervalleRGB(colorSample, this.min("blue"), this.max("blue"),
				this.calculMoy(blueTab))) {
			color = "BLUE";
		} else if (this.estDansLIntervalleRGB(colorSample, this.min("yellow"), this.max("yellow"),
				this.calculMoy(yellowTab))) {
			color = "YELLOW";
		} else if (this.estDansLIntervalleRGB(colorSample, this.min("black"), this.max("black"),
				this.calculMoy(blackTab))) {
			color = "BLACK";
		} else if (this.estDansLIntervalleRGB(colorSample, this.min("white"), this.max("white"),
				this.calculMoy(whiteTab))) {
			color = "WHITE";
		} else if (this.estDansLIntervalleRGB(colorSample, this.min("gre"), this.max("grey"),
				this.calculMoy(greyTab))) {
			color = "GREY";
		}

		System.out.println(color);
		return color;
	}

	public String colorScan() {// scan une couleur et retourne la couleur analysée sous forme de String
		this.echantillon();
		couleurCourant = this.colorRenvoi();
		return couleurCourant;
	}

	public String getCouleurCourant() {
		return couleurCourant;
	}

	public void afficheTest(String methode, String couleur) {// choix: min max moyenne
		// permet de tester les méthodes min max et moyenne
		switch (methode) {
		case "min":
			float[] min = this.min(couleur);
			System.out.println("min:");
			System.out.println("R:" + min[0]);
			System.out.println("G:" + min[1]);
			System.out.println("B:" + min[2]);
			break;

		case "max":
			float[] max = this.max(couleur);
			System.out.println("max:");
			System.out.println("R:" + max[0]);
			System.out.println("G:" + max[1]);
			System.out.println("B:" + max[2]);
			break;

		case "moyenne":
			float[] moy = this.calculMoy(redTab);// pour l'instant je met rouge par defaut parce que j'ai la flemme de
													// faire un autre switch si ça marche je rectifierai
			System.out.println("moyennee:" + moy);
			break;

		}
	}

	public void affiche() {
		// permet de tester la méthode setTab
		for (int i = 0; i < 5; i++) {
			System.out.println("R" + redTab[i][0]);
			System.out.println("G" + redTab[i][0]);
			System.out.println("B" + redTab[i][1]);
		}
	}
}