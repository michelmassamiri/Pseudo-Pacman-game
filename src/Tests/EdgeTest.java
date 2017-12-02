package Tests;

import Model.Directions;
import Model.Edge;
import Model.Labyrinth;
import Model.Vertex;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class EdgeTest {

    private Labyrinth l;
    private Vertex v1, v2;

    @Before
    public void init()
    {
        v1 = new Vertex(1, 2, 0);
        v2 = new Vertex(2, 2, 1);
        l = Labyrinth.getInstance();
        l.addVertex(v1);
        l.addVertex(v2);
        l.addEdge(v1, v2);
    }

    @Test
    public void connectionTest()
    {
        assertEquals(l.isConnected(v1, v2), true);
        assertEquals(l.isConnected(v2, v1), true);
        Vertex v = new Vertex(0, 0, 0);
        assertEquals(l.isConnected(v1, v), false);
        assertEquals(l.isConnected(v, v2), false);

        assertEquals(l.doesntExist(v1, Directions.EAST), false);
        assertEquals(l.doesntExist(v1, Directions.NORTH), true);
    }



}
