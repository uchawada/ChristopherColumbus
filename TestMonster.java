package seFinal;
import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class TestMonster {

	int scalingFactor = 40;
	
	@Test
	public void test() {
		OceanExplorer oe = new OceanExplorer();
		Monster m = new Monster(oe,scalingFactor);
		MonsterSprite monsterSprites[] = new MonsterSprite[10];
		
		for(int j = 0; j< 10;j++) {
			monsterSprites[j] = new MonsterSprite(j,j,scalingFactor);
		}
		m.setMonsterSprites(monsterSprites);
		m.setShipLoca(new Point(10,10));
		assertFalse(m.touchedShip());
		
		m.setShipLoca(new Point(9,9));
		assertTrue(m.touchedShip());
		
		m.setCollison();
		assertTrue(m.getCollison());
		
		
	}

}
