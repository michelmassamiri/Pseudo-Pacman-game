package Model.Entity.Actions;

import Controller.GameController;
import Model.Entity.StaticEntityDoor;

public class StaticDoorAction extends AbstractAction{

	private StaticEntityDoor door;
	
	public StaticDoorAction(StaticEntityDoor entity){
		super();
		door = entity;
	}
	
	@Override
    public boolean isStartable() {
        return p.getPos().equals(door.getPos());
    }
	
	
	@Override
	public void actions() {
		GameController.getInstance().setWin(true);
	}

}
