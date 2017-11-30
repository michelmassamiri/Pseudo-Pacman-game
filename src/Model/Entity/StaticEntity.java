package Model.Entity;

import java.util.Vector;

import Model.Resources.ResourceManager;
import Model.Resources.Resources;
import javafx.scene.image.ImageView;

public class StaticEntity implements Entity {

	private Vector<Float> pos;
	private ImageView imageView;
	
	public StaticEntity(Resources resource) {
		pos = new Vector<Float>();
		imageView = new ImageView(ResourceManager.getInstance().get(resource));
		
		pos.add(new Float(0.0f));
        pos.add(new Float(0.0f));
	}
	
	public StaticEntity(Resources resource, float x, float y) {
		pos = new Vector<Float>();
		imageView = new ImageView(ResourceManager.getInstance().get(resource));
		
		pos.add(x);
        pos.add(y);
	}

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

}
