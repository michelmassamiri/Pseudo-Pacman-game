package Model.Entity.Actions;

import Controller.GameController;
import Model.Entity.StaticEntity;

public class StaticDoorAction extends AbstractAction{

	private StaticEntity door;
	
	public StaticDoorAction(StaticEntity entity){
		super();
		this.entity = entity;
	}

	public StaticDoorAction(){
		super();
	}
	
	@Override
    public boolean isStartable() {
        return p.getPos().equals(entity.getPos());
    }
	
	
	@Override
	public void actions() {
		GameController.getInstance().setWin(true);
	}

}
