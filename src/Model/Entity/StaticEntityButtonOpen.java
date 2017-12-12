package Model.Entity;

import Model.Edge;
import Model.Entity.Actions.StaticButtonOpenAction;
import Model.Resources.Resources;

public class StaticEntityButtonOpen extends StaticEntity{
	public StaticEntityButtonOpen(Resources resource, Edge edge) {
		super(resource);
		action = new StaticButtonOpenAction(this, edge);
	}
	
	public StaticEntityButtonOpen(Resources resource, float x, float y, Edge edge) {
		super(resource, x, y);
		action = new StaticButtonOpenAction(this, edge);
	}
}
