package Model.Entity;

import Model.Resources.Resources;
import javafx.scene.image.ImageView;
import java.util.Vector;

public interface Entity {

    /**
     * @return the X coordinate of the Entity's position
     */
    Float getPosX();

    /**
     * @return the Y coordinate of the Entity's position
     */
    Float getPosY();

    /**
     * @return the position of the Entity
     */
    Vector<Float> getPos();

    /**
     * Setter for the position
     * @param x the position on the X coordinate of the Entity.
     */
    void setPosX(Float x);
    /**
     * Setter for the position
     * @param y the position on the Y coordinate of the Entity.
     */
    void setPosY(Float y);
    /**
     * Setter for the position
     * @param x the position on the X coordinate of the Entity.
     */
    void setPosX(float x);
    /**
     * Setter for the position
     * @param y the position on the Y coordinate of the Entity.
     */
    void setPosY(float y);
    /**
     * Setter for the position
     * @param pos the position of the Entity. Must be the X coordinate first and then the Y coordinate.
     */
    void setPos(Vector<Float> pos);

    /**
     * Getter for the sprite of the Entity
     * @return the drawable image, that can be used in a javafx.scene
     */
    ImageView getDrawable();

    /**
     * Setter of the sprite for the Entity. In order for it to work properly, the resource must be loaded.
     * @param res the name of the Resource with which the ImageView will be made.
     */
    void setDrawable(Resources res);
	
}
