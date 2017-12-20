package Model.Entity.Actions;

import Controller.GameController;
import Model.Entity.StaticEntity;


public class StaticCandyAction extends AbstractAction{

    private int value;
	
    public StaticCandyAction(StaticEntity entity, int score){
    	super();
    	this.entity = entity;
    	value = score;
    }

    public StaticCandyAction(int score){
        super();
        value = score;
    }

    public boolean isStartable() {
        return entity.getPos().equals(p.getPos());
    }
    
	@Override
	public void actions() {
		GameController.getInstance().setScore(value);
		entity.setPosX(-1);
	}

}
