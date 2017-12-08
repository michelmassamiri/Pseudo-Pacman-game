package Model.Entity.Actions;

import Controller.GameController;
import Model.Entity.StaticEntityButton;

public class StaticButtonAction extends AbstractAction{
	
	private	StaticEntityButton button;
	 
	
    public StaticButtonAction(StaticEntityButton entity){
    	super();
    	button = entity;
    }
    
    public boolean isStartable() {
        return button.getPos().equals(p.getPos());
    }
    
	@Override
	public void actions() {
		
	}
}
