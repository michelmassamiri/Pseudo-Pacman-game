package Model.Entity;

import Model.Entity.Actions.StaticDoorAction;
import Model.Resources.Resources;

public class StaticEntityDoor extends StaticEntity{

	public StaticEntityDoor(Resources resource) {
		super(resource);
		action = new StaticDoorAction(this);
	}

}
