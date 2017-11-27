package Model.Entity;

import Model.Directions;
import javafx.event.Event;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Vector;

public class Player extends DynamicEntity {

    private HashMap<KeyCode, Directions> movementKeys;

    /**
     * Default constructor for the Player.
     * Initialise all numerical values to 0 and the movement keys on the UP, DOWN, LEFT and RIGHT keys
     */
    public Player()
    {
        movementKeys = new HashMap<>();
        pos = new Vector<>();
        imageView = new ImageView();

        pos.add(new Float(0.0f));
        pos.add(new Float(0.0f));

        movementKeys.put(KeyCode.RIGHT,Directions.EAST);
        movementKeys.put(KeyCode.LEFT, Directions.WEST);
        movementKeys.put(KeyCode.UP, Directions.NORTH);
        movementKeys.put(KeyCode.DOWN, Directions.SOUTH);
    }

    /**
     * Add or replace a key used to control the player
     * @param key the key to press in order to control the player
     * @param dir the direction in which the player should go when the key is pressed
     */
    public void setKey(KeyCode key, Directions dir) { movementKeys.put(key, dir); }

    /**
     * Remove a key from used to control the player
     * @param key the key to remove from the table
     */
    public void removeKey(KeyCode key) { movementKeys.remove(key); }

    /**
     * Function used to handle the player event.
     * @param event the keyEvent associated with the player
     */
    public void handleEvent(InputEvent event)
    {
        if(event instanceof KeyEvent)
        {
            KeyEvent e = (KeyEvent) event;
            Directions d = movementKeys.get(e.getCode());
            if(d != null)
                move(d);
        }
    }
}
