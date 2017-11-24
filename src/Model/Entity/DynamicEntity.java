package Model.Entity;

import Model.Directions;
import javafx.scene.image.ImageView;

import java.util.Vector;

public abstract class DynamicEntity implements Entity{

    private Vector<Float> pos;
    private ImageView imageView;

    @Override
    public Float getPosX() { return pos.elementAt(0); }
    @Override
    public Float getPosY() { return pos.elementAt(1); }
    @Override
    public Vector<Float> getPos() { return (Vector<Float>) pos.clone(); }

    @Override
    public void setPosX(Float x) { pos.set(0, x); }
    @Override
    public void setPosY(Float y) { pos.set(0, y); }
    @Override
    public void setPosX(float x) { pos.set(0, x); }
    @Override
    public void setPosY(float y) { pos.set(0, y); }
    @Override
    public void setPos(Vector<Float> pos) { this.pos = (Vector<Float>) pos.clone(); }

    @Override
    public ImageView getDrawable() { return imageView; }

    /**
     * method used to move a DynamicEntity in a specific direction.
     * @param dir the direction in which the Entity must move
     */
    public void move(Directions dir)
    {

    }

}
