package Model.Entity.Actions;

import Controller.GameController;
import Model.Edge;
import Model.Entity.StaticEntityButtonClose;

public class StaticButtonCloseAction extends AbstractAction {
	private	StaticEntityButtonClose button;
	private Edge edge;
	
    public StaticButtonCloseAction(StaticEntityButtonClose entity, Edge edge ){
    	super();
    	button = entity;
    	this.edge = edge;
    }
    
    public boolean isStartable() {
        return button.getPos().equals(p.getPos());
    }
    
	@Override
	public void actions() {
		GameController.getInstance().closeDoor(edge);

	}
}
