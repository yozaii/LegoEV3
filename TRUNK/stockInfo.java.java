package classInfo;

public class stockInfo {
	private String campOpp; //camp oppos� o� le robot devrai d�poser le palet
	private String campDep; //camp de d�part du robot
	private int orrien;
//constructeur	
public stockInfo (){
	this.campOpp= null;
	this.campOpp=null;
	this.orrien= 0;
}

//permet d'acc�der � l'orientation du robot.
public int getOrrien(){
	return orrien;
}

public void setOrrien(int x) {
	 orrien+=x;
}

}

		