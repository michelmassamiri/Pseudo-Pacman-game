package Model.Entity.Actions;

import Controller.GameController;
import Model.Edge;
import Model.Entity.StaticEntity;

public class StaticButtonCloseAction extends AbstractAction {
	private Edge edge;
	
    public StaticButtonCloseAction(StaticEntity entity, Edge edge ){
    	super();
    	this.entity = entity;
    	this.edge = edge;
    }

	public StaticButtonCloseAction(Edge edge ){
		super();
		this.edge = edge;
	}
    
    public boolean isStartable() {
        return entity.getPos().equals(p.getPos());
    }
    
	@Override
	public void actions() {
		GameController.getInstance().closeDoor(edge);

	}
}
