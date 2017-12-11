package Controller;

import java.util.Vector;

import Model.*;
import Model.Entity.*;
import Model.Resources.Resources;

import View.ViewFrame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController {
	private static GameController instance = null;
	private Model model;
	private ViewFrame viewFrame;
	private Player player;
	private BadGuy bad1, bad2;
	private StaticEntityDoor door;
	private StaticEntityCandy candy1, candy2, candy3, candy4;
	private Timeline timeline;
	
	

	private int score;
	private boolean gameOver;
	private boolean win;
	
	/**
	 * Design Pattern Singleton of the Game Controller
	 * @return Instance of game controller
	 */
	public static GameController getInstance(){
		if(instance == null)
			instance = new GameController();
		return instance;
	}
	
	private GameController(){
		model = new Model();
		viewFrame = ViewFrame.getInstance();
	    model.loadAll();
		player = Player.getInstance();
		bad1 = new BadGuy();
		bad1.setPosX(1);
		bad1.setPosY(2);
		bad2 = new BadGuy(3,6);
		candy1 = new StaticEntityCandy(Resources.CANDY_1, 2, 1, 10);
		candy2 = new StaticEntityCandy(Resources.CANDY_2, 3, 4, 20);
		candy3 = new StaticEntityCandy(Resources.CANDY_3, 5, 5, 30);
		candy4 = new StaticEntityCandy(Resources.CANDY_4, 6, 5, 40);
		StaticEntity buttonOpen = new StaticEntity(Resources.BUTTON_OPEN, 7, 5);
		StaticEntity buttonClose = new StaticEntity(Resources.BUTTON_CLOSED, 8, 5);
		door = new StaticEntityDoor(Resources.DOOR_OPEN, 9, 5);
		model.addEntity(player);
		model.addEntity(bad1);
		model.addEntity(candy1);
		model.addEntity(candy2);
		model.addEntity(candy3);
		model.addEntity(candy4);
		model.addEntity(buttonOpen);
		model.addEntity(buttonClose);
		model.addEntity(door);
		model.addEntity(bad2);
		timeline = new Timeline(new KeyFrame(
		        Duration.millis(2500),
		        eventMoveBadGuy));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}
	
	public EventHandler<ActionEvent> eventMoveBadGuy = new EventHandler<ActionEvent>(){

		public void handle(ActionEvent event) {
			bad1.Manhatan(model.getLabyrinth());
			bad2.Manhatan(model.getLabyrinth());
			viewFrame.update();
			if(bad1.getAction().isStartable() || bad2.getAction().isStartable()){
				gameOver= true;
				timeline.stop();
				viewFrame.gameOver(score);
			}
		}
	};
	
	public Vector<Entity> getEntities(){
		return model.getEntities();
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setScore(int value){
		score = score + value;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setGameOver(boolean value){
		gameOver =  value;
	}
	
	public boolean getGameOver(){
		return gameOver;
	}
	
	public void setWin(boolean value){
		win =  value;
	}
	
	public boolean getWin(){
		return win;
	}
	
/* * Launch game's display
	 * @param primaryStage the javafx's main stage
	 */
	public void start(Stage primaryStage){
		viewFrame.start(primaryStage);
		primaryStage.show();

	}	
	public EventHandler<KeyEvent> eventHandlerkey = new EventHandler<KeyEvent>() {
		@Override
		public void handle (KeyEvent event) {

			KeyCode keycode = event.getCode();
			if (player.getDirection(keycode) != null){
				Player.getInstance().move(Player.getInstance().getDirection(keycode));
				
				if(candy1.getAction().isStartable()){
					candy1.getAction().actions();
					candy1.setPosX(-1);
					viewFrame.update();
					model.supEntity(candy1);
					viewFrame.getPane().getChildren().remove(candy1);
				}
				if(candy2.getAction().isStartable()){
					candy2.getAction().actions();
					candy2.setPosX(-1);
					viewFrame.update();
					model.supEntity(candy2);
					viewFrame.getPane().getChildren().remove(candy2);
				}
				if(candy3.getAction().isStartable()){
					candy3.getAction().actions();
					candy3.setPosX(-1);
					viewFrame.update();
					model.supEntity(candy3);
					viewFrame.getPane().getChildren().remove(candy3);
				}
				if(candy4.getAction().isStartable()){
					candy4.getAction().actions();
					candy4.setPosX(-1);
					viewFrame.update();
					model.supEntity(candy4);
					viewFrame.getPane().getChildren().remove(candy4);
				}
				viewFrame.update();
				
				if(door.getAction().isStartable()){
					door.getAction().actions();
					if(win){
						timeline.stop();
						viewFrame.win(score);
					}
				}
				if(bad1.getAction().isStartable() || bad2.getAction().isStartable()){
					gameOver= true;
					timeline.stop();
					viewFrame.gameOver(score);
				}
							
			}
			if (keycode == KeyCode.ESCAPE)
				System.exit(0);
		}	
		
			
	};
	
	/**
	 * Take the graph and paint it in the view
	 */
	public void drawLabyrinth() {
		Labyrinth labyrinth = model.getLabyrinth();
		for(int i =0; i<=Vertex.EAST_BORDER; i++) {
			for(int j=0; j<=Vertex.SOUTH_BORDER; j++) {
				//corner
				if(i==0 && j==0){
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, ViewFrame.WALLCOLOR);
					
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, Color.AZURE);
				}
				if(i==0 && j==Vertex.SOUTH_BORDER){
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.NORTH))					
						ViewFrame.drawWall(i, j, i, j-1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, ViewFrame.WALLCOLOR);
					
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.NORTH))
						ViewFrame.drawWall(i, j, i, j-1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, Color.AZURE);
				}
				if(i==Vertex.EAST_BORDER  && j==0){
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, ViewFrame.WALLCOLOR);
					
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, Color.AZURE);
				}
				if(i==Vertex.EAST_BORDER && j==Vertex.SOUTH_BORDER){
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.NORTH))					
						ViewFrame.drawWall(i, j, i, j-1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, ViewFrame.WALLCOLOR);
					
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.NORTH))
						ViewFrame.drawWall(i, j, i, j-1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, Color.AZURE);
				}
				//first column
				if( i==0 && 0<j && j<Vertex.SOUTH_BORDER){
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.NORTH))					
						ViewFrame.drawWall(i, j, i, j-1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, ViewFrame.WALLCOLOR);
					
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.NORTH))
						ViewFrame.drawWall(i, j, i, j-1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, Color.AZURE);
				}
				//first row
				if(0<i && i<Vertex.EAST_BORDER && j==0){
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, ViewFrame.WALLCOLOR);
					
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, Color.AZURE);
				}
				//last row
				if(0<i && i<Vertex.EAST_BORDER && j==Vertex.SOUTH_BORDER){
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.NORTH))					
						ViewFrame.drawWall(i, j, i, j-1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, ViewFrame.WALLCOLOR);
					
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.NORTH))
						ViewFrame.drawWall(i, j, i, j-1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, Color.AZURE);
				}
				//last column
				if( i==Vertex.EAST_BORDER && 0<j && j<Vertex.SOUTH_BORDER){
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.NORTH))					
						ViewFrame.drawWall(i, j, i, j-1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, ViewFrame.WALLCOLOR);
					
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.NORTH))
						ViewFrame.drawWall(i, j, i, j-1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, Color.AZURE);
				}
				//not border
				if(0<i && i<Vertex.EAST_BORDER && 0<j && j<Vertex.SOUTH_BORDER){
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.NORTH))					
						ViewFrame.drawWall(i, j, i, j-1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, ViewFrame.WALLCOLOR);
					if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, ViewFrame.WALLCOLOR);
					
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.NORTH))
						ViewFrame.drawWall(i, j, i, j-1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
						ViewFrame.drawWall(i, j, i, j+1, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.WEST))
						ViewFrame.drawWall(i, j, i-1, j, Color.AZURE);
					if (labyrinth.isClosedDoor(labyrinth.getVertexByXY(i,j), Directions.EAST))
						ViewFrame.drawWall(i, j, i+1, j, Color.AZURE);
				}
			}
		}
	}	
	
}
