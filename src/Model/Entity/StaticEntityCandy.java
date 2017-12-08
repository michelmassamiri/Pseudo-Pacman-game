package Model.Entity;

import Model.Entity.Actions.StaticCandyAction;
import Model.Resources.Resources;

public class StaticEntityCandy extends StaticEntity {
	
	public StaticEntityCandy(Resources resource, int score) {
		super(resource);
		action = new StaticCandyAction(this, score);
	}	
	
	public StaticEntityCandy(Resources resource, float x, float y, int score) {
		super(resource, x, y);
		action = new StaticCandyAction(this, score);
	}	
}
