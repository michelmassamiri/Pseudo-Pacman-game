package View;

import java.awt.Frame;

import Controller.GameController;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ViewFrame {
	static final int SPAN = 4; //Pixels for a unit
	static final int WALL = 2; //thickness of the wall (in units)
	static final int CELL = 9; //size of the cells (in units)
	public static final Paint WALLCOLOR = Color.BURLYWOOD;
	
	private static GridPane pane;
	private ImageView imageView;
	
	public ViewFrame()
	{
		
	}
	
	public void start(Stage primaryStage){
		primaryStage.setTitle("Pseudo PAC-MAN");
		drawFrame(primaryStage, nbx, nby);
		primaryStage.show();
	}
	
	/**
	 * 
	 * @param stage
	 * @param nbrX
	 * @param nbrY
	 */
	public static void drawFrame(Stage stage, int nbrX, int nbrY){
		//TODO by jeniffer
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
	 * @param nameJPG String name of file which content sprite. nameJPG must be ./Image/nameofJPG
	 */
	private void drawObject(String nameJPG){
		Image image = new Image( getClass().getResource(nameJPG).toExternalForm());
		imageView = new ImageView(image);
		Frame.pane.getChildren().add(this.imageView);
		double xt = (int) ((ViewFrame.WALL + sprite.getX() * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		double yt = (int) ((ViewFrame.WALL + sprite.getY() * (ViewFrame.WALL + ViewFrame.CELL)) * ViewFrame.SPAN);
		
		imageView.setX(xt);
		imageView.setY(yt);
	}
	
}
