package Tests;

import Model.Directions;
import Model.Vertex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VertexTest {

    private Vertex v;


    @Before
    public void init()
    {
        v = new Vertex(0, 0, 0);
    }

    @Test
    public void positionTest()
    {
        assertEquals(v.getX(), 0);
        assertEquals(v.getY(), 0);
        v.setX(5);
        v.setY(8);
        assertEquals(v.getX(), 5);
        assertEquals(v.getY(), 8);
        v.setY(0);
        v.setX(0);
    }

    @Test
    public void outboundTest()
    {
        assertEquals(v.inBorders(Directions.NORTH), false);
        assertEquals(v.inBorders(Directions.EAST), true);
        assertEquals(v.inBorders(Directions.WEST), false);
        assertEquals(v.inBorders(Directions.SOUTH), true);
        v.setX(Vertex.EAST_BORDER);
        v.setY(Vertex.SOUTH_BORDER);
        assertEquals(v.inBorders(Directions.NORTH), true);
        assertEquals(v.inBorders(Directions.EAST), false);
        assertEquals(v.inBorders(Directions.WEST), true);
        assertEquals(v.inBorders(Directions.SOUTH), false);
        v.setX(1);
        v.setY(1);
        assertEquals(v.inBorders(Directions.NORTH), true);
        assertEquals(v.inBorders(Directions.EAST), true);
        v.setX(14);
        v.setY(14);
        assertEquals(v.inBorders(Directions.SOUTH), true);
        assertEquals(v.inBorders(Directions.WEST), true);
    }
}
