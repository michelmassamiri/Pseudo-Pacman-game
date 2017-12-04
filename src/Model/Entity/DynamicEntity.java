package Model.Entity;

import Model.Directions;
import Model.Labyrinth;
import Model.Vertex;
import Model.Resources.ResourceManager;
import Model.Resources.Resources;
import javafx.scene.image.ImageView;

import java.util.Vector;

public abstract class DynamicEntity implements Entity{

    protected Vector<Float> pos;
    protected ImageView imageView;
    protected Labyrinth labyrinth;

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
            	if(getPosX() < Vertex.EAST_BORDER-1 && !labyrinth.isClosed(labyrinth.getVertexByXY((int)(float)getPosX(), (int)(float)getPosY()),dir) && !labyrinth.isClosedDoor(labyrinth.getVertexByXY((int)(float)getPosX(), (int)(float)getPosY()),dir))
            		setPosX(getPosX() + 1.0f);
                break;
            case WEST:
            	if(getPosX() > Vertex.WEST_BORDER && !labyrinth.isClosed(labyrinth.getVertexByXY((int)(float)getPosX(), (int)(float)getPosY()),dir) && !labyrinth.isClosedDoor(labyrinth.getVertexByXY((int)(float)getPosX(), (int)(float)getPosY()),dir))
            		setPosX(getPosX() - 1.0f);
                break;
            case NORTH:
            	if(getPosY() > Vertex.NORTH_BORDER && !labyrinth.isClosed(labyrinth.getVertexByXY((int)(float)getPosX(), (int)(float)getPosY()),dir) && !labyrinth.isClosedDoor(labyrinth.getVertexByXY((int)(float)getPosX(), (int)(float)getPosY()),dir))
            		setPosY(getPosY() - 1.0f);
                break;
            case SOUTH:
            	if(getPosY() < Vertex.SOUTH_BORDER-1 && !labyrinth.isClosed(labyrinth.getVertexByXY((int)(float)getPosX(), (int)(float)getPosY()),dir) && !labyrinth.isClosedDoor(labyrinth.getVertexByXY((int)(float)getPosX(), (int)(float)getPosY()),dir))
            		setPosY(getPosY() + 1.0f);
                break;
            default:
            	break;
        }
    }

}
