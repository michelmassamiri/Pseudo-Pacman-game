package Tests;


import Controller.GameController;
import Model.Directions;
import Model.Labyrinth;
import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.junit.*;
import static org.junit.Assert.*;

public class Player {

    private Model.Entity.Player p;
    private Scene s;
    private Pane pane;
    private FXRobot robot;

    @Before
    public void init()
    {
        p = Model.Entity.Player.getInstance();
        Labyrinth.getInstance().buildFull();
        pane = new Pane();
        s = new Scene(pane, 1000, 1000);
        s.setOnKeyPressed(GameController.getInstance().eventHandlerkey);
        robot = FXRobotFactory.createRobot(s);
    }

    @Test
    public void movementTest()
    {
        Float one = 1.0f, zero = 0.0f;
        assertEquals(p.getPosX(), zero);
        assertEquals(p.getPosY(), zero);
        p.move(Directions.SOUTH);
        p.move(Directions.EAST);
        assertEquals(p.getPosX(), one);
        assertEquals(p.getPosY(), one);
        p.move(Directions.NORTH);
        p.move(Directions.WEST);
        assertEquals(p.getPosX(), zero);
        assertEquals(p.getPosY(), zero);
    }

    /**
     * This method should be used to test the event handling part of the player.
     * A robot will simulate some keyboard entries.
     * If an exception is thrown, the catch part makes an impossible assert, which make this test not passable.
     * With that method, we are sure there is no possible fail from the robot if the test is passed.
     */
    @Test
    public void eventHandlingTest()
    {
        try{

            assertEquals(p.getPosX(), new Float(0.0f));
            assertEquals(p.getPosY(), new Float(0.0f));
            robot.keyPress(KeyCode.DOWN);
            robot.keyRelease(KeyCode.DOWN);
            robot.keyPress(KeyCode.RIGHT);
            robot.keyRelease(KeyCode.RIGHT);
            assertEquals(p.getPosX(), new Float(1.0f));
            assertEquals(p.getPosY(), new Float(1.0f));
            robot.keyPress(KeyCode.UP);
            robot.keyRelease(KeyCode.UP);
            robot.keyPress(KeyCode.LEFT);
            robot.keyRelease(KeyCode.LEFT);
            assertEquals(p.getPosX(), new Float(0.0f));
            assertEquals(p.getPosY(), new Float(0.0f));
        }
        catch (Exception e)
        {
            assertEquals(true, false);
        }
    }

    @Test
    public void playerKeySet()
    {
        assertEquals(p.getDirection(KeyCode.RIGHT), Directions.EAST);
        assertEquals(p.getDirection(KeyCode.LEFT), Directions.WEST);
        assertEquals(p.getDirection(KeyCode.UP), Directions.NORTH);
        assertEquals(p.getDirection(KeyCode.DOWN), Directions.SOUTH);

        p.setKey(KeyCode.A, Directions.EAST);
        assertEquals(p.getDirection(KeyCode.A),Directions.EAST);

        p.removeKey(KeyCode.UP);
        assertEquals(p.getDirection(KeyCode.UP),null);
    }
}
