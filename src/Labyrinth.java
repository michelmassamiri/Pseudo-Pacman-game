import Controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Labyrinth extends Application{
	private static GameController game;
	
	public static void main(String[] args) {
		game = GameController.getInstance();
		launch(args);
	}
	
	
	@Override
	public void start(Stage stage) {
		try {
			game.start(stage);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
				
	}
	
	@Override
	public void stop() {
		System.exit(0);
	}

}
