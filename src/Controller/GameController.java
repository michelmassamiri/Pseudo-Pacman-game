package Controller;

import java.util.Vector;

import Model.*;
import Model.Entity.*;
import Model.Entity.Actions.StaticButtonCloseAction;
import Model.Entity.Actions.StaticButtonOpenAction;
import Model.Entity.Actions.StaticCandyAction;
import Model.Entity.Actions.StaticDoorAction;
import Model.Resources.Resources;

import View.ViewFrame;

import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;
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
    private PlayerController playerController;
    private Model model;
    private ViewFrame viewFrame;
    private Vector<StaticEntity> candyList;
    private StaticEntity buttonOpen, buttonClose, door;
    private Timeline timeline;
    private Vector<BadGuy> badGuys;


    private int score;
    private boolean gameOver;
    private boolean win;

    /**
     * Design Pattern Singleton of the Game Controller
     *
     * @return Instance of game controller
     */
    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    /**
     *
     */
    private GameController() {
        model = new Model();
        viewFrame = ViewFrame.getInstance();
        playerController = new PlayerController();
        model.loadAll();
        //Edge wall = model.getLabyrinth().closeDoorRandom();
        Vertex vertex = model.getLabyrinth().randomVertexByDir();
        Edge wall = model.getLabyrinth().getEdge(vertex, Directions.NORTH);
        while (wall == null) {
            vertex = model.getLabyrinth().randomVertexByDir();
            wall = model.getLabyrinth().getEdge(vertex, Directions.NORTH);
        }
        model.getLabyrinth().closeDoor(wall);
        //player = Player.getInstance();

        badGuys = new Vector<>();
        badGuys.add(new BadGuy(1, 2));
        badGuys.add(new BadGuy(3, 6));

        candyList = new Vector<StaticEntity>();
        candyList.add(new StaticEntity(Resources.CANDY_1, 2, 1, new StaticCandyAction(10)));
        candyList.add(new StaticEntity(Resources.CANDY_2, 3, 4, new StaticCandyAction(20)));
        candyList.add(new StaticEntity(Resources.CANDY_3, 5, 5, new StaticCandyAction(30)));
        candyList.add(new StaticEntity(Resources.CANDY_4, 6, 5, new StaticCandyAction(40)));

        buttonOpen = new StaticEntity(Resources.BUTTON_OPEN, vertex.getX(), vertex.getY() + 1, new StaticButtonOpenAction(wall));
        buttonClose = new StaticEntity(Resources.BUTTON_CLOSED, vertex.getX(), vertex.getY() - 1, new StaticButtonCloseAction(wall));
        door = new StaticEntity(Resources.DOOR_OPEN, 9, 5, new StaticDoorAction());

        model.addEntity(Player.getInstance());
        for (StaticEntity candy : candyList) {
            model.addEntity(candy);
        }
        model.addEntity(buttonOpen);
        model.addEntity(buttonClose);
        model.addEntity(door);
        for (BadGuy b : badGuys)
            model.addEntity(b);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(1400),
                eventMoveBadGuy));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    /**
     *
     */
    public EventHandler<ActionEvent> eventMoveBadGuy = new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
            for (BadGuy b : badGuys)
                b.Manhatan(model.getLabyrinth());
            for (BadGuy b : badGuys) {
                if (b.getAction().isStartable()) {
                    gameOver = true;
                    timeline.stop();
                    viewFrame.gameOver(score);
                }
            }
        }
    };

    /**
     * @return
     */
    public Vector<Entity> getEntities() {
        return model.getEntities();
    }

    /**
     * Necessary for the view in order to draw the Labyrinth
     * @return the Labyrinth instance
     */
    public Labyrinth getLabyrinth() {
    	return model.getLabyrinth() ;
    }

    /**
     * @param value
     */
    public void setScore(int value) {
        score = score + value;
    }

    /**
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * @param value
     */
    public void setGameOver(boolean value) {
        gameOver = value;
    }

    /**
     * @return
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * @param value
     */
    public void setWin(boolean value) {
        win = value;
    }

    /**
     * @return
     */
    public boolean getWin() {
        return win;
    }

    /**
     * Launch game's display
     *
     * @param primaryStage the javafx's main stage
     */
    public void start(Stage primaryStage) {
        viewFrame.start(primaryStage, Vertex.EAST_BORDER + 1, Vertex.SOUTH_BORDER + 1);
        primaryStage.show();

    }

    /**
     *
     */
    public EventHandler<KeyEvent> eventHandlerkey = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {

            KeyCode keycode = event.getCode();
            if (Player.getInstance().getDirection(keycode) != null) {
            	playerController.moveChange(keycode);

                    for (StaticEntity candy : candyList) {
                        if (candy.getAction().isStartable()) {
                            candy.getAction().actions();
                            candy.setPosX(-1);
                            viewFrame.update();
                            model.supEntity(candy);
                            viewFrame.getPane().getChildren().remove(candy);
                        }
                    viewFrame.update();


                    if (door.getAction().isStartable()) {
                        door.getAction().actions();
                        if (win) {
                            timeline.stop();
                            viewFrame.win(score);
                        }
                    }
                    if (buttonOpen.getAction().isStartable()) {
                        buttonOpen.getAction().actions();
                        System.out.println("open");
                    }
                    if (buttonClose.getAction().isStartable()) {
                        buttonClose.getAction().actions();
                        System.out.println("close");
                    }

                    for (BadGuy b : badGuys) {
                        if (b.getAction().isStartable()) {
                            gameOver = true;
                            timeline.stop();
                            viewFrame.gameOver(score);
                        }
                    }

                }
                if (keycode == KeyCode.ESCAPE)
                    System.exit(0);
            }


        }
    };

        /**
         * @param edge
         */
        public void openDoor(Edge edge) {
            model.getLabyrinth().openDoor(edge);
            ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), Color.WHITE);
        }

        /**
         * @param edge
         */
        public void closeDoor(Edge edge) {
            model.getLabyrinth().closeDoor(edge);
            ViewFrame.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), Color.BLUE);
        }
}
