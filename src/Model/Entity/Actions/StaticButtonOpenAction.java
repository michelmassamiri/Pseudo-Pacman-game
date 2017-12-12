package Model.Entity.Actions;

import Controller.GameController;
import Model.Edge;
import Model.Entity.StaticEntityButtonOpen;

public class StaticButtonOpenAction extends AbstractAction{
	private	StaticEntityButtonOpen button;
	private Edge edge;
	
    public StaticButtonOpenAction(StaticEntityButtonOpen entity, Edge edge ){
    	super();
    	button = entity;
    	this.edge = edge;
    }
    
    public boolean isStartable() {
        return button.getPos().equals(p.getPos());
    }
    
	@Override
	public void actions() {
		GameController.getInstance().openDoor(edge);

	}
}
