package seFinal;

import java.awt.Point;

public class Star implements Visitee{

	OceanMap oceanMap;
	
	public Star(OceanMap oceanMap){
		this.oceanMap = oceanMap;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public void operation(ShipStrategy pirateShip){
//		pirateShip.movePirateShip();
		System.out.println(pirateShip.getShipPace());
	}

}
