package View;

import com.sun.javafx.scene.traversal.Direction;

import Controller.GameController;
import Model.Vertex;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ViewFrame {
	static final int SPAN = 4; //Pixels for a unit
	static final int WALL = 2; //thickness of the wall (in units)
	static final int CELL = 9; //size of the cells (in units)
	public static final Paint WALLCOLOR = Color.CORAL;
	
	private static ViewFrame instance = null;
	private static Pane pane;
	
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
	 * @param primaryStage
	 */
	public void start(Stage primaryStage){
		primaryStage.setTitle("Pseudo PAC-MAN");
		drawFrame(primaryStage, 15, 15);
		int size = GameController.getInstance().getEntities().size();
		for (int i=0; i<size; ++i) {
			drawObject(GameController.getInstance().getEntities().elementAt(i).getDrawable(), GameController.getInstance().getEntities().elementAt(i).getPosX(), GameController.getInstance().getEntities().elementAt(i).getPosY() );
		}
		drawLabyrinth();
		primaryStage.show();
	}
	
	/**
	 * Playing environment without sprites nor diving wall. 
	 * @param stage Frame
	 * @param nbrX distance from one vertex to another in plane regarding X
	 * @param nbrY "										"	regarding Y
	 */
	public static void drawFrame(Stage stage, int nbrX, int nbrY){
		//TODO by jeniffer
		
		Scene scene = new Scene(pane, 
						((WALL + CELL) * nbrX + WALL)* SPAN, 
						((WALL + CELL) * nbrY + WALL)* SPAN);
		scene.setFill(Color.WHITE);
		
		Rectangle square;
		stage.setScene(scene);
		square = new Rectangle (0, 0,
				SPAN * (nbrX * (CELL+WALL) + WALL), WALL *SPAN);
		square.setFill(Color.CORAL);
		pane.getChildren().add(square);
		square = new Rectangle (0, SPAN * (nbrY	* (CELL+WALL)),
				SPAN * ( nbrX * (CELL+WALL) + WALL), WALL * SPAN );
		square.setFill(Color.CORAL);
		pane.getChildren().add(square);
		
		square = new Rectangle (0, 0,
				WALL * SPAN, SPAN * (nbrY * (CELL+WALL) + WALL));
		square.setFill(Color.CORAL);
		pane.getChildren().add(square);
		
		square = new Rectangle (SPAN * (nbrX * (CELL + WALL)), 0,
				WALL * SPAN, SPAN * (nbrY * (CELL + WALL)+ WALL));
		square.setFill(Color.CORAL);
		pane.getChildren().add(square);
		
		for (int x=0 ; x < nbrX-1; ++x)
		{
			int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
			for (int y=0; y < nbrY-1; ++y)
			{
				int offsetY = ((WALL+CELL) + (WALL + CELL) * y) * SPAN;
				square = new Rectangle (offsetX, offsetY,
						WALL * SPAN, WALL *SPAN);
				square.setFill(Color.CORAL);
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
	 * Create the object in the frame with sprite in the nameJPG
	 * @param nameJPG String name of file which content sprite.
	 */
	public void drawObject(ImageView sprite, Float x, Float y){
		pane.getChildren().add(sprite);
		double xt = (int) ((ViewFrame.WALL + x * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN) ;
		double yt = (int) ((ViewFrame.WALL + y * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN) ;
		
		sprite.setX(xt);
		sprite.setY(yt);
	}
	
	public void update() {
		int size = GameController.getInstance().getEntities().size();
		for (int i=0; i<size; i++) {
			GameController.getInstance().getEntities().elementAt(i).getDrawable();
		}
	}
	
	public void drawLabyrinth() {
		GameController.getInstance().getModel().getLabyrinth();
		for(int i =0; i<Vertex.EAST_BORDER; i++) {
			for(int j=0; j<Vertex.SOUTH_BORDER; j++) {
				if (isClosed(GameController.getInstance().getModel().getLabyrinth().getVertexByXY(i,j), Direction.UP))
					drawWall(i, j, i-1, j, WALLCOLOR);
				if (isClosed(GameController.getInstance().getModel().getLabyrinth().getVertexByXY(i,j), Direction.DOWN))
					drawWall(i, j, i+1, j, WALLCOLOR);
				if (isClosed(GameController.getInstance().getModel().getLabyrinth().getVertexByXY(i,j), Direction.LEFT))
					drawWall(i, j, i, j-1, WALLCOLOR);
				if (isClosed(GameController.getInstance().getModel().getLabyrinth().getVertexByXY(i,j), Direction.RIGHT))
					drawWall(i, j, i, j+1, WALLCOLOR);
				
			}
		}
	}
	
}
