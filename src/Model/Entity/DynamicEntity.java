package Model.Entity;

import Model.Directions;
import javafx.scene.image.ImageView;

import java.util.Vector;

public abstract class DynamicEntity implements Entity{

    protected Vector<Float> pos;
    protected ImageView imageView;

    public Float getPosX() { return pos.elementAt(0); }
    public Float getPosY() { return pos.elementAt(1); }
    public Vector<Float> getPos() { return (Vector<Float>) pos.clone(); }

    public void setPosX(Float x) { pos.set(0, x); }
    public void setPosY(Float y) { pos.set(0, y); }
    public void setPosX(float x) { pos.set(0, x); }
    public void setPosY(float y) { pos.set(0, y); }
    public void setPos(Vector<Float> pos) { this.pos = (Vector<Float>) pos.clone(); }

    public ImageView getDrawable() { return imageView; }

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
                setPosX(getPosY() - 1.0f);
                break;
            case SOUTH:
                setPosX(getPosY() + 1.0f);
                break;
        }
    }

}
