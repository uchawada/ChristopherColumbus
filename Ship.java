package seFinal;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Ship extends Observable implements Visitor{

	Point currentLocation;
	int dimensions = 15;
	int GridyStones = 10;
	OceanMap oceanMap;
	Random rand = new Random();
	Observer pirateShip;
	Star star;
	
	/*
	 * constructor for Ship class, 
	 * generates a random number for the ship to be placed on. 
	 */
	public Ship(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getShipLocation();
		star = new Star(oceanMap);
		while(true){
			int x = rand.nextInt(dimensions);
			int y = rand.nextInt(dimensions);
			if(this.oceanMap.getMap()[x][y] != true){
				currentLocation = new Point(x,y);
				break;
			}
		}
	}
	
	public Point getShipLocation(){
		return currentLocation;
	}
	
	/*
	 * checks the boundaries, and if a cell is not open,
	 * and moves arround the direction accordingly. 
	 * Moves east on key right, 
	 * 		west on key left, 
	 * 		north on key up, and 
	 * 		south on key down. 
	 */

	public void move(String s){
		if(s.equals("EAST")){
			if(currentLocation.x < (dimensions -1) && 
				oceanMap.getMap()[currentLocation.x+1][currentLocation.y] != true){
				currentLocation.x++;
				setChanged();
				notifyObservers();
				System.out.println(currentLocation);
			}
			}
		else if(s.equals("WEST")){
			if(currentLocation.x > 0 &&
				oceanMap.getMap()[currentLocation.x-1][currentLocation.y] != true){
				currentLocation.x--;
				setChanged();
				notifyObservers();
				System.out.println(currentLocation);
			}
		}
		else if(s.equals("NORTH")){
			if(currentLocation.y > 0 &&
				oceanMap.getMap()[currentLocation.x][currentLocation.y - 1] != true){
				currentLocation.y--;
				setChanged();
				notifyObservers();
				System.out.println(currentLocation);
			}
			}
		else if (s.equals("SOUTH")){
			if(currentLocation.y < (dimensions - 1) &&
				oceanMap.getMap()[currentLocation.x][currentLocation.y + 1] != true){
				currentLocation.y++;
				setChanged();
				notifyObservers();
				System.out.println(currentLocation);
				
			}
		}
		
		
	}
	
	@Override
	public void visit(Star star) {
//		if(currentLocation.equals(oceanMap.getStarLocation())){
////			star.operation();
//			System.out.println("Calling the slow pirate ship");
//		}
	}

	@Override
	public void visit(Whirlpool whirlpool) {
	
			
	}

	//@Override
	//public void visit(Treasure treasure) {
		// TODO Auto-generated method stub
		
	//}
		
}
