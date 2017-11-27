package Controller;

import Model.Model;
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
	}
	
	/**
	 * Launch game's display
	 * @param primaryStage
	 */
	public void start(Stage primaryStage){
		viewFrame.start(primaryStage);
	}	
	
}
