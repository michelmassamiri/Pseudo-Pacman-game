package Model.Entity;

import Model.Directions;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Vector;

public class Player extends DynamicEntity {

    private HashMap<KeyCode, Directions> movementKeys;

    public Player()
    {
        movementKeys = new HashMap<>();
        pos = new Vector<>(2);
        imageView = new ImageView();

        movementKeys.put(KeyCode.RIGHT,Directions.EAST);
        movementKeys.put(KeyCode.LEFT, Directions.WEST);
        movementKeys.put(KeyCode.UP, Directions.NORTH);
        movementKeys.put(KeyCode.DOWN, Directions.SOUTH);
    }

    public void setKey(KeyCode key, Directions dir)
    {
        movementKeys.put(key, dir);
    }

    public void handleEvent(KeyEvent event)
    {
        Directions d = movementKeys.get(event.getCode());
        if(d != null)
            move(d);
    }
}
