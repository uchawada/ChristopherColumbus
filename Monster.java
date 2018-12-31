package seFinal;

import java.awt.Point;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class MonsterSprite{
	int x;
	int y;
	int scalingFactor;
	Image sharkImage;
	ImageView sharkImageView;
	Point monsterLocation;
	
	//static OceanMap oceanMap;
	//ShipStrategy shipStrategy;
	//Point currentMonsterLocation;
	
	MonsterSprite(int x, int y, int scalingFactor){
		this.x = x;
		this.y = y;
		this.monsterLocation = getMonsterLocation();
		this.scalingFactor = scalingFactor;
		try {
			sharkImage = new Image("image//shark.jpg",scalingFactor,scalingFactor,true,true); 
			sharkImageView = new ImageView(sharkImage);
		} catch (Exception e) {
			
		}
	}
	
	public ImageView getImage(){
		return sharkImageView;
	}
	void setX(int x){
		this.x = x;
		sharkImageView.setX(x * scalingFactor);
	}
	
	void setY(int y){
		this.y = y;
		sharkImageView.setY(y * scalingFactor);
	}
	
	int getX(){
		return x;
	}
	
	int getY(){
		return y;
	}
	
	public Point getMonsterLocation() {
		return new Point(x,y);
	}
	
}
	
public class Monster implements Runnable {
	
	//Boolean running = true;
	Random random = new Random();
	int scalingFactor;
	MonsterSprite[] monsterSprites = new MonsterSprite[10];//was 20
	Thread worker;
	private Point shipLoca = null;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private int interval;
	private OceanExplorer oe = null;
	private boolean collison = false;

	
	public Monster(OceanExplorer oe,int scalingFactor){
		for(int j = 0; j < 10; j++){ // was 20
			int x = random.nextInt(25);// was 50
			int y = random.nextInt(25);// was 50
			monsterSprites[j] = new MonsterSprite(x,y,scalingFactor);
		}
		
		this.scalingFactor = scalingFactor;
		this.oe = oe;
	}
	public boolean touchedShip() {
		for(MonsterSprite monster : monsterSprites) {
			if (monster.getMonsterLocation().equals(shipLoca))
				return true;
		}
		return false;
	}
	
	public void setShipLoca(Point shipLoca) {
		this.shipLoca = shipLoca;
	}

	public void ControlSubThread(int sleepInterval) {
		interval = sleepInterval;
	}
	
	public void setCollison() {
		collison = true;
	}
	
	public boolean getCollison() {
		return collison;
	}
	
	public void setMonsterSprites(MonsterSprite[] monsterSprites) {
		this.monsterSprites = monsterSprites;
	}

	
	public void addToPane(ObservableList<Node> sceneGraph){
		for(MonsterSprite monsterSprite: monsterSprites){
			ImageView sharkImageView = monsterSprite.getImage();
			System.out.println("Adding circle to pane: " + sharkImageView.getX() + " " + sharkImageView.getY());
			sceneGraph.add(sharkImageView);
		}
	}
			
	@Override
    public void run() {
      while (true && !collison) {
    	try {
    		// This is the speed
			Thread.sleep(500);
			
		} catch (InterruptedException e){ 
			Thread.currentThread().interrupt();
			System.out.println(
					"Thread was interrupted, Failed to complete operation");
		}
    	int i = 0;
    	for(MonsterSprite monsterSprite: monsterSprites){
    		if (collison) {
    			break;
    		}
    		// Move X
    		int xMove = monsterSprite.getX() + random.nextInt(3)-1;
    		if (xMove >=0 && xMove <= 24)// was 50
    			monsterSprite.setX(xMove);
    		// Move Y
    		int yMove = monsterSprite.getY() + random.nextInt(3)-1;
    		if (yMove >=0 && yMove <=24)// was 50
    			monsterSprite.setY(yMove);
    		
    		//System.out.println(monsterSprites[i].getMonsterLocation());
    		i++;
    		// This is where we were thinking about trying to add a if statement to see if the ship and monster on in the same cell
    	}
    	if (touchedShip()) {
    		//collison = true;
    		oe.shipMonsterCollison();
    	}
      }
	}

    public void start() {
        worker = new Thread(this);
        worker.start();
    }
  
    public void stop() {
        running.set(false);
    }
    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }
 
    boolean isRunning() {
        return running.get();
    }
 
}
