package seFinal;

import java.awt.Point;
import java.util.Observer;
import java.util.Random;

import seFinal.Monster;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage; 

public class OceanExplorer extends Application {
	Pane myPane;
	final int dimensions = 15;
	final int gridyIslands = 15;
	final int scale = 40;
	Image shipImage;
	ImageView shipImageView;
	ImageView pirateImageView;
	ImageView pirateImageView2;
	ImageView starImageView;
	ImageView whirlImageView;
	OceanMap oceanMap;
	Point treasureLocation;
	Scene scene; 
	Ship ship;
	Star star;
	Whirlpool whirlpool;
	ShipStrategy pirateShip1;
	ShipStrategy pirateShip2;
	Monster shark;
	Thread sharkThread;
	ImageView treasureView;
	ImageView sharkView;
	boolean collison = false;
	//Monster monster;
	//Monster monsterSprites;

	Random rand = new Random();

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage OceanStage) throws Exception {
		//calls the methods for outputting the required output. 
		oceanMap = new OceanMap(dimensions, gridyIslands);	 
		ship = new Ship(oceanMap);
		myPane = new AnchorPane();

		star = new Star(oceanMap);
		//treasure = new Treasure(oceanMap);
		whirlpool = new Whirlpool(oceanMap);
		ship.visit(star);
		//ship.visit(treasure);

		drawMap();
		loadIslands(10);
		loadShipImage();
		loadStarImage();
		loadWhirlImage();

		pirateShip1 = new DefaultPirateShip(oceanMap, oceanMap.getPirateLocation());
		ship.addObserver((Observer) pirateShip1);

		pirateShip2 = new DefaultPirateShip(oceanMap, oceanMap.getPirateLocation2());
		ship.addObserver((Observer) pirateShip2);

		loadPirateShipImage();
		scene = new Scene(myPane, 600, 600);
		OceanStage.setScene(scene);
		OceanStage.setTitle("Columbus sails the Ocean Blue");

		shark = new Monster(this,scale);
		shark.addToPane(myPane.getChildren());
		sharkThread = new Thread(shark);
		sharkThread.start();

		loadTreasureImage();

		shark.setShipLoca(ship.currentLocation);

		OceanStage.show();
		startSailing();
	}

	public void shipMonsterCollison() {
		Text text = new Text();
		text.setLayoutX(10);
		text.setLayoutY(35);
		text.setFont(new Font(33));
		text.setStrokeWidth(1);
		text.setStroke(Color.WHITE);
		new Notifier(text);
		myPane.getChildren().add(text);

		Notifier.getInstance().setText("You Lost... the shark ate you!");
		Notifier.getInstance().cancelTimer();
		collison = true;
		shark.setCollison();
		return;
	}


	private void drawMap() {
		//making a grid FOCUS!!!!
		for(int x = 0; x < dimensions; x++){
			for(int y = 0; y < dimensions; y ++){
				Rectangle rect = new Rectangle(x*scale, y*scale, scale, scale);
				rect.setStroke(Color.BLACK); //we want the black outline
				rect.setFill(Color.PALETURQUOISE);
				myPane.getChildren().add(rect);
			}
		}
	}
	//	/*
	//	 * Uses random function to generate random number of islands, and setsv= all the visible
	//	 * spots to false, as they are occupied. 
	//	 */
	private void loadIslands(int i){
		int count = 0;
		Random rand = new Random();
		while(count<i){
			int x;
			int y;
			//makes sure islands all are in diff spots
			while(true){
				x = rand.nextInt(dimensions);
				y = rand.nextInt(dimensions);
				if(oceanMap.getMap()[x][y] != true){
					break;
				}
			}
			Image islandImage = new Image("image//island.jpg",scale,scale,true,true); 
			ImageView islandImageView = new ImageView(islandImage);
			islandImageView.setX(x*scale);
			islandImageView.setY(y*scale);
			//islands are marked as '1' in 2d int array
			oceanMap.getMap()[x][y] = true;
			myPane.getChildren().add(islandImageView);
			count++;
		}
	}
	private void loadShipImage() {
		/*
		 * loads ships image on the oceanMap by creating an ImageView Object and sets visible to false, 
		 * as that place is occupied by the ocean Map. 
		 */
		Image shipImage = new Image("image//ship.png", scale, scale, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getShipLocation().x * scale);
		shipImageView.setY(ship.getShipLocation().y * scale);	
		myPane.getChildren().add(shipImageView);	
		oceanMap.getMap()[oceanMap.getShipLocation().x][oceanMap.getShipLocation().y] = false;
	}

	//loads two pirate ships on the oceanMap and sets visible to false
	private void loadPirateShipImage(){
		Image pirateImage = new Image("image//pirateShip.png", scale, scale, true, true);
		pirateImageView = new ImageView(pirateImage);
		pirateImageView.setX(oceanMap.getPirateLocation().x * scale);
		pirateImageView.setY(oceanMap.getPirateLocation().y * scale);
		myPane.getChildren().add(pirateImageView);	
		pirateImageView2 = new ImageView(pirateImage);
		pirateImageView2.setX(oceanMap.getPirateLocation2().x * scale);
		pirateImageView2.setY(oceanMap.getPirateLocation2().y * scale);
		myPane.getChildren().add(pirateImageView2);
		oceanMap.getMap()[oceanMap.getPirateLocation().x][oceanMap.getPirateLocation().y] = false;
		oceanMap.getMap()[oceanMap.getPirateLocation2().x][oceanMap.getPirateLocation2().y] = false;

	}
	private void loadStarImage(){
		Image starImage = new Image("image//star.png", scale, scale, true, true);
		starImageView = new ImageView(starImage);
		starImageView.setX(oceanMap.getStarLocation().x * scale);
		starImageView.setY(oceanMap.getStarLocation().y * scale);	
		myPane.getChildren().add(starImageView);	
		oceanMap.getMap()[oceanMap.getStarLocation().x][oceanMap.getStarLocation().y] = false;
	}

	private void loadWhirlImage(){
		Image whirlImage = new Image("image//whirlpool.jpg", scale, scale, true, true);
		whirlImageView = new ImageView(whirlImage);
		whirlImageView.setX(oceanMap.getWhirlPoolLocation().x * scale);
		whirlImageView.setY(oceanMap.getWhirlPoolLocation().y * scale);	
		myPane.getChildren().add(whirlImageView);	
		oceanMap.getMap()[oceanMap.getWhirlPoolLocation().x][oceanMap.getWhirlPoolLocation().y] = false;
	}

	private void loadTreasureImage() {
		Image treasureImage = new Image("image//treasure.png",scale,scale,true,true);
		treasureView = new ImageView(treasureImage);
		treasureView.setX(oceanMap.getTreasureLocation().x*scale);
		treasureView.setY(oceanMap.getTreasureLocation().y*scale);
		myPane.getChildren().add(treasureView);
		oceanMap.getMap()[oceanMap.getTreasureLocation().x][oceanMap.getTreasureLocation().y] = false;
	}

	private void loadMonsterImage() {
		Image monsterImage = new Image("image//shark.jpg",scale,scale,true,true);
		sharkView = new ImageView(monsterImage);
		sharkView.setX(oceanMap.getShipLocation().x*scale);
		sharkView.setY(oceanMap.getShipLocation().y*scale);
		myPane.getChildren().add(sharkView);
	}

	/*
	 * Uses the event Handler to handle the sailing method of the ship and the pirate Ships, 
	 * It uses the goEast, goWest... methods from the ship class for the ships to move. 
	 */
	private void startSailing(){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent ke) {

				System.out.println(pirateShip1.getShipPace());
				System.out.println(pirateShip2.getShipPace());

				if(collison) {
					return;
				}

				if(ship.currentLocation.equals(oceanMap.getStarLocation())){
					ship.deleteObserver(pirateShip1);
					pirateShip1 = new SlowPirateShip(oceanMap, oceanMap.getPirateLocation());
					ship.addObserver(pirateShip1);
					star.operation(pirateShip1);

					ship.deleteObserver(pirateShip2);
					pirateShip2 = new SlowPirateShip(oceanMap, oceanMap.getPirateLocation2());
					ship.addObserver(pirateShip1);
					star.operation(pirateShip2);
				}
				if(ship.currentLocation.equals(oceanMap.getWhirlPoolLocation())){
					ship.deleteObserver(pirateShip1);					
					pirateShip1 = new FastPirateShip(oceanMap, oceanMap.getPirateLocation());
					ship.addObserver(pirateShip1);
					whirlpool.operation(pirateShip1);
				}

				if(oceanMap.getPirateLocation2().equals(oceanMap.getShipLocation())){
					Text text = new Text();
					text.setLayoutX(10);
					text.setLayoutY(35);
					text.setFont(new Font(33));
					text.setStrokeWidth(1);
					text.setStroke(Color.WHITE);
					new Notifier(text);
					myPane.getChildren().add(text);

					Notifier.getInstance().setText("You Lost... The pirates got you!");
					Notifier.getInstance().cancelTimer();
					return;
				}
				if(oceanMap.getPirateLocation().equals(oceanMap.getShipLocation())){
					Text text = new Text();
					text.setLayoutX(10);
					text.setLayoutY(35);
					text.setFont(new Font(33));
					text.setStrokeWidth(1);
					text.setStroke(Color.WHITE);
					new Notifier(text);
					myPane.getChildren().add(text);

					Notifier.getInstance().setText("You Lost... The pirates got you!");
					Notifier.getInstance().cancelTimer();
					return;
				}
				if (ship.currentLocation.equals(oceanMap.getTreasureLocation())) {
					Text text = new Text();
					text.setLayoutX(10);
					text.setLayoutY(35);
					text.setFont(new Font(33));
					text.setStrokeWidth(1);
					text.setStroke(Color.WHITE);
					new Notifier(text);
					myPane.getChildren().add(text);

					Notifier.getInstance().setText("You Found the Treasure!... You Won!");
					Notifier.getInstance().cancelTimer();
					return;
				}


				/*if (ship.currentLocation.equals(ship.currentLocation)) {
					for(int i=0; i<10; i++)
                {
              		if(ship.currentLocation.equals(monster.monsterSprites[i].getMonsterLocation()))
                       {
							Text text = new Text();
							text.setLayoutX(10);
							text.setLayoutY(35);
							text.setFont(new Font(33));
							text.setStrokeWidth(1);
							text.setStroke(Color.WHITE);
							new Notifier(text);
							myPane.getChildren().add(text);

							Notifier.getInstance().setText("You Lose");
							return;
                       }
                }
			}*/

				KeyCode k = ke.getCode();
				if(!collison) {

					switch(ke.getCode()){
					case RIGHT:
						ship.move("EAST");
						break;
					case LEFT:
						ship.move("WEST");
						break;
					case UP:
						ship.move("NORTH");
						break;
					case DOWN:
						ship.move("SOUTH");
						break;
					default:
						break;

					}
				}
				shark.setShipLoca(ship.currentLocation);
				if(shark.touchedShip()) {
					shipMonsterCollison();
				}

				shipImageView.setX(ship.getShipLocation().x*scale);
				shipImageView.setY(ship.getShipLocation().y*scale);

				pirateImageView.setX(oceanMap.getPirateLocation().x * scale);
				pirateImageView.setY(oceanMap.getPirateLocation().y * scale);

				pirateImageView2.setX(oceanMap.getPirateLocation2().x * scale);
				pirateImageView2.setY(oceanMap.getPirateLocation2().y * scale);

			}

		});  
	}

}
