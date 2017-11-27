package Tests;


import Model.Directions;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import org.junit.*;


import java.awt.*;

import static org.junit.Assert.*;

public class Player {

    private Model.Entity.Player p;

    @Before
    public void init()
    {
        p = new Model.Entity.Player();
    }

    @Test
    public void movementTest()
    {
        assertEquals(p.getPosX(), new Float(0.0f));
        assertEquals(p.getPosY(), new Float(0.0f));
        p.move(Directions.SOUTH);
        p.move(Directions.EAST);
        assertEquals(p.getPosX(), new Float(1.0f));
        assertEquals(p.getPosY(), new Float(1.0f));
        p.move(Directions.NORTH);
        p.move(Directions.WEST);
        assertEquals(p.getPosX(), new Float(0.0f));
        assertEquals(p.getPosY(), new Float(0.0f));
    }

    @Test
    public void eventHandlingTest()
    {
        try{
            Robot robot = new Robot();
            /*assertEquals(p.getPosX(), new Float(0.0f));
            assertEquals(p.getPosY(), new Float(0.0f));
            p.move(Directions.SOUTH);
            p.move(Directions.EAST);
            assertEquals(p.getPosX(), new Float(1.0f));
            assertEquals(p.getPosY(), new Float(1.0f));
            p.move(Directions.NORTH);
            p.move(Directions.WEST);
            assertEquals(p.getPosX(), new Float(0.0f));
            assertEquals(p.getPosY(), new Float(0.0f));*/
        }
        catch (Exception e)
        {
            assertEquals(true, false);
        }
    }
}
