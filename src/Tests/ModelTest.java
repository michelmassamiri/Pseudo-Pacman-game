package Tests;

import Model.Resources.ResourceManager;
import Model.Resources.Resources;
import javafx.embed.swing.JFXPanel;
import org.junit.*;
import Model.Model;

import static org.junit.Assert.assertEquals;


public class ModelTest {
    private Model m;
    private JFXPanel p;

    @Before
    public void init()
    {
        m = new Model();
        p = new JFXPanel();
        m.loadAll();
    }

    @Test
    public void loadingTest()
    {
        ResourceManager rm = ResourceManager.getInstance();
        for(Resources r : Resources.values())
        {
            assertEquals(rm.get(r) == null, false);
        }
    }
}
