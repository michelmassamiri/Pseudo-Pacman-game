package Model.Entity;

import Model.Entity.Actions.StaticButtonAction;
import Model.Resources.Resources;

public class StaticEntityButton extends StaticEntity{
	public StaticEntityButton(Resources resource) {
		super(resource);
		action = new StaticButtonAction(this);
	}
	
	public StaticEntityButton(Resources resource, float x, float y) {
		super(resource, x, y);
		action = new StaticButtonAction(this);
	}
}
