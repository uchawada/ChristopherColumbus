package seFinal;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.text.Text;

public class FastPirateShip implements ShipStrategy, Observer{

	OceanMap oceanMap;
	Point currentPirateLocation;
	Ship ship;
	ShipStrategy shipPace;
	
	public FastPirateShip(OceanMap oceanMapIn, Point currentPirateLocationIN){
		currentPirateLocation = currentPirateLocationIN;
		oceanMap = oceanMapIn;
		ship = new Ship(oceanMapIn);
	}
	@Override
	public void movePirateShip() {

		//if piratelocation is equal to ship location, print Win. 
		if(currentPirateLocation.equals(oceanMap.getWhirlPoolLocation()) || currentPirateLocation.equals(oceanMap.getStarLocation())){
			currentPirateLocation = new Point(0,0);
			ship.deleteObserver(this);	
		}
		//else moves pirateShip by to the right
		else if (currentPirateLocation.x - oceanMap.getShipLocation().x < 0){
			if(currentPirateLocation.x < oceanMap.dimensions && oceanMap.getMap()[currentPirateLocation.x+1][currentPirateLocation.y] != true){
				currentPirateLocation.x ++;
			}	
		}
		//else moves pirateShip to its left
		else if(currentPirateLocation.x > 0 && oceanMap.getMap()[currentPirateLocation.x-1][currentPirateLocation.y] != true){
			currentPirateLocation.x --;
		}
		
		//moves the pirateShip up, along the Y-axis.
		if (currentPirateLocation.y - oceanMap.getShipLocation().y < 0){
			if(currentPirateLocation.y < oceanMap.dimensions && oceanMap.getMap()[currentPirateLocation.x][currentPirateLocation.y+1] != true){
				currentPirateLocation.y ++;
			}	
		}
		//moves pirateShip down 
		else if(currentPirateLocation.y > 0 && oceanMap.getMap()[currentPirateLocation.x][currentPirateLocation.y-1] != true){
			currentPirateLocation.y --;
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Ship){
			oceanMap.shipLocation = ((Ship)o).getShipLocation();
			movePirateShip();
		}	
		
	}
	@Override
	public String getShipPace() {
		return "Touched the whirlpool ... Pirate ship is in Fast mode."; 
	}

	}
	
