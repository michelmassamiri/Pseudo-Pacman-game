package Model.Entity;

import Model.Entity.Actions.StaticDoorAction;
import Model.Resources.Resources;

public class StaticEntityDoor extends StaticEntity{

	public StaticEntityDoor(Resources resource) {
		super(resource);
		action = new StaticDoorAction(this);
	}
	
	public StaticEntityDoor(Resources resource, float x, float y) {
		super(resource, x, y);
		action = new StaticDoorAction(this);
	}

}
