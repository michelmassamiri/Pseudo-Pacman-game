package Model.Entity;

import Model.Directions;
import Model.ResourceManager;
import Model.Resources;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Vector;

public abstract class DynamicEntity implements Entity{

    protected Vector<Float> pos;
    protected ImageView imageView;

    public Float getPosX() { return pos.elementAt(0); }
    public Float getPosY() { return pos.elementAt(1); }
    public Vector<Float> getPos() { return (Vector<Float>) pos.clone(); }

    public void setPosX(Float x) { pos.set(0, x); }
    public void setPosY(Float y) { pos.set(1, y); }
    public void setPosX(float x) { pos.set(0, x); }
    public void setPosY(float y) { pos.set(1, y); }
    public void setPos(Vector<Float> pos) { this.pos = (Vector<Float>) pos.clone(); }

    public ImageView getDrawable() { return imageView; }

    public void setDrawable(Resources res)
    {
        ResourceManager rm = ResourceManager.getInstance();
        imageView = new ImageView(rm.get(res));
    }

    /**
     * method used to move a DynamicEntity in a specific direction.
     * @param dir the direction in which the Entity must move
     */
    public void move(Directions dir)
    {
        switch (dir) {
            case EAST:
                setPosX(getPosX() + 1.0f);
                break;
            case WEST:
                setPosX(getPosX() - 1.0f);
                break;
            case NORTH:
                setPosY(getPosY() - 1.0f);
                break;
            case SOUTH:
                setPosY(getPosY() + 1.0f);
                break;
        }
    }

}
