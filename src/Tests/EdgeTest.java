package Tests;

import Model.Edge;
import Model.Vertex;
import org.junit.*;

public class EdgeTest {

    private Edge e;
    private Vertex v1, v2;

    @Before
    public void init()
    {
        v1 = new Vertex(1, 2, 0);
        v2 = new Vertex(2, 2, 1);
        //e = new Edge(v1, v2);
    }

}
