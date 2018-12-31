package seFinal;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip{

	static OceanMap oceanMap;
	ShipStrategy shipStrategy;
	Point currentPirateLocation;
	
	public PirateShip(OceanMap oceanMapIN, Point currentPirateLocationIN){
		oceanMap = oceanMapIN;
		currentPirateLocation = currentPirateLocationIN;
	}

	/*
	 * The update method is used for the moving the pirateShips according to the main Ship
	 */
}

