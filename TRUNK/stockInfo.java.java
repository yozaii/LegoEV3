package classInfo;

public class stockInfo {
	private String campOpp; //camp opposé où le robot devrai déposer le palet
	private String campDep; //camp de départ du robot
	private int orrien;
//constructeur	
public stockInfo (){
	this.campOpp= null;
	this.campOpp=null;
	this.orrien= 0;
}

//permet d'accéder à l'orientation du robot.
public int getOrrien(){
	return orrien;
}

public void setOrrien(int x) {
	 orrien+=x;
}

}

		