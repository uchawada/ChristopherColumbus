package seFinal;

public class Whirlpool implements Visitee{

	OceanMap oceanMap;
	
	public Whirlpool(OceanMap oceanMap){

		this.oceanMap = oceanMap;
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void operation(ShipStrategy pirateShip) {
//		pirateShip.movePirateShip();
		System.out.println(pirateShip.getShipPace());

	}
	

}
