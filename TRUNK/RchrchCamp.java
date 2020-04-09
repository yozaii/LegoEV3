package rechercheCamp;
import lejos.hardware.Button;

public void RchrchCamp() {// cette methode permet au robot de se diriger vers le camps de depart, si il rencontre un palet il lattrape et le depose dans le camp oppose.
	this.getRoues().MoveForwardSpeed(250f);// le robot avance
    do {
            this.getColor().colorScan();//renvoi la couleur
            this.getUltrason().DistanceScan();//renvoi la distance entre le robot et le mur.
    }while ((!(this.ToucherDeux()) && !(this.getUltrason().DistanceLimit(0.1f)) && (this.getColor().getCouleurCourant()!= "YELLOW" && this.getColor().getCouleurCourant()!= "WHITE"&& this.getColor().getCouleurCourant() != "RED")));
    if(this.ToucherDeux()) {// si le capteur tactile est touche
        this.roues.Stop();// le robot sarrete
        this.getPinces().Close();//il attrape le palet
        this.getRoues().RotateD("Left", 180);//le robot fait une rotation de 180degres.
        this.CherchWhite();// il se dirige vers la ligne blanche et depose le palet
        this.setGo(false);//comme cest false donc le robot a croise un palet il devra le deposer.
    }
    
    else if ( this.getColor().getCouleurCourant()=="WHITE"){ //si cest le robot est sur la ligne blanche il fait une rotation de 90degres et s'arrete.
    	    this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
    	    this.getRoues().RotateD("Left", 90);//Le robot fait une rotation de 90degres vers la gauche.
    }
    
    else if (this.getUltrason().DistanceLimit(0.1f)) {//si le robot capte le mur il fait une rotation de 180degres par la gauche et le programme est recommence.
    	this.getRoues().RotateD("Left", 180);
    	this.CherchWhite();
    	
    }
    else if ( this.getColor().getCouleurCourant()=="YELLOW"){// si le robot est sur la ligne jaune il avance jusqu'a ce que il capte la ligne blanche.
    		this.RotateUntilLinePerp("YELLOW", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur jaune.
    		this.getRoues().MoveForwardSpeed(250f);// Le robot avance.
    		do {//Le robot renvoi la couleur tant que celle ci n est pas blanche.
    			this.getColor().colorScan();// renvoi la couleur.
    		}
    		while (this.getColor().getCouleurCourant()!="WHITE");
    		this.RotateUntilLinePerp("WHITE", "Left");//Le robot tourne autour de lui meme jusqu'a qu'il capte la couleur blanche.
    	    this.getRoues().RotateD("Left", 90);// rotation par la gauche de 90degres.
    		
    }
    else if ( this.getColor().getCouleurCourant()=="RED"){// si la couleur captee est rouge le robot avance jusqua capater la couleur blanche.
    			this.RotateUntilLinePerp("RED", "Left");
    			this.getRoues().MoveForwardSpeed(250f);
	    		do {
	    			this.getColor().colorScan();
	    		}
	    		while (this.getColor().getCouleurCourant()!="WHITE");
	    		this.RotateUntilLinePerp("WHITE", "Left");
	    	    this.getRoues().RotateD("Left", 90);
   	 
    }
    
    this.getRoues().setOrrien(0);
    
}