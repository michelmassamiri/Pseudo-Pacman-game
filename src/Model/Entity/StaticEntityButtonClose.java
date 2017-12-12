package Model.Entity;

import Model.Edge;
import Model.Entity.Actions.StaticButtonCloseAction;
import Model.Resources.Resources;

public class StaticEntityButtonClose extends StaticEntity{
	public StaticEntityButtonClose(Resources resource, Edge edge) {
		super(resource);
		action = new StaticButtonCloseAction(this, edge);
	}
	
	public StaticEntityButtonClose(Resources resource, float x, float y, Edge edge) {
		super(resource, x, y);
		action = new StaticButtonCloseAction(this, edge);
	}
}
