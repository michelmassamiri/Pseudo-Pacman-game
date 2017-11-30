package Model.Entity;

import Model.Directions;
import Model.Resources.ResourceManager;
import Model.Resources.Resources;
import javafx.event.Event;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Vector;

public class Player extends DynamicEntity {

    private HashMap<KeyCode, Directions> movementKeys;
    private static Player INSTANCE = null;

    /**
     * Method that returns the instance of the Player singleton
     * @return the instance of the player
     */
    public static Player getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new Player();
        return INSTANCE;
    }

    /**
     * Default constructor for the Player.
     * Initialise all numerical values to 0 and the movement keys on the UP, DOWN, LEFT and RIGHT keys
     */
    private Player()
    {
        movementKeys = new HashMap<>();
        pos = new Vector<>();
        imageView = new ImageView(ResourceManager.getInstance().get(Resources.PLAYER));

        pos.add(new Float(0.0f));
        pos.add(new Float(0.0f));

        setKey(KeyCode.RIGHT,Directions.EAST);
        setKey(KeyCode.LEFT, Directions.WEST);
        setKey(KeyCode.UP, Directions.NORTH);
        setKey(KeyCode.DOWN, Directions.SOUTH);
    }

    /**
     * Register a new key or replace a key used to control the player
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
     * Method that gives a direction to make the player move when a key is entered
     * @param key the key that is pressed
     * @return the direction if the key is registered, however returns null.
     **/
    public Directions getDirection(KeyCode key) { return movementKeys.get(key); }
}
