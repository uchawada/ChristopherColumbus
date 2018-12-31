package seFinal;

import java.awt.Point;
import java.util.Random;

public class OceanMap {
	Random rand = new Random();
	Random rand1 = new Random();
	int gridyStones = 10;
	int dimensions = 25;
	Point shipLocation;
	Point pirateShipLocation;
	Point pirateShipLocation2;
	Point islandLocation; 
	Point starLocation;
	Point whirlPoolLocation;
	Point treasureLocation;
	Point monsterLocation;
	Ship ship;
	boolean myGridyIsland[][];
	
	//constructor 
	public OceanMap(int dimensions, int gridyStones){
		this.dimensions = dimensions;
		myGridyIsland = new boolean[dimensions][dimensions];
		this.gridyStones = gridyStones;
		shipLocation = placeShip();
		pirateShipLocation = placePirateShip();
		pirateShipLocation2 = placePirateShip();
		islandLocation = placeIslands();	
		starLocation = placeStar();
		whirlPoolLocation = placeWhirlPool();
		treasureLocation = placeTreasure();
	}
	/*
	 * Gets the location for the Map, the ships and the island. 
	 */

	public boolean[][] getMap(){
		return myGridyIsland;
	}
	public Point getShipLocation(){
		return shipLocation;
	}
	public Point getPirateLocation(){
		return pirateShipLocation;
	}
	public Point getPirateLocation2(){
		return pirateShipLocation2;
	}
	public Point getIslandLocation(){
		return islandLocation;
	}
	public Point getStarLocation(){
		return starLocation;
	}
	public Point getWhirlPoolLocation(){
		return whirlPoolLocation;
	}
	public Point getTreasureLocation(){
		return treasureLocation;
	}

	/*
	 * Checks for open spots using the boolean visible 2D array, and places islands
	 * on those spots. 
	 */
	public Point placeIslands(){
		int islandsToPlace = gridyStones;
		int x = 0;
		int y = 0;
		while(islandsToPlace > 0){
			x = rand.nextInt(gridyStones);
			y = rand.nextInt(gridyStones);
			if(myGridyIsland[x][y] == false){
				//myGridyIsland[x][y] = true;
				islandsToPlace--;
			}
		}
		return new Point(x,y);
	}
	/*
	 * PLaces the ship on any open spot.
	 */
	public Point placeShip(){
		boolean placedShip = false;
		int x = 0; 
		int y = 0;
		while(!placedShip){
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if(myGridyIsland[x][y] == false){
				myGridyIsland[x][y] = true;
				placedShip = true;
			}
		//System.out.println("X = " + x + ", Y = " + y);
		}return new Point(x,y);	
	}

//	Places the pirateship one on an open spot on the grid. 
	public Point placePirateShip(){
		boolean placedPirateShip = false;
		int x = 0; 
		int y = 0;
		while(!placedPirateShip){
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if(myGridyIsland[x][y] == false){
				myGridyIsland[x][y] = true;
				placedPirateShip = true;
			}	
		}
		return new Point(x,y);	
	}

	public Point placeStar() {
		boolean placedStar = false;
		int x = 0; 
		int y = 0;
		while(!placedStar){
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if(myGridyIsland[x][y] == false){
				myGridyIsland[x][y] = true;
				placedStar = true;
			}	
		}
		return new Point(x,y);	
	}

	public Point placeWhirlPool(){
		boolean placedWhirl = false;
		int x = 0; 
		int y = 0;
		while(!placedWhirl){
		x = rand.nextInt(dimensions);
		y = rand.nextInt(dimensions);
		if(myGridyIsland[x][y] == false){
			myGridyIsland[x][y] = true;
				placedWhirl = true;
		}	
		}
		return new Point(x,y);	
	}
	
	public Point  placeTreasure() {
		boolean placedTreasure = false;
		int x = 0;
		int y = 0;
		while(!placedTreasure) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if(myGridyIsland[x][y] == false) {
				myGridyIsland[x][y] = true;
					placedTreasure = true;
			}
		}
		return new Point(x,y);
	}
}
	

