package Controller;

import java.util.Vector;

import Model.*;
import Model.Entity.*;
import Model.Resources.ResourceManager;
import Model.Resources.Resources;
import View.ViewFrame;

import javafx.stage.Stage;

public class GameController {
	private static GameController instance = null;
	private Model model;
	private ViewFrame viewFrame;
	
	
	/**
	 * Design Pattern Singleton of Game Controller
	 * @return Instance of game controller
	 */
	public static GameController getInstance(){
		if(instance == null)
			instance = new GameController();
		return instance;
	}
	/**
	 * Constructor of Controller allowing the management of events in the program.
	 * @param m Model du design pattern MVC
	 * @param v Vue du design pattern MVC
	 *//*
	private GameController(Model m, View v)
	{
		model = m;
		view = v;
	}*/
	
	private GameController(){
		model = new Model();
		viewFrame = ViewFrame.getInstance();
		ResourceManager.getInstance().load(Resources.PLAYER, "player.png");
		Player player = Player.getInstance();
		model.addEntity(player);
	}
	
	public Vector<Entity> getEntities(){
		return model.getEntities();
	}
	
	/**
	 * Launch game's display
	 * @param primaryStage
	 */
	public void start(Stage primaryStage){
		viewFrame.start(primaryStage);
	}	
	
}
