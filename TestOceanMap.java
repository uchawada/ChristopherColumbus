package seFinal;
import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class TestOceanMap {

	int mapSize = 25;
	
	@Test
	public void test() {
		OceanMap om = new OceanMap(25,10);
		assertEquals(25,om.getMap().length);
		
		//Testing PlayerShip Location
		Point point = om.getShipLocation();
		assertTrue(point.getX() < mapSize);
		assertTrue(point.getY() < mapSize);
		
		//Testing Pirate Location
		point = om.getPirateLocation();
		assertTrue(point.getX() < mapSize);
		assertTrue(point.getY() < mapSize);
		
		//Testing Pirate2 Location
		point = om.getPirateLocation2();
		assertTrue(point.getX() < mapSize);
		assertTrue(point.getY() < mapSize);
		
		//Testing Star Location
		point = om.getStarLocation();
		assertTrue(point.getX() < mapSize);
		assertTrue(point.getY() < mapSize);
		
		//Testing Whirlpool Location
		point = om.getWhirlPoolLocation();
		assertTrue(point.getX() < mapSize);
		assertTrue(point.getY() < mapSize);
		
		//Testing Treasure Location
		point = om.getTreasureLocation();
		assertTrue(point.getX() < mapSize);
		assertTrue(point.getY() < mapSize);
		
	}

}
