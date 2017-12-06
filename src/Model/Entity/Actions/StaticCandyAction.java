package Model.Entity.Actions;

import Controller.GameController;
import Model.Entity.StaticEntityCandy;


public class StaticCandyAction extends AbstractAction{

    private	StaticEntityCandy candy;
    private int value;
	
    public StaticCandyAction(StaticEntityCandy entity, int score){
    	super();
    	candy = entity;
    	value = score;
    }
    
    public boolean isStartable() {
        return candy.getPos().equals(p.getPos());
    }
    
	@Override
	public void actions() {
		GameController.getInstance().setScore(value);
		GameController.getInstance().getModel().supEntity(candy);
	}

}
