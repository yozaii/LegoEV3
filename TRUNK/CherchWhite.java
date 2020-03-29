package chercheWhite;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class CherchWhite {// methode qui permet de diriger le robot vers la ligne blanche quel que soit sa position de depart.
	this.getRoues().MoveForwardSpeed(250f);// le robot avance
do {
    this.getColor().colorScan();//renvoi la couleur
    this.getUltrason().DistanceScan();//renvoi la distance entre le robot et le mur.
}while ((!(this.ToucherDeux()) && !(this.getUltrason().DistanceLimit(0.1f)) && (this.getColor().getCouleurCourant()!= "YELLOW" && this.getColor().getCouleurCourant()!= "WHITE"&& this.getColor().getCouleurCourant() != "RED")));//avancer jusqu'a la ligne rougre, jaune ou blanche.

if ( this.getColor().getCouleurCourant()=="WHITE"){ //si cest le robot est sur la ligne blanche il fait une rotation de 90degres et s'arrete.
    this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
    this.getRoues().RotateD("Left", 90);
}

else if (this.getUltrason().DistanceLimit(0.1f)) {//si le robot capte le mur il fait une rotation de 180degres par la gauche et le programme est recommence.
this.getRoues().RotateD("Left", 180);
this.CherchWhite();

}
else if ( this.getColor().getCouleurCourant()=="YELLOW"){// si le robot est sur la ligne jaune il avance jusqu'a ce que il capte la ligne blanche.
	this.RotateUntilLinePerp("YELLOW", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur jaune.
	this.getRoues().MoveForwardSpeed(250f);
	do {
		this.getColor().colorScan();
	}
	while (this.getColor().getCouleurCourant()!="WHITE");
	this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
    this.getRoues().RotateD("Left", 90);
	
}
else if ( this.getColor().getCouleurCourant()=="RED"){// si cest rouge le robot avance jusqu'a la ligne blanche.
		this.RotateUntilLinePerp("RED", "LEFT");
		this.getRoues().MoveForwardSpeed(250f);
		do {
			this.getColor().colorScan();
		}
		while (this.getColor().getCouleurCourant()!="WHITE");
		this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
	    this.getRoues().RotateD("Left", 90);

}

this.getRoues().setOrrien(0);

}

}



