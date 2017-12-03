package Controller;

import java.util.Vector;

import Model.*;
import Model.Entity.*;
import Model.Resources.Resources;

import View.ViewFrame;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameController {
	private static GameController instance = null;
	private Model model;
	private ViewFrame viewFrame;
	private Player player;
	

	private int score;
	private boolean gameOver; 
	
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
		BadGuy bad1 = new BadGuy();
		bad1.setPosX(1);
		bad1.setPosY(2);
		BadGuy bad2 = new BadGuy(3,6);
		StaticEntity candy1 = new StaticEntity(Resources.CANDY_1, 2, 1);
		StaticEntity candy2 = new StaticEntity(Resources.CANDY_2, 3, 4);
		StaticEntity candy3 = new StaticEntity(Resources.CANDY_3, 5, 5);
		StaticEntity candy4 = new StaticEntity(Resources.CANDY_4, 6, 5);
		StaticEntity buttonOpen = new StaticEntity(Resources.BUTTON_OPEN, 7, 5);
		StaticEntity buttonClose = new StaticEntity(Resources.BUTTON_CLOSED, 8, 5);
		StaticEntity door = new StaticEntity(Resources.DOOR_OPEN, 9, 5);
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
		
	}
	
	public Vector<Entity> getEntities(){
		return model.getEntities();
	}
	
	public Model getModel() {
		return model;
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
			player.setDirection(keycode);
			player.move(player.getDirection(keycode));
			viewFrame.update();
		}	
		
			
	};
	
	/**
	 * Take the graph and paint it in the view
	 */
	public void drawLabyrinth() {
		Labyrinth labyrinth = model.getLabyrinth();
		for(int i =1; i<Vertex.EAST_BORDER; i++) {
			for(int j=1; j<Vertex.SOUTH_BORDER; j++) {
				if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.NORTH))
					ViewFrame.drawWall(i, j, i, j-1, ViewFrame.WALLCOLOR);
				if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.SOUTH))
					ViewFrame.drawWall(i, j, i, j+1, ViewFrame.WALLCOLOR);
				if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.WEST))
					ViewFrame.drawWall(i, j, i-1, j, ViewFrame.WALLCOLOR);
				if (labyrinth.isWall(labyrinth.getVertexByXY(i,j), Directions.EAST))
					ViewFrame.drawWall(i, j, i+1, j, ViewFrame.WALLCOLOR);
				
			}
		}
	}
	
}
