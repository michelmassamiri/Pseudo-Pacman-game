import Controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Labyrinth extends Application{

	public static void main(String[] args) {
		launch();
	}
	
	
	@Override
	public void start(Stage stage) {
		Controller.makeInstance();
		Controller.start(stage);
		
	}
	
	@Override
	public void stop() {
		System.exit(0);
	}

}
