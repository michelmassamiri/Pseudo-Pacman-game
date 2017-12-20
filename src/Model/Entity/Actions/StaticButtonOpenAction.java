package Model.Entity.Actions;

import Controller.GameController;
import Model.Edge;
import Model.Entity.StaticEntity;

public class StaticButtonOpenAction extends AbstractAction{
	private StaticEntity button;
	private Edge edge;
	
    public StaticButtonOpenAction(StaticEntity entity, Edge edge ){
    	super();
    	this.entity = entity;
    	this.edge = edge;
    }

	public StaticButtonOpenAction(Edge edge){
		super();
		this.edge = edge;
	}
    
    public boolean isStartable() {
        return entity.getPos().equals(p.getPos());
    }
    
	@Override
	public void actions() {
		GameController.getInstance().openDoor(edge);

	}
}
