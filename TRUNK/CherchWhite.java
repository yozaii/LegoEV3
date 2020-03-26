package chercheWhite;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class CherchWhite {
	this.getRoues().MoveForwardSpeed(250f); 
	this.getUltrason().DistanceScan();
	  
    do{ 
            this.getColor().colorScan();
    
    }while ((!(robot.ToucherDeux()) && (robot.getColor().getCouleurCourant()!= "YELLOW" && robot.getColor().getCouleurCourant()!= "WHITE"&& robot.getColor().getCouleurCourant() != "RED")));
   
    if ( robot.getColor().getCouleurCourant()=="WHITE"){
    	    this.getRoues().RotateD(180,"Left");
    	    this.getRoues().Stop();	
    }
    	else if ( robot.getColor().getCouleurCourant()=="YELLOW"){
    		robot.FollowLine;
    	}
    		else if ( robot.getColor().getCouleurCourant()=="RED"){
    			robot.FollowLine;
   	 
    		
    		}
   
    


    

