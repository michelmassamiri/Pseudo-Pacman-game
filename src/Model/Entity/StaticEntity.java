package Model.Entity;

import java.util.Vector;

import Model.Entity.Actions.Action;
import Model.Resources.ResourceManager;
import Model.Resources.Resources;
import javafx.scene.image.ImageView;

public class StaticEntity implements Entity {

	private Vector<Float> pos;
	private ImageView imageView;
	private Action action;

	public Float getPosX() { return pos.elementAt(0); }
	public Float getPosY() { return pos.elementAt(1); }
	public Vector<Float> getPos() { return (Vector<Float>) pos.clone(); }

	public void setPosX(Float x) { pos.set(0, x); }
	public void setPosY(Float y) { pos.set(0, y); }
	public void setPosX(float x) { pos.set(0, x); }
	public void setPosY(float y) { pos.set(0, y); }
	public void setPos(Vector<Float> pos) { this.pos = (Vector<Float>) pos.clone(); }

	public ImageView getDrawable() { return imageView; }
	public void setDrawable(Resources res)
	{
		ResourceManager rm = ResourceManager.getInstance();
		imageView = new ImageView(rm.get(res));
	}

	public void setAction(Action a) { action = a; }
    public Action getAction() { return action; }
    public void action() { action.start(); }

}
