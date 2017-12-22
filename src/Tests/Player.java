package Tests;


import Model.Directions;
import Model.Labyrinth;
import javafx.scene.input.KeyCode;
import org.junit.*;
import static org.junit.Assert.*;

public class Player{

    private Model.Entity.Player p;

    @Before
    public void init()
    {
        p = Model.Entity.Player.getInstance();
        Labyrinth.getInstance().buildFull();
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
