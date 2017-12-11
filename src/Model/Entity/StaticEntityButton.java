package Model.Entity;

import Model.Entity.Actions.StaticButtonAction;
import Model.Resources.Resources;

public class StaticEntityButton extends StaticEntity{
	public StaticEntityButton(Resources resource, float x, float y) {
		super(resource);
		action = new StaticButtonAction(this, x, y);
	}
	
	public StaticEntityButton(Resources resource, float x, float y, float x2, float y2) {
		super(resource, x, y);
		action = new StaticButtonAction(this, x2, y2);
	}
}
