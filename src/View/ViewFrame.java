package View;

import Controller.GameController;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ViewFrame {
	private static final int SPAN = 4; //Pixels for a unit
	private static final int WALL = 2; //thickness of the wall (in units)
	private static final int CELL = 9; //size of the cells (in units)
	public static final Paint WALLCOLOR = Color.CORAL;
		
	private static ViewFrame instance = null;
	private static Pane pane;
	private static Scene scene;
	
	
	public Text keyboardDirection = new Text();
	
	/**
	 * Design Pattern Singleton of ViewFrame
	 * @return Instance of view Frame
	 */
	public static ViewFrame getInstance(){
		if(instance == null){
			instance = new ViewFrame();
		}
		return instance;
	}
	
	/**
	 * Constructor of ViewFrame
	 */
	private ViewFrame()
	{
		pane = new Pane();
		
	}
	
	/**
	 * Launch game's display
	 * @param primaryStage the main stage of the game
	 */
	public void start(Stage primaryStage){
		primaryStage.setTitle("Pseudo PAC-MAN");
		drawFrame(primaryStage, 15, 15);

		GameController.getInstance().getEntities();

		int size = GameController.getInstance().getEntities().size();
		for (int i=0; i<size; ++i) {
			drawObject(GameController.getInstance().getEntities().elementAt(i).getDrawable(), GameController.getInstance().getEntities().elementAt(i).getPosX(), GameController.getInstance().getEntities().elementAt(i).getPosY() );
		}
		GameController.getInstance().drawLabyrinth();
		scene.setOnKeyPressed(GameController.getInstance().eventHandlerkey);
		primaryStage.show();
	}
	
	/**
	 * Playing environment without spirits nor diving wall. 
	 * @param stage Frame
	 * @param nbrX distance from one vertex to another in plane regarding X
	 * @param nbrY "										"	regarding Y
	 */
	public static void drawFrame(Stage stage, int nbrX, int nbrY){
				
		scene = new Scene(pane, 
						((WALL + CELL) * nbrX + WALL)* SPAN, 
						((WALL + CELL) * nbrY + WALL)* SPAN);
		scene.setFill(Color.WHITE);
		
		Rectangle square;
		stage.setScene(scene);
		square = new Rectangle (0, 0,
				SPAN * (nbrX * (CELL+WALL) + WALL), WALL *SPAN);
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		square = new Rectangle (0, SPAN * (nbrY	* (CELL+WALL)),
				SPAN * ( nbrX * (CELL+WALL) + WALL), WALL * SPAN );
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		
		square = new Rectangle (0, 0,
				WALL * SPAN, SPAN * (nbrY * (CELL+WALL) + WALL));
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		
		square = new Rectangle (SPAN * (nbrX * (CELL + WALL)), 0,
				WALL * SPAN, SPAN * (nbrY * (CELL + WALL)+ WALL));
		square.setFill(WALLCOLOR);
		pane.getChildren().add(square);
		
		for (int x=0 ; x < nbrX-1; ++x)
		{
			int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
			for (int y=0; y < nbrY-1; ++y)
			{
				int offsetY = ((WALL+CELL) + (WALL + CELL) * y) * SPAN;
				square = new Rectangle (offsetX, offsetY,
						WALL * SPAN, WALL *SPAN);
				square.setFill(WALLCOLOR);
				pane.getChildren().add(square);
			}
		}
				
	}
	
	
	/**
	 * Create a Wall in the coordinate given in arguments with the color given
	 * @param xs Source coordinate x
	 * @param ys Source coordinate y
	 * @param xt Destination coordinate x
	 * @param yt Destination coordinate y
	 * @param color Color of Wall
	 */
	public static void drawWall(int xs, int ys, int xt, int yt, Paint color){
		int x = 0, y = 0, xspan = 0, yspan = 0;
		if(ys==yt){
			x = ((WALL + CELL) + (WALL + CELL) * ((int)(xs+xt)/2)) * SPAN;
			y = (WALL + ys * (WALL + CELL)) * SPAN;
			xspan = WALL * SPAN;
			yspan = CELL * SPAN;
			Rectangle square = new Rectangle(x,y,xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		}
		else if (xs==xt){
			x = (WALL + xs * (WALL + CELL)) * SPAN;
			y = ((WALL + CELL) + (WALL + CELL) * ((int) (ys + yt)/2)) * SPAN;
			xspan = CELL * SPAN;
			yspan = WALL * SPAN;
			Rectangle square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		}
	}


	/**
	 * method that draws a sprite on the screen, at specified position.
	 * @param sprite the sprite to draw on the screen
	 * @param x the position of the sprite on the screen, regarding the x axis
	 * @param y the position of the sprite on the screen, regarding the y axis
	 */
	public void drawObject(ImageView sprite, Float x, Float y){
		pane.getChildren().add(sprite);
		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN) ;
		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN) ;
		
		sprite.setX(xt);
		sprite.setY(yt);
	}
	

	/*
	 * We use SetOnKeyPressed instead of setOnAction for the event that makes move player
	 */
	public void setOnKeyPressed () {
		
		pane.setOnKeyPressed(GameController.getInstance().eventHandlerkey);
	}
	

	/**
	 * Draw every object in their new position
	 */
	public void update() {
		int size = GameController.getInstance().getEntities().size();
		for (int i=0; i<size; i++) {
			updateObject(GameController.getInstance().getEntities().elementAt(i).getDrawable(), GameController.getInstance().getEntities().elementAt(i).getPosX(), GameController.getInstance().getEntities().elementAt(i).getPosY());
		}
	}
	
	public void updateObject(ImageView sprite, Float x, Float y){
		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN) ;
		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN) ;
		
		sprite.setX(xt);
		sprite.setY(yt);
	}
	
}
