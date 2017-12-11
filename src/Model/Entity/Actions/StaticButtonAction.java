package Model.Entity.Actions;

import Controller.GameController;
import Model.Entity.StaticEntityButton;

public class StaticButtonAction extends AbstractAction{
	
	private	StaticEntityButton button;
	private float x, y;
	 
	
    public StaticButtonAction(StaticEntityButton entity, float x, float y ){
    	super();
    	button = entity;
    	this.x = x;
    	this.y = y;
    }
    
    public boolean isStartable() {
        return button.getPos().equals(p.getPos());
    }
    
	@Override
	public void actions() {
		GameController.getInstance().deleteWall(x, y);
	}
}
