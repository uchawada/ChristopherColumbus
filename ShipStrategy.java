package seFinal;

import java.util.Observer;

public interface ShipStrategy extends Observer {
	public void movePirateShip();
	public String getShipPace();
	
}
