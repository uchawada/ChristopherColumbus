package seFinal;

public interface Visitee {
	public void accept(Visitor visitee);
	public void operation(ShipStrategy pirateShip);
}
